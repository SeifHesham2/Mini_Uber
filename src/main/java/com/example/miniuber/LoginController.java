package com.example.miniuber;


import com.example.miniuber.classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

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

    public void Login(ActionEvent e) throws IOException, SQLException {
        HandlingErrors.hideErrorLabel(errorLabel, 46);
        Person person = LoginProxy.PersonLogin(emailField.getText(), passwordField.getText());

        if(person instanceof Driver )
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("driverDashboard.fxml"));
            scene = new Scene(fxmlLoader.load(), 988, 576);

            DriverDashboardController driverDashboardController = fxmlLoader.getController();
            driverDashboardController.initialize(person.getId());

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else if(person instanceof Customer)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("customerDashboard.fxml"));
            scene = new Scene(fxmlLoader.load(), 988, 576);

            CustomerDashboardController customerDashboardController = fxmlLoader.getController();
            customerDashboardController.initialize(person.getId());

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else if(person instanceof Employee)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("employeeDashboard.fxml"));
            scene = new Scene(fxmlLoader.load(), 988, 576);

            EmployeeDashboardController employeeDashboardController = fxmlLoader.getController();
            employeeDashboardController.initialize(person.getId());

            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            errorLabel.setText("Invalid credentials. Please try again.");
        }
    }

    public void handleKeyPress(javafx.scene.input.KeyEvent event) throws IOException, SQLException {
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