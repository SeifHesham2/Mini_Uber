package com.example.miniuber;

import com.example.miniuber.classes.RetrieveFromDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ForgotPasswordController {
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField dummyTextField;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button retrieveButton;
    @FXML
    private Label errorLabel;
    private Stage stage;
    private Scene scene;

    public void initialize() {
        // Set focus to the dummyTextField
        dummyTextField.requestFocus();

        emailField.setOnMouseClicked(event -> HandlingErrors.hideErrorLabel(errorLabel, 70));
        phoneField.setOnMouseClicked(event -> HandlingErrors.hideErrorLabel(errorLabel, 70));
    }

    public void retrievePassword(ActionEvent e)
    {
        HandlingErrors.hideErrorLabel(errorLabel, 70);
        if(validateFields())
        {
            errorLabel.setLayoutX(80);
            if(HandlingErrors.validateEmailPhoneCriteria(phoneField, emailField, errorLabel))
            {
                String password = RetrieveFromDatabase.retrieveForgotCustomer(emailField.getText(), phoneField.getText());
                if(password == null)
                    passwordLabel.setText("No user found with the given credentials.");
                else
                    passwordLabel.setText("Password: " + password);
            }
        }
        else
        {
            errorLabel.setLayoutX(70.0);
            errorLabel.setText("Please fill all the data and try again.");
        }
    }

    public void handleKeyPress(javafx.scene.input.KeyEvent event) throws IOException, SQLException, InterruptedException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            retrievePassword(new ActionEvent(retrieveButton, null));
        }
    }

    private boolean validateFields() {
        // Check if all required fields are filled in
        return !emailField.getText().trim().isEmpty()
                && !phoneField.getText().trim().isEmpty();
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
