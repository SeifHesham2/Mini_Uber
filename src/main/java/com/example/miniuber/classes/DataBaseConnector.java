package com.example.miniuber.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    private Connection connection;
    private static DataBaseConnector instance= null;

    public static DataBaseConnector getInstance(){
        if(instance==null){
            instance = new DataBaseConnector();
        }
        return instance;
    }


    public Connection connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MiniUber", "root", "123");
            System.out.println("Connected to the database");
            return connection;
        } catch (SQLException e) {
            System.out.println("Unable to connect to the database");
            e.printStackTrace();
            return null;
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection");
            e.printStackTrace();
        }
    }
}
