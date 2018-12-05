package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.util.ExplosionHelper;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ResourceCrudController extends BaseFxmlController {

  private Resource selectedResource;
  private CrudAction crudAction;

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
  private TextField manufacturedTextField;

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

  @Override
  public void refresh() {
    if (isLibrarianLoggedIn()) {
      if (crudAction == CrudAction.UPDATE) {
        populateResource();

        createButton.setVisible(false);
      } else {
        updateButton.setVisible(false);
      }
    } else {
      createButton.setVisible(false);
      updateButton.setVisible(false);
    }
  }

  private void populateResource() {
    idLabel.setText(selectedResource.getResourceId());
    titleTextField.setText(selectedResource.getTitle());
    yearTextField.setText(Integer.toString(selectedResource.getYear()));

    switch (selectedResource.getType()) {
      case BOOK:
        populateBook();
        break;
      case DVD:
        populateDvd();
        break;
      case LAPTOP:
        populateLaptop();
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
    audioLanguagesTextArea.setText(
        ExplosionHelper.implode(selectedDvd.getLanguages())
    );
    subtitleLanguagesTextArea.setText(
        ExplosionHelper.implode(selectedDvd.getSubtitleLanguages())
    );
  }

  private void populateLaptop() {
    Laptop selectedLaptop = (Laptop) selectedResource;

    modelTextField.setText(selectedLaptop.getModel());
    manufacturedTextField.setText(selectedLaptop.getModel());
    operatingSystemTextField.setText(
        selectedLaptop.getInstalledOperatingSystem()
    );
  }

  public void create() {

  }

  public void update() {

  }

  public void showCopies() {

  }

  public void back() {
    SceneHelper.setUpScene(this, "BrowseResources");
  }

  public Resource getSelectedResource() {
    return selectedResource;
  }

  public void setSelectedResource(
      Resource selectedResource
  ) {
    this.selectedResource = selectedResource;
  }

  public CrudAction getCrudAction() {
    return crudAction;
  }

  public void setCrudAction(CrudAction crudAction) {
    this.crudAction = crudAction;
  }
}
