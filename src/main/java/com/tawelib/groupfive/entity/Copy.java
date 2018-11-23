package com.tawelib.groupfive.entity;

//import com.tawelib.groupfive.repository.LeaseRepository;
import java.util.List;

/**
 * File Name - Copy.java
 * The ‘Copy’ class is the class that stores information about a particular copy of a
 * resource.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Copy {

  private String id;
  private CopyStatus status;
  private Resource resource;
  private String borrowingCustomerId;

  /**
   * Instantiates a new Copy.
   *
   * @param resource the resource
   */
  public Copy(Resource resource) {
    this.resource = resource;
    this.status = CopyStatus.AVAILABLE;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public CopyStatus getStatus() {
    return status;
  }

  /**
   * Sets status.
   *
   * @param status the status
   */
  public void setStatus(CopyStatus status) {
    this.status = status;
  }

  /**
   * Gets borrowing customer id.
   *
   * @return the borrowing customer id
   */
  public String getBorrowingCustomerId() {
    return borrowingCustomerId;
  }

  /**
   * Sets borrowing customer id.
   *
   * @param borrowingCustomerId the borrowing customer id
   */
  public void setBorrowingCustomerId(String borrowingCustomerId) {
    this.borrowingCustomerId = borrowingCustomerId;
  }

  /**
   * Gets lease history.
   *
   * @return list of leases of this copy
   */
  public List<Lease> getLeaseHistory() {
    //return LeaseRepository.getLeases(this.id);
    return null; //TODO: Once CopyRepository has been implemented, uncomment above line.
  }

  /**
   * Gets current lease.
   *
   * @return the current lease
   */
  public List<Lease> getCurrentLease() {
    //allLeases = LeaseRepository.getLeases(this.id);
    return null; //TODO: Once CopyRepository has been implemented, finish this method.
    //TODO: Write logic for sorting through leases to find current lease pertaining to this copy


  }


}
