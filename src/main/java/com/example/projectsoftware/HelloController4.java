package com.example.projectsoftware;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.logging.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;

public class HelloController4 {
private static final String UNDER_IMPLEMENTATION = "Under implementation";
private static final Logger logger = Logger.getLogger(HelloController4.class.getName());

    private Connection connection;
    private PreparedStatement checkReservationStatement;
    @FXML
    private Button b500;

    @FXML
    private Button b60;

    @FXML
    private DatePicker datereservation;

    @FXML
    private Label lb7;

    @FXML
    private Label lb8;

    @FXML
    private Label lb9;

    @FXML
    private ImageView serviceimage;

    @FXML
    private ChoiceBox<String > servicetime;

    @FXML
    void cancleservice(javafx.event.ActionEvent event) {
                  logger.info(UNDER_IMPLEMENTATION );


    }

    @FXML
    void clicktimeservicechoice(MouseEvent event) {
                       logger.info(UNDER_IMPLEMENTATION );


    }

    @FXML
    void resser(ActionEvent event) {
                 logger.info(UNDER_IMPLEMENTATION );

    }


    public void initialize() {
        servicetime.getItems().addAll("16:00:00", "18:00:00", "20:00:00");
        
        private static final Logger logger = Logger.getLogger(YourClassName.class.getName());


        try {
    connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", getPasswordFromEnvironment() );
    checkReservationStatement = connection.prepareStatement("SELECT COUNT(*) FROM software.reservations WHERE date = ? AND starttime = ? AND serviceid = ?");
} catch (SQLException e) {
    logger.severe("Error while checking availability: " + e.getMessage());
}

        datereservation.setDayCellFactory(dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                } else {
                    setDisable(false);
                    int reservedCount = getReservedCount(item);
                    if (reservedCount == 3) {
                        setStyle("-fx-background-color: #ff0000;");
                        setOnMouseClicked(event -> showAlert("All time slots are reserved for this day."));
                    } else if (reservedCount > 0) {
                        setStyle("-fx-background-color: #ffff00;");
                    } else {
                        setStyle("-fx-background-color: #00ff00;");
                    }
                }
            }
        });



    }

    private int getReservedCount(LocalDate date) {
       int reservedCount = 0;
    try {
        for (String time : servicetime.getItems()) {
            checkReservationStatement.setDate(1, Date.valueOf(date));
            checkReservationStatement.setTime(2, Time.valueOf(time));
            checkReservationStatement.setInt(3, getHallId());
            checkReservationStatement.addBatch(); // Add the statement to the batch
        }
        int[] counts = checkReservationStatement.executeBatch(); // Execute the batch
        for (int count : counts) {
            if (count > 0) {
                reservedCount++;
            }
        }
    } catch (SQLException e) {
            logger.severe("Error while checking availability: " + e.getMessage());
     
    }
    return reservedCount;
    }

    private int getHallId() {

      String hallName = lb9.getText();
    int hallId = 0;
    try (PreparedStatement statement = connection.prepareStatement("SELECT serviceid FROM software.services WHERE servicename = ?")) {
        statement.setString(1, hallName);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                hallId = resultSet.getInt("serviceid");
            }
        }
    } catch (SQLException e) {
     logger.severe("Error while checking availability: " + e.getMessage());
    }
    return hallId;
    }
    public void choisetiameondate(javafx.scene.input.MouseEvent mouseEvent) {
        LocalDate selectedDate = datereservation.getValue();
        if (selectedDate == null) {
            return;
        }

        servicetime.getItems().clear();

        List<String> availableTimes = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",getPasswordFromEnvironment() );

            checkReservationStatement = connection.prepareStatement("SELECT DISTINCT starttime FROM software.reservations WHERE date = ? AND serviceid = ?");

            checkReservationStatement.setDate(1, Date.valueOf(selectedDate));
            checkReservationStatement.setInt(2, getHallId());


            ResultSet resultSet = checkReservationStatement.executeQuery();

            while (resultSet.next()) {
                availableTimes.add(resultSet.getString("starttime"));
            }

            connection.close();
        } catch (SQLException e) {
        logger.severe("Error while checking availability: " + e.getMessage());
        }

        List<String> allTimes = List.of("16:00:00", "18:00:00", "20:00:00");

        List<String> availableTimesFiltered = allTimes.stream()
                .filter(time -> !availableTimes.contains(time))
                .collect(Collectors.toList());

        servicetime.getItems().addAll(availableTimesFiltered);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
     private static String getPasswordFromEnvironment() {
        String password = System.getenv("1482003");
        if (password == null) {
            throw new IllegalStateException("Database password not found in environment variables.");
        }
        return password;
    }

}
