package view;

import model.Buch;
import repo.buecherRepo;
import service.BuchService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class BuecherGUI extends Application {

    private BuchService buchService;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        buchService = new BuchService();

        showMainMenu();
    }

    private void showMainMenu() {
        // Layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        Label titleLabel = new Label("*** Bibliotheksverwaltungssystem ***");

        Button addBuchButton = new Button("Buch hinzufügen");
        addBuchButton.setOnAction(e -> showAddBookView());

        Button showListButton = new Button("Liste anzeigen");
        showListButton.setOnAction(e -> showListView());

        Button searchBookButton = new Button("Buch suchen");
        searchBookButton.setOnAction(e -> showSearchBookView());

        root.getChildren().addAll(titleLabel, addBuchButton, showListButton, searchBookButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("BVS - Hauptmenü");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddBookView() {
        // Layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // Titel input
        TextField titelField = new TextField();
        titelField.setPromptText("Titel des Buches eingeben: ");

        // Autor input
        TextField autorField = new TextField();
        autorField.setPromptText("Autor des Buches eingeben: ");

        // Veröffentlichungsjahr input
        TextField verjahrField = new TextField();
        verjahrField.setPromptText("Veröffentlichungsjahr des Buches eingeben:");

        // ISBN input
        TextField isbnField = new TextField();
        isbnField.setPromptText("ISBN des Buches eingeben:");

        Button addButton = new Button("Buch hinzufügen");
        addButton.setOnAction(e -> {
            String titel = titelField.getText();
            String autor = autorField.getText();
            int verjahr = Integer.parseInt(verjahrField.getText());
            String isbn = isbnField.getText();

            if(titel.isEmpty()) {
                showAlert("Fehler", "Titel darf nicht leer sein.");
                return;
            }

            Buch buch = new Buch(titel, autor, isbn, verjahr);
            buchService.buchHinzufuegen(buch);

            showAlert("Bravo", "Hast du guuuut gemacht.");

            // Zurück zum Hauptmenü
            showMainMenu();
        });

        Button cancelButton = new Button("Abbrechen");
        cancelButton.setOnAction(e -> showMainMenu());

        HBox buttonBox = new HBox(10, addButton, cancelButton);

        root.getChildren().addAll(
                new Label("Neues Buch hinzufügen"),
                titelField,
                autorField,
                verjahrField,
                isbnField,
                buttonBox
        );

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Buch hinzufügen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showListView() {
        // Layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // TableView for tasks
        TableView<Buch> tableView = new TableView<>();

        // Columns
        TableColumn<Buch, String> titelColumn = new TableColumn<>("Titel");
        titelColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitel()));

        TableColumn<Buch, String> autorColumn = new TableColumn<>("Autor");
        autorColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAutor()));

        TableColumn<Buch, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getIsbn()));

        TableColumn<Buch, Integer> verjahrColumn = new TableColumn<>("Veröf. Jahr");
        verjahrColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getVerjahr()).asObject());

        TableColumn<Buch, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().isStatus() ? "Verliehen" : "Vorhanden")
        );

        tableView.getColumns().addAll(titelColumn, autorColumn, isbnColumn, verjahrColumn, statusColumn);

        // Daten laden
        tableView.getItems().addAll(buchService.getbuecher());

        // Buttons
        Button deleteButton = new Button("Buch löschen");
        deleteButton.setOnAction(e -> {
            Buch selectedTask = tableView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                // Bestätigungsdialog anzeigen
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Bestätigung");
                confirmationAlert.setHeaderText("Buch löschen");
                confirmationAlert.setContentText("Bist du sicher, dass das Buch '" + selectedTask.getTitel() + "' gelöscht werden soll?");

                // Benutzerantwort abfragen
                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    buchService.buchLoeschen(selectedTask.getTitel());
                    showAlert("Erfolg", "Buch gelöscht.");
                    showListView();
                }
            } else {
                showAlert("Fehler", "Bitte wählen Sie ein Buch aus.");
            }
        });


        Button statusButton = new Button("Status ändern ");
        statusButton.setOnAction(e -> {
            Buch selectedBook = tableView.getSelectionModel().getSelectedItem();
            if (selectedBook != null) {
                buchService.verfuegbarkeit(selectedBook.getTitel());
                showAlert("Erfolg", "Buch als verliehen markiert.");
                showListView();
            } else {
                showAlert("Fehler", "Bitte wählen Sie ein Buch aus.");
            }
        });

        Button addButton = new Button("Neues Buch hinzufügen");
        addButton.setOnAction(e -> showAddBookView());

        Button backButton = new Button("Zurück zum Hauptmenü");
        backButton.setOnAction(e -> showMainMenu());

        HBox buttonBox = new HBox(10, deleteButton, statusButton, addButton, backButton);

        root.getChildren().addAll(new Label("Alle Bücher"), tableView, buttonBox);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Alle Bücher anzeigen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showSearchBookView() {
        // Layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // Suchfeld
        TextField searchField = new TextField();
        searchField.setPromptText("Suchbegriff eingeben");

        Button searchButton = new Button("Suchen");
        TableView<Buch> resultsTable = new TableView<>();

        // Suchbutton-Action
        searchButton.setOnAction(e -> {
            String suchbegriff = searchField.getText();
            if (suchbegriff.isEmpty()) {
                showAlert("Fehler", "Suchbegriff darf nicht leer sein.");
                return;
            }

            // Suche im BuchService
            List<Buch> suchergebnisse = buchService.sucheBuch(suchbegriff);
            resultsTable.getItems().clear(); // Vorherige Ergebnisse löschen
            resultsTable.getItems().addAll(suchergebnisse); // Neue Ergebnisse hinzufügen
        });

        // Spalten für die Ergebnistabelle
        TableColumn<Buch, String> titelColumn = new TableColumn<>("Titel");
        titelColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitel()));

        TableColumn<Buch, String> autorColumn = new TableColumn<>("Autor");
        autorColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAutor()));

        TableColumn<Buch, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getIsbn()));

        TableColumn<Buch, Integer> verjahrColumn = new TableColumn<>("Veröf. Jahr");
        verjahrColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getVerjahr()).asObject());

        TableColumn<Buch, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().isStatus() ? "Verliehen" : "Vorhanden")
        );

        resultsTable.getColumns().addAll(titelColumn, autorColumn, isbnColumn, verjahrColumn, statusColumn);

        // Zurück-Button
        Button backButton = new Button("Zurück zum Hauptmenü");
        backButton.setOnAction(e -> showMainMenu());

        root.getChildren().addAll(
                new Label("Buch suchen"),
                searchField,
                searchButton,
                resultsTable,
                backButton
        );

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Buch suchen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message){
    }
}
