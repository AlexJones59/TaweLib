package com.tawelib.groupfive.entity;

//import com.tawelib.groupfive.repository.LeaseRepository;
import java.util.List;

/**
 * Copy.java
 * The ‘Copy’ class is the class that stores information about a particular copy of a resource.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Copy {

  private String id;
  private CopyStatus status;
  private Resource resource;

  /**
   * Instantiates a new Copy.
   *
   * @param resource the resource
   */
  public Copy(Resource resource) {
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
    //TODO: Write the logic for sorting through leases to find only the current lease pertaining to this copy


  }
}
