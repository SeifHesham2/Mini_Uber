package com.example.miniuber;

import com.example.miniuber.classes.Register;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField dummyTextField;
    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;
    @FXML
    private Button registerButton;
    private Scene scene;
    private Stage stage;

    public void initialize() {
        // Set focus to the dummyTextField
        dummyTextField.requestFocus();

        firstNameField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
        lastNameField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
        emailField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
        phoneField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
        passwordField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
    }
    public void Register(ActionEvent e) throws SQLException, InterruptedException, IOException {
        HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63);
        boolean done = false;
        if(HandlingErrors.validateEmailPhoneCriteria(phoneField, emailField, errorLabel))
        {
            if (validateFields())
            {
                done = Register.register(firstNameField.getText(), lastNameField.getText(), emailField.getText(), phoneField.getText(), passwordField.getText());
                if(done)
                {
                    updateLabelWithDelay(successLabel, () -> {
                        try {
                            redirectToLogin(e);  // Move the scene transition inside the callback
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                }
                else
                {
                    errorLabel.setLayoutX(103.0);
                    errorLabel.setText("Email already exists.");
                }
            }
            else
                errorLabel.setText("Please fill all the data and try again.");
        }
    }

    private void updateLabelWithDelay(Label label, Runnable onComplete) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> label.setText("Registered successfully, you will be\nredirected to the login page in 3")),
                new KeyFrame(Duration.seconds(1), event -> label.setText("Registered successfully, you will be\nredirected to the login page in 2")),
                new KeyFrame(Duration.seconds(2), event -> label.setText("Registered successfully, you will be\nredirected to the login page in 1")),
                new KeyFrame(Duration.seconds(3), event -> {
                    label.setText("Redirecting...");
                    onComplete.run();  // Execute the callback
                })
        );

        timeline.setOnFinished(event -> {
            // Add any logic to be executed after the label is shown for 3 seconds
        });

        timeline.play();
    }

    private boolean validateFields() {
        // Check if all required fields are filled in
        return !firstNameField.getText().trim().isEmpty()
                && !lastNameField.getText().trim().isEmpty()
                && !emailField.getText().trim().isEmpty()
                && !phoneField.getText().trim().isEmpty()
                && !passwordField.getText().trim().isEmpty();
    }

    public void handleKeyPress(javafx.scene.input.KeyEvent event) throws IOException, SQLException, InterruptedException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            Register(new ActionEvent(registerButton, null));
        }
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
