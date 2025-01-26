package mvc.presentation;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.stage.Window;
import mvc.Book;
import mvc.Model;

import javax.swing.text.View;
import java.util.HashSet;
import java.util.Optional;


public class MainFX extends Application {
  private static HashSet<Book> books = new HashSet<>(); // Static reference to the books set from Main
  private Model model; // The model of the application
  private ObservableList<Book> bookList; // ObservableList for the ListView
  private ListView<Book> listView; // The ListView to display books


  @Override
  public void start(Stage stage) {
    // Initialize the model with the provided books
    model = new Model(books);

    // Convert the model's books to an ObservableList
    bookList = FXCollections.observableArrayList(model.getBooks());

    // Create the ListView to display books
    listView = new ListView<>(bookList);

    // Toolbar with buttons
    ToolBar toolBar = createToolBar(stage);

    // Root layout
    BorderPane root = new BorderPane();
    root.setTop(toolBar);
    root.setCenter(listView);

    // Scene setup
    Scene scene = new Scene(root, 600, 400);
    stage.setScene(scene);
    stage.setTitle("Book Manager");
    stage.show();
  }

  private ToolBar createToolBar(Stage stage) {
    // Create buttons
    Button btnAdd = new Button("Add");
    Button btnRemove = new Button("Remove");
    Button btnEdit = new Button("Edit");

    // Disable Remove and Edit by default
    btnRemove.setDisable(true);
    btnEdit.setDisable(true);

    // Listen for selection changes in the ListView
    listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      boolean selected = newSelection != null;
      btnRemove.setDisable(!selected);
      btnEdit.setDisable(!selected);
    });

    // Add button action
    btnAdd.setOnAction(e -> handleAdd(stage));

    // Remove button action
    btnRemove.setOnAction(e -> handleRemove());

    // Edit button action
    btnEdit.setOnAction(e -> handleEdit(stage));

    return new ToolBar(btnAdd, btnRemove, btnEdit);
  }

  private void handleAdd(Window owner) {
    // Dialog for adding a new book
    Dialog<Book> dialog = createBookDialog("Add Book", null);

    Optional<Book> result = dialog.showAndWait();
    result.ifPresent(book -> {
      model.addBook(book); // Update the model
      updateView(); // Refresh the ListView
    });
  }

  private void handleRemove() {
    Book selectedBook = listView.getSelectionModel().getSelectedItem();
    if (selectedBook != null) {
      model.removeBook(selectedBook); // Update the model
      updateView(); // Refresh the ListView
    }
  }

  private void handleEdit(Window owner) {
    Book selectedBook = listView.getSelectionModel().getSelectedItem();
    if (selectedBook != null) {
      // Dialog for editing the selected book
      Dialog<Book> dialog = createBookDialog("Edit Book", selectedBook);

      Optional<Book> result = dialog.showAndWait();
      result.ifPresent(editedBook -> {
        model.removeBook(selectedBook); // Remove the old book
        model.addBook(editedBook); // Add the updated book
        updateView(); // Refresh the ListView
      });
    }
  }

  private Dialog<Book> createBookDialog(String title, Book existingBook) {
    Dialog<Book> dialog = new Dialog<>();
    dialog.setTitle(title);

    // Dialog content
    DialogPane dialogPane = dialog.getDialogPane();
    dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    TextField titleField = new TextField(existingBook != null ? existingBook.getTitle() : "");
    TextField authorNameField = new TextField(existingBook != null ? existingBook.getAuthorName() : "");
    TextField authorSurnameField = new TextField(existingBook != null ? existingBook.getAuthorSurname() : "");
    TextField yearField = new TextField(existingBook != null ? existingBook.getYear() : "");

    dialogPane.setContent(new VBox(10,
            new Label("Title:"), titleField,
            new Label("Author First Name:"), authorNameField,
            new Label("Author Last Name:"), authorSurnameField,
            new Label("Year:"), yearField
    ));

    dialog.setResultConverter(buttonType -> {
      if (buttonType == ButtonType.OK) {
        return new Book(titleField.getText(), authorNameField.getText(), authorSurnameField.getText(), yearField.getText());
      }
      return null;
    });

    return dialog;
  }

  private void updateView() {
    // Refresh the ObservableList with the updated books from the model
    bookList.setAll(model.getBooks());
  }
}
