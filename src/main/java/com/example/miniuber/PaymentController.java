package com.example.miniuber;

import com.example.miniuber.classes.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField CVVField;
    @FXML
    private TextField expirationDateField;
    @FXML
    private TextField dummyTextField;
    @FXML
    private RadioButton paypalRadio;
    @FXML
    private RadioButton visaRadio;
    @FXML
    private Label errorLabel;
    @FXML
    private Label successLabel;
    @FXML
    private Button submitPaymentButton;
    private Stage stage;
    private Scene scene;
    protected int customerID;
    public void initialize(int customerID, Stage stage) {
        this.customerID = customerID;
        this.stage = stage;

        dummyTextField.requestFocus();

        firstNameField.setOnMousePressed(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
        lastNameField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
        emailField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
        cardNumberField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
        CVVField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
        expirationDateField.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
        visaRadio.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
        paypalRadio.setOnMouseClicked(event -> HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63));
    }
    public void register(ActionEvent e) throws SQLException {
        HandlingErrors.hideBothLabels(errorLabel, successLabel, 63, 63);

        TextField phone = new TextField();
        phone.setText("12345678900");
        if(validateFields())
        {
            errorLabel.setLayoutX(90);
            if(HandlingErrors.validateEmailPhoneCriteria(phone, emailField, errorLabel) && validateCVVAndCardNumber())
            {
                YearMonth yearMonth = parseYearMonth(expirationDateField.getText());
                if(yearMonth != null)
                {
                    System.out.println("Invalid input format");
                    errorLabel.setLayoutX(55);
                    errorLabel.setText("Please enter a valid MM/YY format.");
                }
                else if (isValidDate(expirationDateField))
                {
                    System.out.println("Parsed YearMonth: " + yearMonth);

                    PaymentStrategy payment = null;
                    if(paypalRadio.isSelected())
                        payment = new Paypal(cardNumberField.getText(), CVVField.getText(), yearMonth, customerID);
                    else if(visaRadio.isSelected())
                        payment = new Visa(cardNumberField.getText(), CVVField.getText(), yearMonth, customerID);

                    Boolean done = InsertToDatabase.InsertPayment(payment);
                    done = true;
                    if(done)
                    {
                        dummyTextField.requestFocus();
                        updateLabelWithDelay(successLabel, () -> {
                        });
                    }
                    else
                    {
                        errorLabel.setLayoutX(90);
                        errorLabel.setText("An error has occurred.");
                    }
                }
            }
        }
        else
            errorLabel.setText("Please fill all the data and try again.");
    }

    private YearMonth parseYearMonth(String input) {
        try {
            // Split the input into month and year parts
            String[] parts = input.split("/");

            // Validate month (from 1 to 12)
            int month = Integer.parseInt(parts[0]);
            if (month < 1 || month > 12) {
                return null;
            }

            // Validate year (from 0 to 100)
            int year = Integer.parseInt(parts[1]);
            if (year < 0 || year > 100) {
                return null;
            }

            // Assume the year is in the range 2000-2099 for YY format
            if (year < 100) {
                year += 2000;
            }

            // Create a YearMonth instance
            return YearMonth.of(year, month);
        } catch (Exception e) {
            // Handle parsing errors
            return null;
        }
    }

    private boolean validateFields() {
        // Check if all required fields are filled in
        boolean fieldsFilled = !firstNameField.getText().trim().isEmpty()
                && !lastNameField.getText().trim().isEmpty()
                && !emailField.getText().trim().isEmpty()
                && !cardNumberField.getText().trim().isEmpty()
                && !CVVField.getText().trim().isEmpty()
                && !expirationDateField.getText().trim().isEmpty();

        // Check if at least one radio button is selected
        boolean radioButtonSelected = paypalRadio.isSelected() || visaRadio.isSelected();

        // Return true if all required fields are filled and at least one radio button is selected
        return fieldsFilled && radioButtonSelected;
    }

    private void updateLabelWithDelay(Label label, Runnable onComplete) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> label.setText("Payment added successfully, you will be\nredirected back in 3")),
                new KeyFrame(Duration.seconds(1), event -> label.setText("Payment added successfully, you will be\nredirected back in 2")),
                new KeyFrame(Duration.seconds(2), event -> label.setText("Payment added successfully, you will be\nredirected back in 1")),
                new KeyFrame(Duration.seconds(3), event -> {
                    label.setText("Redirecting...");
                    onComplete.run();  // Execute the callback
                })
        );

        timeline.setOnFinished(event -> {
            onComplete.run();
            submitPaymentButton.getScene().getWindow().hide();
        });

        timeline.play();
    }

    public void handleKeyPress(javafx.scene.input.KeyEvent event) throws IOException, SQLException, InterruptedException {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger loginButton action
            register(new ActionEvent(submitPaymentButton, null));
        }
    }

    public boolean isValidDate(TextField expirationDate) {
        // Define the regex pattern for MM/YY format
        String regex = "^(0[1-9]|1[0-2])/(\\d{2})$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(expirationDate.getText());

        if(matcher.matches())
        {
            YearMonth currentYearMonth = YearMonth.now();
            YearMonth yearMonth = parseYearMonth(expirationDate.getText());
            if(yearMonth.isAfter(currentYearMonth) || yearMonth.equals(currentYearMonth))
                return true;
            else
            {
                System.out.println("This card has already been expired.");
                errorLabel.setLayoutX(55);
                errorLabel.setText("This card has already been expired.");
                return false;
            }
        }
        else
        {
            System.out.println("Invalid input format");
            errorLabel.setLayoutX(55);
            errorLabel.setText("Please enter a valid MM/YY format.");
            return false;
        }
    }

    private boolean validateCVVAndCardNumber() {
        // Validate CVV (3 digits)
        if (!CVVField.getText().replaceAll("\\s+","").matches("\\d{3}")) {
            errorLabel.setText("CVV must be 3 digits");
            return false;
        }

        // Validate card number (16 digits)
        if (!cardNumberField.getText().replaceAll("\\s+","").matches("\\d{16}")) {
            errorLabel.setLayoutX(58);
            errorLabel.setText("Card number must be 16 digits");
            return false;
        }

        return true;
    }

    public void addCardNumberFormatListener() {
        cardNumberField.setOnKeyTyped(event -> {
            int caretPosition = cardNumberField.getCaretPosition();
            char typedChar = event.getCharacter().charAt(0);

            // Check if the typed character is the backspace key
            if (typedChar == '\b') {
                // Backspace was pressed, handle accordingly (e.g., allow deletion)
                return;
            }

            // Remove any non-digit characters from the new value
            String cleanedValue = cardNumberField.getText().replaceAll("\\D", "");

            // Check if the length exceeds 16 digits
            if (cleanedValue.length() > 16) {
                // If so, truncate the value to 16 digits
                cleanedValue = cleanedValue.substring(0, 16);
            }

            // Format the card number with spaces after every 4 digits
            StringBuilder formattedValue = new StringBuilder();
            int digitCount = 0;
            for (int i = 0; i < cleanedValue.length(); i++) {
                if (digitCount > 0 && digitCount % 4 == 0) {
                    formattedValue.append(" ");
                    digitCount = 0; // reset digit count after adding a space
                }
                formattedValue.append(cleanedValue.charAt(i));
                digitCount++;
            }

            // Update the text field with the formatted value
            cardNumberField.setText(formattedValue.toString());

            // Set the caret position back to its original value
            cardNumberField.positionCaret(caretPosition + 1); // Increment by 1 to account for the typed character
        });
    }


    public void addCVVFormatListener() {
        CVVField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Remove any non-digit characters from the new value
            String cleanedValue = newValue.replaceAll("\\D", "");

            // Check if the length exceeds 3 digits
            if (cleanedValue.length() > 3) {
                // If so, truncate the value to 3 digits
                cleanedValue = cleanedValue.substring(0, 3);
            }

            // Update the text field with the cleaned value
            CVVField.setText(cleanedValue);
        });
    }
}
