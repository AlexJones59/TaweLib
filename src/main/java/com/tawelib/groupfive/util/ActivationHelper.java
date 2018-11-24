package com.tawelib.groupfive.util;

import com.tawelib.groupfive.exception.InvalidProductKeyException;
import com.tawelib.groupfive.exception.UnsupportedSystemException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains methods used to verify activation of the product and to activate it.
 *
 * @author Petr Hoffmann
 * @version 0.3
 */
public class ActivationHelper {

  private static final String PROPERTIES_FILE_PATH = "app.properties";
  private static final String PRODUCT_KEY_PROPERTY_NAME = "product_key";
  private static final String PRODUCT_HASH_PROPERTY_NAME = "product_key_hash";
  private static final String PRODUCT_KEY_HASH_SALT = "GO GROUP 5 GO!!!";
  private static final String PRODUCT_KEY_PATTERN = "LPK\\-\\d{4}-(?:\\w|\\d){5}-\\w{4}-XXX";

  private ActivationHelper() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Checks whether the product is activated. Returns true if so, false otherwise.
   *
   * @return Activated.
   */
  public static boolean isActivated() {
    ProductKeyHashPair productKeyHashPair;

    try {
      productKeyHashPair = readProductKeyHashPair();
    } catch (IOException e) {
      return false;
    }

    return isProductKeyHashPairValid(
        productKeyHashPair.productKey,
        productKeyHashPair.productKeyHash
    );
  }

  /**
   * Activates the product. Throws an exception when unable to.
   *
   * @param productKey Product key.
   * @param controlHash Control hash.
   * @throws InvalidProductKeyException When the given product key-hash pair is invalid.
   * @throws UnsupportedSystemException When the activation fails.
   */
  public static void activate(String productKey, String controlHash)
      throws InvalidProductKeyException {
    if (!isProductKeyHashPairValid(productKey, controlHash)) {
      throw new InvalidProductKeyException();
    }

    Properties properties;

    try (FileWriter writer = new FileWriter(PROPERTIES_FILE_PATH)) {
      properties = new Properties();

      properties.setProperty(PRODUCT_KEY_PROPERTY_NAME, productKey);
      properties.setProperty(PRODUCT_HASH_PROPERTY_NAME, controlHash);

      properties.store(writer, null);
    } catch (IOException e) {
      throw new UnsupportedSystemException(e);
    }
  }

  /**
   * Checks whether the product key matches the control hash.
   *
   * @param productKey Product key.
   * @param controlHash Control hash.
   * @return Whether the product key matches the control hash.
   */
  private static boolean keyMatchesHash(String productKey, String controlHash) {
    MessageDigest messageDigest;
    try {
      messageDigest = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new UnsupportedSystemException("Activation cannot be verified on this system.");
    }

    String saltedContent = productKey + PRODUCT_KEY_HASH_SALT;

    byte[] rawHash = messageDigest.digest(saltedContent.getBytes());

    BigInteger numericHash = new BigInteger(1, rawHash);

    String calculatedHash = numericHash.toString(16);

    return calculatedHash.equals(controlHash);
  }

  /**
   * Returns true if the product key-hash pair is valid, false otherwise.
   *
   * @param productKey Product key.
   * @param controlHash Control hash.
   * @return Whether the product key-hash pair is valid.
   */
  private static boolean isProductKeyHashPairValid(String productKey, String controlHash) {
    Pattern pattern = Pattern.compile(PRODUCT_KEY_PATTERN);
    Matcher matcher = pattern.matcher(productKey);
    boolean matchesPattern = matcher.matches();

    boolean isAuthorized = keyMatchesHash(productKey, controlHash);

    return matchesPattern && isAuthorized;
  }

  /**
   * Extracts the product key-hash pair from the properties file.
   *
   * @return Product key-hash pair.
   * @throws IOException When unable to read the properties file.
   */
  private static ProductKeyHashPair readProductKeyHashPair() throws IOException {
    FileReader reader = new FileReader(PROPERTIES_FILE_PATH);

    Properties properties = new Properties();
    properties.load(reader);

    ProductKeyHashPair productKeyHashPair = new ProductKeyHashPair();

    productKeyHashPair.productKey = properties.getProperty(PRODUCT_KEY_PROPERTY_NAME);
    productKeyHashPair.productKeyHash = properties.getProperty(PRODUCT_HASH_PROPERTY_NAME);

    reader.close();

    return productKeyHashPair;
  }

  /**
   * A data class holding a product key-hash pair.
   */
  private static class ProductKeyHashPair {

    String productKey;
    String productKeyHash;
  }
}
