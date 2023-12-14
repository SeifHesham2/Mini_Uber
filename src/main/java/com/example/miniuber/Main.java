package com.example.miniuber;

import com.example.miniuber.classes.Employee;
import com.example.miniuber.classes.RetrieveFromDatabase;
import com.example.miniuber.classes.Trip;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public void Exit(Stage stage) throws IOException
    {
        ExitDialog.showExitConfirmation(stage);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Employee.AutomaticCarAssignment();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 645, 500);

        Image icon = new Image("file:F:/ASU/7TH SEMESTER/Software Design Patterns/MiniUber/src/main/resources/com/example/images/img.png");
        stage.getIcons().add(icon);

        stage.setTitle("Uber");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            try {
                event.consume();
                Exit(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}