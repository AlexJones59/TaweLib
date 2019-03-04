package com.tawelib.groupfive.entity;

import com.tawelib.groupfive.repository.*;

import java.io.Serializable;

/**
 * Library.java Brings all the elements needed for the library together.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class Library implements Serializable {

  /**
   * The name of the library.
   */
  private String name;

  /**
   * The resource repository.
   */
  private ResourceRepository resourceRepository;

  /**
   * The copy repository.
   */
  private CopyRepository copyRepository;

  /**
   * The lease repository.
   */
  private LeaseRepository leaseRepository;

  /**
   * The transaction repository.
   */
  private TransactionRepository transactionRepository;

  /**
   * The request repository.
   */
  private RequestRepository requestRepository;

  /**
   * The customer repository.
   */
  private CustomerRepository customerRepository;

  /**
   * The librarian repository.
   */
  private LibrarianRepository librarianRepository;

  /**
   * The fine repository.
   */
  private FineRepository fineRepository;

  /**
   * The event repository.
   */
  private EventRepository eventRepository;

  /**
   * The participation repository.
   */
  private ParticipationRepository participationRepository;

  /**
   * Creates a new library with a given name.
   *
   * @param name The name of the library.
   */
  public Library(String name) {
    this.name = name;
    resourceRepository = new ResourceRepository();
    copyRepository = new CopyRepository();
    leaseRepository = new LeaseRepository();
    transactionRepository = new TransactionRepository();
    requestRepository = new RequestRepository();
    customerRepository = new CustomerRepository();
    librarianRepository = new LibrarianRepository();
    fineRepository = new FineRepository();
    eventRepository = new EventRepository();
    participationRepository = new ParticipationRepository();
  }

  /**
   * Retrieves the name of the library.
   *
   * @return The name of the library.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the library.
   *
   * @param name New name of the library.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieves the resource repository.
   *
   * @return The resource repository.
   */
  public ResourceRepository getResourceRepository() {
    return resourceRepository;
  }

  /**
   * Retrieves the copy repository.
   *
   * @return The copy repository.
   */
  public CopyRepository getCopyRepository() {
    return copyRepository;
  }

  /**
   * Retrieves the lease repository.
   *
   * @return The lease repository.
   */
  public LeaseRepository getLeaseRepository() {
    return leaseRepository;
  }

  /**
   * Retrieves the transaction repository.
   *
   * @return The transaction repository.
   */
  public TransactionRepository getTransactionRepository() {
    return transactionRepository;
  }

  /**
   * Retrieves the request repository.
   *
   * @return The request repository.
   */
  public RequestRepository getRequestRepository() {
    return requestRepository;
  }

  /**
   * Retrieves the customer repository.
   *
   * @return The customer repository.
   */
  public CustomerRepository getCustomerRepository() {
    return customerRepository;
  }

  /**
   * Retrieves the librarian repository.
   *
   * @return The librarian repository.
   */
  public LibrarianRepository getLibrarianRepository() {
    return librarianRepository;
  }

  /**
   * Gets fine repository.
   *
   * @return the fine repository
   */
  public FineRepository getFineRepository() {
    return fineRepository;
  }

  /**
   * Retrieves event repository.
   *
   * @return the event repository
   */
  public EventRepository getEventRepository() {
    return eventRepository;
  }

  /**
   * Retrieves participation repository.
   *
   * @return the participation repository
   */
  public ParticipationRepository getParticipationRepository() {
    return participationRepository;
  }

}
