package com.example.miniuber;

import com.example.miniuber.classes.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ExitDialog {
    public static void showExitConfirmation(Window owner) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(owner);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit!");
        alert.setContentText("Are you sure you want to continue?");

        // Set the custom icon for the dialog
        Image icon = new Image("file:F:/ASU/7TH SEMESTER/Software Design Patterns/Project/Mini_Uber/src/main/resources/com/example/images/uber-logo-png-0.png");
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(icon);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("You successfully logged out!");
            ((Stage) owner).close();
            Platform.exit();  // Terminate the JavaFX application
        }
    }
    public static void showTripConfirmation(Window owner, int driverID, Trip trip, ObservableList<Trip> data1, ObservableList<Trip> data2, Label successLabel1) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTripTime = trip.getTripTime().format(formatter);

        // Create Text nodes for bold parts
        Text tripIdText = createBoldText("Trip ID: ");
        Text destinationText = createBoldText("Destination: ");
        Text pickupText = createBoldText("Pickup: ");
        Text priceText = createBoldText("Price: ");
        Text tripTimeText = createBoldText("Trip Time: ");
        Text paymentMethodText = createBoldText("Payment Method: ");

        // Create a VBox to hold the Text nodes
        VBox vbox = new VBox(
                tripIdText,
                new Text("" + trip.getTripID()),
                destinationText,
                new Text("" + trip.getDestination()),
                pickupText,
                new Text("" + trip.getPickupPoint()),
                priceText,
                new Text("" + String.format("%.2f", trip.getTripPrice())),  // Format price with two decimal places
                tripTimeText,
                new Text("" + formattedTripTime),
                paymentMethodText,
                new Text("" + trip.getType())
        );

        // Show a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // Set the custom icon for the dialog
        Image icon = new Image("file:F:/ASU/7TH SEMESTER/Software Design Patterns/Project/Mini_Uber/src/main/resources/com/example/images/uber-logo-png-0.png");
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(icon);

        alert.setTitle("Trip Details Confirmation");
        alert.setHeaderText("Are you sure you want to assign this trip to yourself?");
        alert.getDialogPane().setContent(vbox);

        // Custom buttons for confirmation alert
        ButtonType buttonTypeOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeCancel);

        // Show and wait for user's response
        alert.showAndWait().ifPresent(result -> {
            if (result == buttonTypeOk) { // Use the buttonTypeOk reference
                try {
                    Driver driver = RetrieveFromDatabase.retrieveDriver(driverID);
                    Driver.AcceptTrip(driver, trip.getTripID());
                    data1.remove(trip);
                    data2.add(new Trip(trip.getTripID(), trip.getDestination(), trip.getPickupPoint(), trip.getTripTime(), trip.getTripPrice(), trip.getType(), trip.isFinished()));
                    successLabel1.setText("Trip assigned successfully.");
                    UpdateDataBase.UpdateDriverNumberOfTrips(driver.getId(), driver.getNumberOfTrips());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Operation canceled");
                successLabel1.setText("");  // Set an empty string or set to your default error message
            }
        });
    }
    private static Text createBoldText(String text) {
        Text boldText = new Text(text);
        boldText.setStyle("-fx-font-weight: bold;");
        return boldText;
    }
}
