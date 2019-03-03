package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Game;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;
import com.tawelib.groupfive.exception.ResourceNotFoundException;
import com.tawelib.groupfive.manager.ResourceManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.ExplosionHelper;
import com.tawelib.groupfive.util.FileSystemHelper;
import com.tawelib.groupfive.util.ResourceHelper;
import com.tawelib.groupfive.util.SceneHelper;
import com.tawelib.groupfive.util.TrailerHelper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * The type Resource crud controller. From here all different types of resources have a screen that
 * can be viewed by the user in a neat format and is populated based on previous actions they have
 * selected.
 *
 * @author Petr Hoffman
 * @version 1.0
 */
public class ResourceCrudController extends BaseFxmlController {

  private Resource selectedResource;
  private CrudAction crudAction;

  @FXML
  private AnchorPane bookAnchorPane;

  @FXML
  private AnchorPane dvdAnchorPane;

  @FXML
  private AnchorPane laptopAnchorPane;

  @FXML
  private AnchorPane gameAnchorPane;

  @FXML
  private Label idLabel;

  @FXML
  private TextField titleTextField;

  @FXML
  private TextField yearTextField;

  @FXML
  private TextField authorTextField;

  @FXML
  private TextField publisherTextField;

  @FXML
  private TextField genreTextField;

  @FXML
  private TextField isbnTextField;

  @FXML
  private TextField languageTextField;

  @FXML
  private TextField modelTextField;

  @FXML
  private TextField manufacturerTextField;

  @FXML
  private TextField operatingSystemTextField;

  @FXML
  private TextField directorTextField;

  @FXML
  private TextField runtimeTextField;

  @FXML
  private TextField publisherGameTextField;

  @FXML
  private TextField genreGameTextField;

  @FXML
  private TextField ratingTextField;

  @FXML
  private ComboBox<String> multiplayerComboBox;

  @FXML
  private TextArea audioLanguagesTextArea;

  @FXML
  private TextArea subtitleLanguagesTextArea;

  @FXML
  private Button createButton;

  @FXML
  private Button updateButton;

  @FXML
  private Button showTrailerButton;

  @FXML
  private Button showCopiesButton;

  @FXML
  private ImageView resourceImageView;

  @FXML
  private ComboBox<ResourceType> resourceTypeComboBox;

  private ResourceType[] resourceTypes = {ResourceType.BOOK, ResourceType.DVD,
      ResourceType.LAPTOP, ResourceType.GAME};

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    if (crudAction == CrudAction.UPDATE) {
      populateResource();
    } else if (crudAction == CrudAction.CREATE) {
      if (resourceTypeComboBox.getItems().isEmpty()) {
        resourceTypeComboBox.getItems().addAll(Arrays.asList(resourceTypes));
        resourceTypeComboBox.setValue(ResourceType.BOOK);
      }
      initMultiplayerComboBox();

      switch (resourceTypeComboBox.getValue()) {
        case BOOK:
          showSubtypePane(bookAnchorPane);
          break;
        case DVD:
          showSubtypePane(dvdAnchorPane);
          break;
        case LAPTOP:
          showSubtypePane(laptopAnchorPane);
          break;
        case GAME:
          showSubtypePane(gameAnchorPane);
          break;
        default:
          break;
      }
    }

    //sets visibilities based on the above conditions
    showTrailerButton.setVisible(crudAction != CrudAction.CREATE);
    showCopiesButton.setVisible(crudAction != CrudAction.CREATE);
    resourceTypeComboBox.setVisible(crudAction == CrudAction.CREATE);

    createButton
        .setVisible(isLibrarianLoggedIn() && crudAction == CrudAction.CREATE);
    updateButton
        .setVisible(isLibrarianLoggedIn() && crudAction == CrudAction.UPDATE);
  }

  /**
   * Creates a new resource depending on the type of resource.
   */
  public void create() {
    try {
      switch (resourceTypeComboBox.getValue()) {
        case BOOK:
          ResourceManager.createBook(library, titleTextField.getText(),
              Integer.parseInt(yearTextField.getText()), null,
              authorTextField.getText(), publisherTextField.getText(),
              genreTextField.getText(), isbnTextField.getText(),
              languageTextField.getText());
          break;
        case DVD:
          ResourceManager.createDvd(library, titleTextField.getText(),
              Integer.parseInt(yearTextField.getText()), null,
              directorTextField.getText(),
              Integer.parseInt(runtimeTextField.getText()),
              ExplosionHelper.explode(audioLanguagesTextArea.getText()),
              ExplosionHelper.explode(subtitleLanguagesTextArea.getText()));
          break;
        case LAPTOP:
          ResourceManager.createLaptop(library, titleTextField.getText(),
              Integer.parseInt(yearTextField.getText()), null,
              manufacturerTextField.getText(), modelTextField.getText(),
              operatingSystemTextField.getText());
          break;
        case GAME:
          boolean mp;
          if ((multiplayerComboBox.getValue()).equals("Multiplayer")) {
            mp = true;
          } else {
            mp = false;
          }
          ResourceManager.createGame(library, titleTextField.getText(),
              Integer.parseInt(yearTextField.getText()), null,
              publisherGameTextField.getText(), genreGameTextField.getText(),
              ratingTextField.getText(), mp);
          break;
        default:
          break;
      }

      AlertHelper.alert(AlertType.INFORMATION, "Successfully created");
      back();
    } catch (NumberFormatException e) {
      AlertHelper.alert(AlertType.ERROR, e.getMessage());
    }
  }

  /**
   * Updates a resource depending on the type of resource.
   */
  public void update() {
    try {
      switch (selectedResource.getType()) {
        case BOOK:
          ResourceManager.updateBook(library, selectedResource.getResourceId(),
              titleTextField.getText(),
              Integer.parseInt(yearTextField.getText()), null,
              authorTextField.getText(), publisherTextField.getText(),
              genreTextField.getText(), isbnTextField.getText(),
              languageTextField.getText());
          break;
        case DVD:
          ResourceManager.updateDvd(library, selectedResource.getResourceId(),
              titleTextField.getText(),
              Integer.parseInt(yearTextField.getText()), null,
              directorTextField.getText(),
              Integer.parseInt(runtimeTextField.getText()),
              ExplosionHelper.explode(audioLanguagesTextArea.getText()),
              ExplosionHelper.explode(subtitleLanguagesTextArea.getText()));
          break;
        case LAPTOP:
          ResourceManager
              .updateLaptop(library, selectedResource.getResourceId(),
                  titleTextField.getText(),
                  Integer.parseInt(yearTextField.getText()), null,
                  manufacturerTextField.getText(), modelTextField.getText(),
                  operatingSystemTextField.getText());
          break;
        case GAME:
          boolean mp;
          if ((multiplayerComboBox.getValue()).equals("Multiplayer")) {
            mp = true;
          } else {
            mp = false;
          }
          ResourceManager.updateGame(library, selectedResource.getResourceId(),
              titleTextField.getText(), Integer.parseInt(yearTextField.getText()),
              null, publisherGameTextField.getText(), genreGameTextField.getText(),
              ratingTextField.getText(), mp);
          break;
        default:
          break;
      }

      AlertHelper.alert(AlertType.INFORMATION, "Successfully updated");
      back();
    } catch (NumberFormatException | ResourceNotFoundException e) {
      AlertHelper.alert(AlertType.ERROR, e.getMessage());
    }
  }

  /**
   * Displays a popup window with the resource's trailer.
   */
  public void showTrailer() {
    TrailerHelper.showTrailer(selectedResource);
  }

  /**
   * Shows copies available for a resource.
   */
  public void showCopies() {
    ResourceCopiesController newController = (ResourceCopiesController) SceneHelper
        .setUpScene(this, "ResourceCopies");

    newController.setSelectedResource(selectedResource);
    newController.refresh();
  }

  /**
   * The method creates the window with selecting the image to set as a profile called from pressing
   * a button chooseFileImg, accepts only png format.
   */
  public void chooseImage() {
    if (isLibrarianLoggedIn()) {
      FileChooser fileChooser = new FileChooser();
      fileChooser.getExtensionFilters()
          .add(new FileChooser.ExtensionFilter("PNG", "*.png"));
      File file = fileChooser.showOpenDialog(getPrimaryStage());
      if (file != null) {
        try {
          File currProfImg = new File(
              FileSystemHelper.getResourcePicturePath(selectedResource));
          BufferedImage tempImg = ImageIO.read(file);
          ImageIO.write(tempImg, "png", currProfImg);
        } catch (IOException e) {
          AlertHelper.alert(AlertType.ERROR, "Unable to load image.");
        }

        AlertHelper.alert(
            AlertType.INFORMATION,
            "Resource image set successfully."
        );
        resourceImageView.setImage(
            ResourceHelper.getResourceImage(selectedResource)
        );
      }
    }
  }

  /**
   * Returns to the browse resources screen.
   */
  @Override
  public void back() {
    SceneHelper.setUpScene(this, "BrowseResources");
  }

  /**
   * Gets selected resource.
   *
   * @return the selected resource
   */
  public Resource getSelectedResource() {
    return selectedResource;
  }

  /**
   * Sets selected resource.
   *
   * @param selectedResource the selected resource
   */
  public void setSelectedResource(Resource selectedResource) {
    this.selectedResource = selectedResource;
  }

  /**
   * Gets crud action.
   *
   * @return the crud action
   */
  public CrudAction getCrudAction() {
    return crudAction;
  }

  /**
   * Sets crud action.
   *
   * @param crudAction the crud action
   */
  public void setCrudAction(CrudAction crudAction) {
    this.crudAction = crudAction;
  }

  /**
   * Set visibilities and actions for each resource type.
   *
   * @param pane the GUI pane for this screen
   */
  private void showSubtypePane(AnchorPane pane) {
    bookAnchorPane.setVisible(bookAnchorPane == pane);
    bookAnchorPane.setManaged(bookAnchorPane == pane);
    dvdAnchorPane.setVisible(dvdAnchorPane == pane);
    dvdAnchorPane.setManaged(dvdAnchorPane == pane);
    laptopAnchorPane.setVisible(laptopAnchorPane == pane);
    laptopAnchorPane.setManaged(laptopAnchorPane == pane);
    gameAnchorPane.setVisible(gameAnchorPane == pane);
    gameAnchorPane.setManaged(gameAnchorPane == pane);
  }

  /**
   * populates the screen with information about the resource.
   */
  private void populateResource() {
    resourceImageView.setImage(
        ResourceHelper.getResourceImage(selectedResource)
    );
    idLabel.setText(selectedResource.getResourceId());
    titleTextField.setText(selectedResource.getTitle());
    yearTextField.setText(Integer.toString(selectedResource.getYear()));

    switch (selectedResource.getType()) {
      case BOOK:
        populateBook();
        showSubtypePane(bookAnchorPane);
        break;
      case DVD:
        populateDvd();
        showSubtypePane(dvdAnchorPane);
        break;
      case LAPTOP:
        populateLaptop();
        showSubtypePane(laptopAnchorPane);
        break;
      case GAME:
        populateGame();
        showSubtypePane(gameAnchorPane);
        break;
      default:
        break;
    }
  }

  /**
   * Method used when populating the screen for a book.
   */
  private void populateBook() {
    Book selectedBook = (Book) selectedResource;

    authorTextField.setText(selectedBook.getAuthor());
    publisherTextField.setText(selectedBook.getPublisher());
    genreTextField.setText(selectedBook.getGenre());
    isbnTextField.setText(selectedBook.getIsbn());
    languageTextField.setText(selectedBook.getLanguage());
  }

  /**
   * Method used when populating the screen for a DVD.
   */
  private void populateDvd() {
    Dvd selectedDvd = (Dvd) selectedResource;

    directorTextField.setText(selectedDvd.getDirector());
    runtimeTextField.setText(Integer.toString(selectedDvd.getRuntime()));
    audioLanguagesTextArea
        .setText(ExplosionHelper.implode(selectedDvd.getLanguages()));
    subtitleLanguagesTextArea
        .setText(ExplosionHelper.implode(selectedDvd.getSubtitleLanguages()));
  }

  /**
   * Method used when populating the screen for a laptop.
   */
  private void populateLaptop() {
    Laptop selectedLaptop = (Laptop) selectedResource;

    modelTextField.setText(selectedLaptop.getModel());
    manufacturerTextField.setText(selectedLaptop.getModel());
    operatingSystemTextField
        .setText(selectedLaptop.getInstalledOperatingSystem());
  }

  private void populateGame() {
    Game selectedGame = (Game) selectedResource;
    initMultiplayerComboBox();
    publisherGameTextField.setText(selectedGame.getPublisher());
    genreGameTextField.setText(selectedGame.getGenre());
    ratingTextField.setText(selectedGame.getRating());
    boolean mp = ((Game) selectedResource).isMultiplayer();
    String text;
    if (mp) {
      text = "Multiplayer";
    } else {
      text = "Singleplayer";
    }
    multiplayerComboBox.setValue(text);
  }

  /**
   * The method initializes the comboBox for choosing the multiplayer/singleplayer option.
   */
  private void initMultiplayerComboBox() {
    if (multiplayerComboBox.getItems().isEmpty()) {
      multiplayerComboBox.getItems().addAll("Multiplayer", "Singleplayer");
      multiplayerComboBox.setValue("Singleplayer");
    }
  }
}
