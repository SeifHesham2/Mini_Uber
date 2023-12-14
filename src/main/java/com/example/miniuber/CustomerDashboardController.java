package com.example.miniuber;

import com.example.miniuber.classes.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CustomerDashboardController {
    @FXML
    private Pane panel1;
    @FXML
    private Pane panel2;
    @FXML
    private Pane panel3;
    @FXML
    private Pane panel4;
    @FXML
    private Pane panel5;
    @FXML
    private Pane panel6;
    // PREVIEW PREVIOUS TRIPS
    @FXML
    private TableView<Trip> tableViewInPanel1;
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
    @FXML
    private TableColumn<Trip, Integer> driverIdColumn;
    private ObservableList<Trip> data = FXCollections.observableArrayList(); // YourDataType should be a class representing your data
    @FXML
    private TextField tripIDField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;
    @FXML
    private Button sendComplaintButton;
    @FXML
    private TextField pickupField;
    @FXML
    private TextField destinationField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<String> paymentMethodCombo;
    @FXML
    private Label errorLabel2;
    @FXML
    private Label successLabel2;
    @FXML
    private Button bookTripButton;
    @FXML
    private TextField tripIDRateField;
    @FXML
    private TextField rateField;
    @FXML
    private Button rateButton;
    @FXML
    private Label errorLabel3;
    @FXML
    private Label successLabel3;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button updateButton;
    @FXML
    private Label errorLabel4;
    @FXML
    private Label successLabel4;
    private Stage stage;
    private Scene scene;
    protected int customerID;

    public void initialize(int customerID) {
        this.customerID = customerID;

        Customer customer = RetrieveFromDatabase.retrieveCustomer(customerID);

        // Set user information in the text fields
        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        emailField.setText(customer.getEmail());
        phoneField.setText(customer.getPhone());
        passwordField.setText(customer.getPassword());

        firstNameField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel4, successLabel4, 270.0, 282.0));
        lastNameField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel4, successLabel4, 270.0, 282.0));
        emailField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel4, successLabel4, 270.0, 282.0));
        passwordField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel4, successLabel4, 270.0, 282.0));
        phoneField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel4, successLabel4, 270.0, 282.0));

        tripIDRateField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel3, successLabel3, 270, 305));
        rateField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel3, successLabel3, 270, 305));

        pickupField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel2, successLabel2, 290, 320));
        destinationField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel2, successLabel2, 290, 320));
        priceField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel2, successLabel2, 290, 320));
        //timeField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel2, successLabel2, 290, 320));
        paymentMethodCombo.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel2, successLabel2, 290, 320));

        descriptionArea.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 300, 320));
        tripIDField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 300, 320));

        // Create a list of payment methods in the combobox
        ObservableList<String> paymentMethods = FXCollections.observableArrayList("Visa", "Paypal", "Cash");
        // Set the items in the ComboBox
        paymentMethodCombo.setItems(paymentMethods);

        // SETTING PANEL PREVIEW PREVIOUS TRIPS
        // Initialize columns with data properties
        tripIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTripID()).asObject());
        destinationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestination()));
        pickupColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPickupPoint()));
        timeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTripTime()));
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTripPrice()).asObject());
        paymentTypeColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPaymentMethod()));
        driverIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDriverID()).asObject());
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

        List<Trip> tripList = Customer.PreviousTripsDetails(customerID);
        for (Trip t : tripList) {
            // Add sample data (replace this with your actual data loading logic)
            data.add(new Trip(t.getTripID(), t.getDriverID(), t.getPickupPoint(), t.getDestination(), t.getTripTime(), t.getTripPrice(), t.getPaymentMethod(), t.isFinished()));
        }
        // Set data to the table
        tableViewInPanel1.setItems(data);
    }

    public void sendComplaint(ActionEvent e) throws SQLException {
        HandlingErrors.hideBothLabels(errorLabel, successLabel, 300, 320);
        if(validateFields())
        {
            if(tripIDField.getText().matches("\\d+"))
            {
                Boolean done = Customer.SendComplaint(descriptionArea.getText(), Integer.parseInt(tripIDField.getText()), customerID, errorLabel);
                if(done)
                    successLabel.setText("Complaint sent successfully.");
            }
            else
                errorLabel.setText("Please enter only numbers in trip ID.");
        }
        else
            errorLabel.setText("Please fill all the data and try again.");
    }

    public void bookTrip(ActionEvent e) throws SQLException
    {
        HandlingErrors.hideBothLabels(errorLabel2, successLabel2, 290, 320);
        if(validateFields2())
        {
            if(validateCriteria2(errorLabel2))
            {
                Boolean done = Customer.RequestTrip(pickupField.getText(), destinationField.getText(), timeField.getText(), Integer.parseInt(priceField.getText()), PaymentFactory.getPaymentMethod(paymentMethodCombo.getValue()), customerID);
                if(done)
                {
                    successLabel2.setText("Trip booked successfully!");
                    refreshTableView();
                }
                else
                    errorLabel2.setText("An error has occurred.");
            }
        }
        else
            errorLabel2.setText("Please fill all the data and try again.");
    }

    private void refreshTableView() {
        // Set data to the table
        // Assuming tableView is the reference to your TableView
        tableViewInPanel1.getItems().clear();
        List<Trip> tripList = Customer.PreviousTripsDetails(customerID);
        // Set data to the table
        tableViewInPanel1.getItems().addAll(tripList);
    }

    public void Rate(ActionEvent e) throws SQLException
    {
        HandlingErrors.hideBothLabels(errorLabel3, successLabel3, 270, 305);
        if(validateFields3())
        {
            if(validateCriteria3(errorLabel3))
            {
                int customerid = RetrieveFromDatabase.retrieveTrip(Integer.parseInt(tripIDRateField.getText()));
                if(customerid != customerID)
                {
                    System.out.println("You don't have this trip in your account.");
                    errorLabel3.setLayoutX(270.0);
                    errorLabel3.setText("You don't have this trip in your account.");
                }
                else
                {
                    Boolean done = Customer.RateDriver(Integer.parseInt(tripIDRateField.getText()), Integer.parseInt(rateField.getText()));
                    if(done)
                        successLabel3.setText("Rate added successfully!");
                    else
                    {
                        errorLabel3.setLayoutX(310.0);
                        errorLabel3.setText("An error has occurred.");
                    }
                }
            }
        }
        else
            errorLabel3.setText("Please fill all the data and try again.");
    }

    public void updateInfo(ActionEvent e) throws SQLException
    {
        HandlingErrors.hideBothLabels(errorLabel4, successLabel4, 270.0, 282.0);
        if(validateFields4())
        {
            errorLabel4.setLayoutX(290);
            if(HandlingErrors.validateEmailPhoneCriteria(phoneField, emailField, errorLabel4))
            {
                Customer customer = RetrieveFromDatabase.retrieveCustomer(customerID);
                Boolean done = Customer.updateInfo(firstNameField.getText(), lastNameField.getText(), emailField.getText(), phoneField.getText(), passwordField.getText(), customer);
                if(done)
                    successLabel4.setText("Customer updated successfully!");
                else
                {
                    errorLabel4.setLayoutX(310.0);
                    errorLabel4.setText("An error has occurred.");
                }
            }
        }
        else
            errorLabel4.setText("Please fill all the data and try again.");
    }

    public void handleKeyPress(javafx.scene.input.KeyEvent event) throws SQLException, IOException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            sendComplaint(new ActionEvent(sendComplaintButton, null));
        }
    }

    public void handleKeyPress2(javafx.scene.input.KeyEvent event) throws SQLException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            bookTrip(new ActionEvent(bookTripButton, null));
        }
    }

    public void handleKeyPress3(javafx.scene.input.KeyEvent event) throws SQLException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            Rate(new ActionEvent(rateButton, null));
        }
    }

    public void handleKeyPress4(javafx.scene.input.KeyEvent event) throws SQLException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            updateInfo(new ActionEvent(updateButton, null));
        }
    }

    private boolean validateFields() {
        // Check if all required fields are filled in
        return !descriptionArea.getText().trim().isEmpty()
                && !tripIDField.getText().trim().isEmpty();
    }

    private boolean validateFields2() {
        // Check if all required fields are filled in
        boolean isPaymentMethodSelected = paymentMethodCombo.getValue() != null;

        return !pickupField.getText().trim().isEmpty()
                && !destinationField.getText().trim().isEmpty()
                && !timeField.getText().trim().isEmpty()
                && !priceField.getText().trim().isEmpty()
                && isPaymentMethodSelected;
    }

    private boolean validateFields4() {
        // Check if all required fields are filled in
        return !firstNameField.getText().trim().isEmpty()
                && !lastNameField.getText().trim().isEmpty()
                && !emailField.getText().trim().isEmpty()
                && !phoneField.getText().trim().isEmpty()
                && !passwordField.getText().trim().isEmpty();
    }

    private boolean validateFields3() {
        // Check if all required fields are filled in
        return !tripIDRateField.getText().trim().isEmpty()
                && !rateField.getText().trim().isEmpty();
    }

    private boolean validateCriteria2(Label errorLabel2)
    {
        if(priceField.getText().matches("\\d+\\.?\\d*"))
        {
            if(isValidDateTime(timeField.getText()))
                return true;
            else
            {
                errorLabel2.setLayoutX(310);
                errorLabel2.setText("Please enter the time in given\nformat: yyyy-MM-dd HH:mm:ss");
                return false;
            }
        }
        else
        {
            errorLabel2.setLayoutX(320);
            errorLabel2.setText("Please enter a valid price.");
            return false;
        }
    }

    private boolean validateCriteria3(Label errorLabel3)
    {
        if(tripIDRateField.getText().matches("\\d+"))
        {
            if(rateField.getText().matches("\\d+"))
            {
                if(Integer.parseInt(rateField.getText()) >= 1 && Integer.parseInt(rateField.getText()) <= 5)
                    return true;
                else
                {
                    errorLabel3.setLayoutX(270.0);
                    errorLabel3.setText("Please enter a rate number from 1 to 5.");
                    return false;
                }
            }
            else
            {
                errorLabel3.setLayoutX(270.0);
                errorLabel3.setText("Please enter a rate number from 1 to 5.");
                return false;
            }
        }
        else
        {
            errorLabel3.setLayoutX(290);
            errorLabel3.setText("Please enter a valid trip ID.");
            return false;
        }
    }

    private boolean isValidDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void showPanel1(ActionEvent e) throws IOException
    {
        panel1.setVisible(true);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);
        panel5.setVisible(false);
        panel6.setVisible(false);
    }
    public void showPanel2(ActionEvent e) throws IOException
    {
        panel1.setVisible(false);
        panel2.setVisible(true);
        panel3.setVisible(false);
        panel4.setVisible(false);
        panel5.setVisible(false);
        panel6.setVisible(false);
    }
    public void showPanel3(ActionEvent e) throws IOException
    {
        panel1.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(true);
        panel4.setVisible(false);
        panel5.setVisible(false);
        panel6.setVisible(false);
    }

    public void showPanel4(ActionEvent e) throws IOException
    {
        panel1.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(true);
        panel5.setVisible(false);
        panel6.setVisible(false);
    }

    public void showPanel5(ActionEvent e) throws IOException
    {
        panel1.setVisible(false);
        panel2.setVisible(false);
        panel3.setVisible(false);
        panel4.setVisible(false);
        panel5.setVisible(true);
        panel6.setVisible(false);
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