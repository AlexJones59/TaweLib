package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;
import com.tawelib.groupfive.manager.ResourceManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.ExplosionHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
  private TextArea audioLanguagesTextArea;

  @FXML
  private TextArea subtitleLanguagesTextArea;

  @FXML
  private Button createButton;

  @FXML
  private Button updateButton;

  @FXML
  private Button showCopiesButton;

  @FXML
  private ComboBox<ResourceType> resourceTypeComboBox;

  private ResourceType[] resourceTypes = {ResourceType.BOOK, ResourceType.DVD,
      ResourceType.LAPTOP};

  @Override
  public void refresh() {
    if (crudAction == CrudAction.UPDATE) {
      populateResource();
    } else if (crudAction == CrudAction.CREATE) {
      if (resourceTypeComboBox.getItems().isEmpty()) {
        resourceTypeComboBox.getItems().addAll(Arrays.asList(resourceTypes));
        resourceTypeComboBox.setValue(ResourceType.BOOK);
      }

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
        default:
          break;
      }
    }

    showCopiesButton.setVisible(crudAction != CrudAction.CREATE);
    resourceTypeComboBox.setVisible(crudAction == CrudAction.CREATE);

    createButton
        .setVisible(isLibrarianLoggedIn() && crudAction == CrudAction.CREATE);
    updateButton
        .setVisible(isLibrarianLoggedIn() && crudAction == CrudAction.UPDATE);
  }

  private void showSubtypePane(AnchorPane pane) {
    bookAnchorPane.setVisible(bookAnchorPane == pane);
    bookAnchorPane.setManaged(bookAnchorPane == pane);
    dvdAnchorPane.setVisible(dvdAnchorPane == pane);
    dvdAnchorPane.setManaged(dvdAnchorPane == pane);
    laptopAnchorPane.setVisible(laptopAnchorPane == pane);
    laptopAnchorPane.setManaged(laptopAnchorPane == pane);
  }

  private void populateResource() {
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
      default:
        break;
    }
  }

  private void populateBook() {
    Book selectedBook = (Book) selectedResource;

    authorTextField.setText(selectedBook.getAuthor());
    publisherTextField.setText(selectedBook.getPublisher());
    genreTextField.setText(selectedBook.getGenre());
    isbnTextField.setText(selectedBook.getIsbn());
    languageTextField.setText(selectedBook.getLanguage());
  }

  private void populateDvd() {
    Dvd selectedDvd = (Dvd) selectedResource;

    directorTextField.setText(selectedDvd.getDirector());
    runtimeTextField.setText(Integer.toString(selectedDvd.getRuntime()));
    audioLanguagesTextArea
        .setText(ExplosionHelper.implode(selectedDvd.getLanguages()));
    subtitleLanguagesTextArea
        .setText(ExplosionHelper.implode(selectedDvd.getSubtitleLanguages()));
  }

  private void populateLaptop() {
    Laptop selectedLaptop = (Laptop) selectedResource;

    modelTextField.setText(selectedLaptop.getModel());
    manufacturerTextField.setText(selectedLaptop.getModel());
    operatingSystemTextField
        .setText(selectedLaptop.getInstalledOperatingSystem());
  }

  /**
   * Creates a new resource.
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
              ExplosionHelper.explode(audioLanguagesTextArea.getText()));
          break;
        case LAPTOP:
          ResourceManager.createLaptop(library, titleTextField.getText(),
              Integer.parseInt(yearTextField.getText()), null,
              manufacturerTextField.getText(), modelTextField.getText(),
              operatingSystemTextField.getText());
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
   * Updates a resource.
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
        default:
          break;
      }

      AlertHelper.alert(AlertType.INFORMATION, "Successfully updated");
      back();
    } catch (NumberFormatException e) {
      AlertHelper.alert(AlertType.ERROR, e.getMessage());
    }
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
   * {@inheritDoc}
   */
  @Override
  public void back() {
    SceneHelper.setUpScene(this, "BrowseResources");
  }

  public Resource getSelectedResource() {
    return selectedResource;
  }

  public void setSelectedResource(Resource selectedResource) {
    this.selectedResource = selectedResource;
  }

  public CrudAction getCrudAction() {
    return crudAction;
  }

  public void setCrudAction(CrudAction crudAction) {
    this.crudAction = crudAction;
  }
}
