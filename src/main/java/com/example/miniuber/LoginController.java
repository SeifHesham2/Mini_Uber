package com.example.miniuber;


import com.example.miniuber.classes.Customer;
import com.example.miniuber.classes.Driver;
import com.example.miniuber.classes.Employee;
import com.example.miniuber.classes.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label errorLabel;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    private Scene scene;
    private Stage stage;

    public void initialize() {
        // Add event handlers for text fields
        emailField.setOnMouseClicked(event -> HandlingErrors.hideErrorLabel(errorLabel, 46));
        passwordField.setOnMouseClicked(event -> HandlingErrors.hideErrorLabel(errorLabel, 46));
    }

    public void Login(ActionEvent e) throws IOException {
        HandlingErrors.hideErrorLabel(errorLabel, 46);
        Driver driver = Login.DriverLogin(emailField.getText(), passwordField.getText());
        Customer customer = Login.CustomerLogin(emailField.getText(), passwordField.getText());
        Employee employee = Login.EmployeeLogin(emailField.getText(), passwordField.getText());

        if(driver != null)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("driverDashboard.fxml"));
            scene = new Scene(fxmlLoader.load(), 988, 576);

            DriverDashboardController driverDashboardController = fxmlLoader.getController();
            driverDashboardController.initialize(driver.getId());

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else if(customer != null)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("customerDashboard.fxml"));
            scene = new Scene(fxmlLoader.load(), 988, 576);

            CustomerDashboardController customerDashboardController = fxmlLoader.getController();
            customerDashboardController.initialize(customer.getId());

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else if(employee != null)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("employeeDashboard.fxml"));
            scene = new Scene(fxmlLoader.load(), 988, 576);

            EmployeeDashboardController employeeDashboardController = fxmlLoader.getController();
            employeeDashboardController.initialize(employee.getId());

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            errorLabel.setText("Invalid credentials. Please try again.");
        }
    }

    public void handleKeyPress(javafx.scene.input.KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            Login(new ActionEvent(loginButton, null));
        }
    }

    @FXML
    private void redirectToRegister(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Register.fxml"));
        scene = new Scene(fxmlLoader.load(), 645, 500);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void redirectToForgotPassword(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("forgotPassword.fxml"));
        scene = new Scene(fxmlLoader.load(), 645, 500);

        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}