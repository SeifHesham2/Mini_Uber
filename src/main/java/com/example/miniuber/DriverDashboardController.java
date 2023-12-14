package com.example.miniuber;

import com.example.miniuber.classes.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class DriverDashboardController {
    @FXML
    private Pane panel1;
    @FXML
    private Pane panel2;
    @FXML
    private Pane panel3;
    @FXML
    private Pane panel4;

    // VIEW TRIPS
    @FXML
    private TableView<Trip> tableViewInPanel2;
    @FXML
    private TableColumn<Trip, Integer> tripIdColumn;
    @FXML
    private TableColumn<Trip, String> destinationColumn;
    @FXML
    private TableColumn<Trip, String> pickupColumn;
    @FXML
    private TableColumn<Trip, LocalDateTime> timeColumn;
    @FXML
    private TableColumn<Trip, Double> priceColumn;
    @FXML
    private TableColumn<Trip, PaymentMethod> paymentTypeColumn;
    @FXML
    private TableColumn<Trip, Boolean> isFinished;
    private ObservableList<Trip> data = FXCollections.observableArrayList(); // YourDataType should be a class representing your data

    @FXML
    private Label successLabel1;

    // AVAILABLE TRIPS
    @FXML
    private TableView<Trip> tableViewInPanel1;
    @FXML
    private TableColumn<Trip, Integer> tripIdColumn1;
    @FXML
    private TableColumn<Trip, String> destinationColumn1;
    @FXML
    private TableColumn<Trip, String> pickupColumn1;
    @FXML
    private TableColumn<Trip, LocalDateTime> timeColumn1;
    @FXML
    private TableColumn<Trip, Double> priceColumn1;
    @FXML
    private TableColumn<Trip, PaymentMethod> paymentTypeColumn1;
    private ObservableList<Trip> data1 = FXCollections.observableArrayList(); // YourDataType should be a class representing your data
    private Stage stage;
    private Scene scene;
    private int driverID;

    public void initialize(int driverID) {
        this.driverID = driverID;
        // SETTING PANEL VIEW TRIPS
        // Initialize columns with data properties
        tripIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTripID()).asObject());
        destinationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));
        pickupColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPickupPoint()));
        timeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTripTime()));
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTripPrice()).asObject());
        paymentTypeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPaymentMethod()));
        isFinished.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isFinished()));
        isFinished.setCellFactory(new Callback<TableColumn<Trip, Boolean>, TableCell<Trip, Boolean>>() {
            @Override
            public TableCell<Trip, Boolean> call(TableColumn<Trip, Boolean> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item ? "Yes" : "No");
                        }
                    }
                };
            }
        });

        List<Trip> tripList = Driver.ViewTripHistory(driverID);
        for (Trip t : tripList) {
            // Add sample data (replace this with your actual data loading logic)
            data.add(new Trip(t.getTripID(), t.getDestination(), t.getPickupPoint(), t.getTripTime(), t.getTripPrice(), t.getPaymentMethod(), t.isFinished()));
        }
        // Set data to the table
        tableViewInPanel2.setItems(data);

        // SETTING PANEL AVAILABLE TRIPS
        tripIdColumn1.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTripID()).asObject());
        destinationColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));
        pickupColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPickupPoint()));
        timeColumn1.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTripTime()));
        priceColumn1.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTripPrice()).asObject());
        paymentTypeColumn1.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPaymentMethod()));

        List<Trip> tripList1 = Driver.ViewAvailableTrips();
        for (Trip t : tripList1) {
            // Add sample data (replace this with your actual data loading logic)
            data1.add(new Trip(t.getTripID(), t.getDestination(), t.getPickupPoint(), t.getTripTime(), t.getTripPrice(), t.getPaymentMethod()));
        }
        // Set data to the table
        tableViewInPanel1.setItems(data1);

        tableViewInPanel1.setRowFactory(tv -> {
            TableRow<Trip> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    Trip selectedTrip = row.getItem();
                    try {
                        handleRowClick(selectedTrip);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });
    }

    private void refreshTableView() {
        // Assuming tableView is the reference to your TableView
        tableViewInPanel2.getItems().clear();
        List<Trip> tripList = Driver.ViewTripHistory(driverID);
        // Set data to the table
        tableViewInPanel2.getItems().addAll(tripList);
    }

    public void showPanel1(ActionEvent e) throws IOException
    {
        panel1.setVisible(true);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);
    }
    public void showPanel2(ActionEvent e) throws IOException
    {
        panel2.setVisible(true);
        panel1.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);
    }

    private void handleRowClick(Trip trip) throws SQLException {
        // Your logic when a row is clicked
        System.out.println("Row clicked! Trip ID: " + trip.getTripID());

        ExitDialog.showTripConfirmation(stage, driverID, trip, data1, data, successLabel1);
        refreshTableView();
    }

    @FXML
    private void redirectToLogin(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        scene = new Scene(fxmlLoader.load(), 645, 500);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
