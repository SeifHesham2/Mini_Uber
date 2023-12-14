package com.example.miniuber;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HandlingErrors {
    public static void hideErrorLabel(Label errorLabel, double errorLayout) {
        errorLabel.setText("");  // Set an empty string or set to your default error message
    }
    public static void hideBothLabels(Label errorLabel, Label successLabel, double errorLayout, double successLayout)
    {
        errorLabel.setLayoutX(errorLayout);
        successLabel.setLayoutX(successLayout);
        errorLabel.setText("");  // Set an empty string or set to your default error message
        successLabel.setText(""); // Set an empty string or set to your default error message
    }
    public static boolean validateEmailPhoneCriteria(TextField phoneField, TextField emailField, Label errorLabel)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if(phoneField.getText().length() == 11 && phoneField.getText().matches("\\d+") && emailField.getText().matches(emailRegex))
            return true;
        else if(phoneField.getText().length() != 11 || !(phoneField.getText().matches("\\d+")))
        {
            errorLabel.setText("Please enter a 11 digits phone number.");
            return false;
        }
        else if(!emailField.getText().matches(emailRegex))
        {
            errorLabel.setText("Please enter a valid email.");
            return false;
        }
        return false;
    }
}
