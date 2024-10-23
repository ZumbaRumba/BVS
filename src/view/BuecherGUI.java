package view;

import model.Buch;
import service.BuchService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


// Erstellen einer grafischen Oberfläche
public class BuecherGUI extends Application {

    private BuchService buchService;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        buchService = new BuchService();

        showMainMenu();
    }

    // Haupmenü
    private void showMainMenu() {

        // Layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        Label titleLabel = new Label("*** Bibliotheksverwaltungssystem ***");

        Button addBuchButton = new Button("Buch hinzufügen");
        addBuchButton.setOnAction(e -> showAddBookView());

        Button showListButton = new Button("Liste anzeigen");
        showListButton.setOnAction(e -> showListView());

        root.getChildren().addAll(titleLabel, addBuchButton, showListButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("BVS - Hauptmenü");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Eingabe des Buches
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
                return;
            }

            Buch buch = new Buch(titel, verjahr, autor, isbn);
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

    // Erstellen einer Box mit einer Liste
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

        TableColumn<Buch, String> statusColumn = new TableColumn<>("Verfügbarkeit");
        statusColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().isStatus() ? "Verliehen" : "Vorhanden"));

        tableView.getColumns().addAll(titelColumn, autorColumn, isbnColumn, verjahrColumn);

    // Daten laden
        tableView.getItems().addAll(buchService.getBuecherListe());

    // Buttons
    Button deleteButton = new Button("Buch löschen");
        deleteButton.setOnAction(e -> {
        Buch selectedTask = tableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            buchService.buchLoeschen(selectedTask.getTitel());
            showAlert("Erfolg", "Buch gelöscht.");
            showListView();
        } else {
            showAlert("Fehler", "Bitte wählen Sie ein Buch aus.");
        }
    });

        Button statusButton = new Button("Als verliehen markieren");
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

    // Warntext falls die Titel-Spalte leer eingegeben wird
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void stop() throws Exception {
        buchService.buecherSpeichern();
        super.stop();
    }


    public static void main(String[] args) {
        launch(args);
    }
}