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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeDashboardController {
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
    @FXML
    private TextField plateNumberField;
    @FXML
    private TextField carTypeField;
    @FXML
    private TextField carColorField;
    @FXML
    private TextField numberOfSeatsField;
    @FXML
    private TextField carModelField;
    @FXML
    private Label errorLabel;
    @FXML
    private Label errorLabel2;
    @FXML
    private Label successLabel;
    @FXML
    private Label successLabel2;
    @FXML
    private Label successLabel3;
    @FXML
    private Label successLabel4;
    @FXML
    private Label successLabel5;
    @FXML
    private Button addCarButton;
    @FXML
    private Button registerButton;
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
    private TextField complaintIdField;
    @FXML
    private TextField carIdField2;
    @FXML
    private TextField driverIdField2;
    @FXML
    private CheckBox changeCarCheckBox;
    @FXML
    private Label errorLabel3;
    @FXML
    private Label errorLabel4;
    @FXML
    private Label errorLabel5;
    @FXML
    private Button addCarToDriverButton;
    @FXML
    private Button updateButton;
    // VIEW COMPLAINTS
    @FXML
    private TableView<Complaints> tableViewInPanel4;
    @FXML
    private TableColumn<Complaints, Integer> complaintIdColumn;
    @FXML
    private TableColumn<Complaints, Integer> tripIdColumn;
    @FXML
    private TableColumn<Complaints, String> descriptionColumn;
    @FXML
    private TableColumn<Complaints, Boolean> openedColumn;
    private ObservableList<Complaints> data = FXCollections.observableArrayList(); // YourDataType should be a class representing your data

    // ASSIGN CAR TO DRIVER
    @FXML
    private TableView<Car> tableViewInPanel5;
    @FXML
    private TableColumn<Car, Integer> carIdTableField;
    @FXML
    private TableColumn<Car, String> plateNumberTableField;
    @FXML
    private TableColumn<Car, String> carTypeTableField;
    @FXML
    private TableColumn<Car, String> carColorTableField;
    @FXML
    private TableColumn<Car, String> carModelTableField;
    @FXML
    private TableColumn<Car, Integer> numberOfSeatsTableField;
    private ObservableList<Car> data2 = FXCollections.observableArrayList(); // YourDataType should be a class representing your data
    private Stage stage;
    private Scene scene;
    protected int employeeID;

    public void initialize(int employeeID) {
        this.employeeID = employeeID;
        carIdField2.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel4, successLabel4, 265, 265));
        driverIdField2.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel4, successLabel4, 265, 265));
        changeCarCheckBox.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel4, successLabel4, 265, 265));

        firstNameField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel3, successLabel3, 250, 289));
        lastNameField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel3, successLabel3, 250, 289));
        emailField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel3, successLabel3, 250, 289));
        phoneField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel3, successLabel3, 250, 289));
        passwordField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel3, successLabel3, 250, 289));

        plateNumberField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 250, 295));
        carModelField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 250, 295));
        carColorField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 250, 295));
        carTypeField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 250, 295));
        numberOfSeatsField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 250, 295));

        complaintIdField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel2, successLabel2, 270, 265));

        // SETTING PANEL VIEW COMPLAINTS
        // Initialize columns with data properties
        complaintIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getComplaintID()).asObject());
        tripIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTripID()).asObject());
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        openedColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isOpened()));
        openedColumn.setCellFactory(new Callback<TableColumn<Complaints, Boolean>, TableCell<Complaints, Boolean>>() {
            @Override
            public TableCell<Complaints, Boolean> call(TableColumn<Complaints, Boolean> param) {
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

        List<Complaints> complaintList = Employee.viewComplaints();
        for (Complaints c : complaintList) {
            // Add sample data (replace this with your actual data loading logic)
            data.add(new Complaints(c.getComplaintID(), c.getTripID(), c.getDescription(), c.isOpened()));
        }
        // Set data to the table
        tableViewInPanel4.setItems(data);

        // ASSIGN OR CHANGE CAR
        // Initialize columns with data properties
        carIdTableField.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCarID()).asObject());
        numberOfSeatsTableField.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumberOfSeats()).asObject());
        plateNumberTableField.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlateNumber()));
        carTypeTableField.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCarType()));
        carColorTableField.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCarColor()));
        carModelTableField.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCarModel()));

        HashMap<Integer, Car> carsList = Employee.CarsList();
        for (Car c : carsList.values()) {
            // Add sample data (replace this with your actual data loading logic)
            data2.add(new Car(c.getCarID(), c.getNumberOfSeats(), c.getPlateNumber(), c.getCarType(), c.getCarColor(), c.getCarModel()));
        }
        tableViewInPanel5.setItems(data2);
    }

    public void addCarToDriver(ActionEvent e) throws SQLException
    {
        HandlingErrors.hideBothLabels(errorLabel4, successLabel4, 265, 265);
        if(!carIdField2.getText().matches("\\d+") || !driverIdField2.getText().matches("\\d+"))
        {
            errorLabel4.setLayoutX(275.0);
            errorLabel4.setText("Please enter only numbers.");
            return;
        }
        boolean valid = Employee.AssignOrChangeCarToDriver(Integer.parseInt(driverIdField2.getText()), Integer.parseInt(carIdField2.getText()), changeCarCheckBox.isSelected(), errorLabel4);
        if(valid)
        {
            successLabel4.setText("Car assigned to driver successfully.");
            refreshTableView2();
        }
    }

    public void updateStatus(ActionEvent e) throws SQLException
    {
        HandlingErrors.hideBothLabels(errorLabel2, successLabel2, 270, 265);
        if(complaintIdField.getText().isEmpty() || !complaintIdField.getText().matches("\\d+"))
        {
            errorLabel2.setLayoutX(291.0);
            errorLabel2.setText("Please enter a complaint ID.");
            return;
        }
        Boolean valid = Employee.updateComplaint(Integer.parseInt(complaintIdField.getText()), errorLabel2);
        if(valid)
        {
            successLabel2.setText("Complaint status updated successfully.");
            refreshTableView();
        }
    }

    public void registerDriver(ActionEvent e) throws SQLException
    {
        HandlingErrors.hideBothLabels(errorLabel3, successLabel3, 250, 289);
        boolean done = false;
        if(HandlingErrors.validateEmailPhoneCriteria(phoneField, emailField, errorLabel3))
        {
            if(validateFields2())
            {
                done = Employee.RegisterDriver(firstNameField.getText(), lastNameField.getText(), emailField.getText(), phoneField.getText(), passwordField.getText());
                if(done)
                {
                    successLabel3.setText("Driver added successfully.");
                    return;
                }
            }
            errorLabel3.setLayoutX(265);
            errorLabel3.setText("Please fill all the data and try again.");
        }
    }

    public void assignCarForAll(ActionEvent e) throws SQLException
    {
        HandlingErrors.hideBothLabels(errorLabel5, successLabel5, 262, 262);
        Employee.AssignCarToAllDrivers(errorLabel5, successLabel5);
    }

    private void refreshTableView() {
        // Assuming tableView is the reference to your TableView
        tableViewInPanel4.getItems().clear();
        List<Complaints> updatedComplaintsList = Employee.viewComplaints();// Retrieve the updated list of complaints from the database
        tableViewInPanel4.getItems().addAll(updatedComplaintsList);
    }

    private void refreshTableView2() {
        // Assuming tableView is the reference to your TableView
        tableViewInPanel5.getItems().clear();
        HashMap<Integer, Car> carsList = Employee.CarsList(); // Retrieve the updated list of available cars from the database
        List<Car> carList = new ArrayList<>(carsList.values());
        tableViewInPanel5.getItems().addAll(carList);
    }
    private void refreshTableView3() {
        // Assuming tableView is the reference to your TableView
        tableViewInPanel5.getItems().clear();
        HashMap<Integer, Car> carsList = Employee.CarsList(); // Retrieve the updated list of available cars from the database
        List<Car> carList = new ArrayList<>(carsList.values());
        tableViewInPanel5.getItems().addAll(carList);
    }

    public void addCar(ActionEvent e) throws SQLException {
        HandlingErrors.hideBothLabels(errorLabel, successLabel, 250, 295);
        if(validateCriteria())
        {
            if(validateFields())
            {
                Car car = new Car(Integer.parseInt(numberOfSeatsField.getText()), plateNumberField.getText(), carTypeField.getText(), carColorField.getText(), carModelField.getText());
                Boolean done = Employee.AddCar(car);
                if(done)
                {
                    refreshTableView3();
                    successLabel.setText("Car added successfully.");
                    return;
                }
            }
            errorLabel.setLayoutX(250.0);
            errorLabel.setText("There is an error inserting this car, try again.");
        }
    }

    private boolean validateFields() {
        // Check if all required fields are filled in
        return !plateNumberField.getText().trim().isEmpty()
                && !carTypeField.getText().trim().isEmpty()
                && !carColorField.getText().trim().isEmpty()
                && !carModelField.getText().trim().isEmpty()
                && !numberOfSeatsField.getText().trim().isEmpty();
    }

    private boolean validateCriteria()
    {
        if(!(numberOfSeatsField.getText().matches("\\d+")) || (Integer.parseInt(numberOfSeatsField.getText()) < 1 || Integer.parseInt(numberOfSeatsField.getText()) > 7))
        {
            errorLabel.setLayoutX(279.0);
            errorLabel.setText("Please enter a valid seats number.");
            return false;
        }
        return true;
    }

    private boolean validateFields2() {
        // Check if all required fields are filled in
        return !firstNameField.getText().trim().isEmpty()
                && !lastNameField.getText().trim().isEmpty()
                && !emailField.getText().trim().isEmpty()
                && !phoneField.getText().trim().isEmpty()
                && !passwordField.getText().trim().isEmpty();
    }

    public void handleKeyPress(javafx.scene.input.KeyEvent event) throws SQLException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            addCar(new ActionEvent(addCarButton, null));
        }
    }

    public void handleKeyPress2(javafx.scene.input.KeyEvent event) throws SQLException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            updateStatus(new ActionEvent(updateButton, null));
        }
    }

    public void handleKeyPress3(javafx.scene.input.KeyEvent event) throws SQLException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            registerDriver(new ActionEvent(registerButton, null));
        }
    }

    public void handleKeyPress4(javafx.scene.input.KeyEvent event) throws SQLException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            addCarToDriver(new ActionEvent(addCarToDriverButton, null));
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
