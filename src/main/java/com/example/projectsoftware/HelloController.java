package com.example.projectsoftware;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.DatePicker;
import javafx.scene.control.DateCell;
import javafx.util.Callback;

import java.io.*;

import javafx.scene.layout.GridPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.io.IOException;

import java.time.LocalTime;
import java.time.format.FormatStyle;
import java.util.stream.Collectors;

import javafx.util.converter.LocalTimeStringConverter;

import javax.imageio.ImageIO;
import javax.swing.*;

import javafx.embed.swing.SwingFXUtils;




public class HelloController {

   private static Logger logger = Logger.getLogger(com.example.projectsoftware.HelloController.class.getName());
    static String window = " An error occurred while opening a new window:";
   private static final String CUSTO_INTER_1_FXML = "custointer.fxml";
     private static final String SERVICE_PROVIDER_PAGE_FXML = "serviceproviderpage.fxml";
    private static final String HALL_INTER_FXML = "Halls.fxml";
 private static final String HELLO_VIEW_FXML ="hello-view.fxml";
 private static final String USER_PRINT ="User not found!";
    private static final String LOCATION_1= "location";
   private static final String CHECKING_AVAILABLE= "Error while checking availability:";
   private static final String INVALID_CREDENTIALS_MESSAGE ="Invalid email or password." ;
   private static final String CAPACITY_COLUMN = "capacity";
    private static final String START_TIME_COLUMN = "starttime";
   private static final String SELECT_HALL_ID_QUERY = "SELECT hallid FROM software.halls WHERE hallname = ?";
   private static final String SELECT_USER_ID_QUERY = "SELECT userid FROM software.users WHERE email = ? AND password = ?";
  private static final String RESERVATION_ID_COLUMN = "reservationid";
   private static final String BOOKING_FAILED_MESSAGE ="Failed to book the hall. Please try again later.";
private static final String HALL_NAME_COLUMN = "hallname";
  private static final String SERVICE_NAME_COLUMN ="servicename";
   private static final String WINDOW_OPENING_ERROR_MESSAGE = "An error occurred while opening a new window:";
    private static final String PRICE_PER_HOUR_COLUMN_NAME ="pricePerHour" ;
   private static final String IMAGE_DESCRIPTION ="Image Files" ;
   private static final String DEFAULT_TIME = "16:00:00";
   private static final String DEFAULT_TIME_1800 ="18:00:00" ;
   private static final String DEFAULT_TIME_2000 ="20:00:00" ;
   private static final String HALLS_JOIN_CONDITION = "INNER JOIN software.halls h ON r.hallid = h.hallid";
   private static final String END_TIME_COLUMN ="endtime" ;
   private static final String TOTAL_PRICE_COLUMN = "totalprice";
   private static final String STATE_COLUMN ="state" ;
   private static final String UPDATE_RESERVATION_SQL = "UPDATE software.new_table_name SET state = ? WHERE reservationid = ?";
   private static final String ACCEPTED_STATE ="accepted" ;
   private static final String GET_SERVICE_ID_SQL ="SELECT serviceid FROM software.services WHERE servicename = ?";
   private static final String SERVICE_ID_COLUMN =  "serviceid";
   private static final String DELETED_STATE = "deleted";
   private static final String PRICE_COLUMN ="price" ;
   private static final String DESCRIPTION_COLUMN = "description";
   private static final String IMAGE_COLUMN ="image" ;
   private static final String SERVICE_ID_COLUMNNN = "serviceId";
   private static final String PNG_EXTENSION = "*.png" ;
   private static final String JPG_EXTENSION = "*.jpg";


   
   

 
   
   
@FXML
public TextField gmailLogIn;
private static final Button service = new Button();
private static final Button halls = new Button();
private static final Button booking = new Button();
private static final Button invoice = new Button();
private static final Button prof = new Button();
private static final Button connect = new Button();
private static final Button bback = new Button();
private static final Button packg = new Button();

public static Button getServiceButton() {
    return service;
}

public static Button getHallsButton() {
    return halls;
}

public static Button getBookingButton() {
    return booking;
}

public static Button getInvoiceButton() {
    return invoice;
}

public static Button getProfButton() {
    return prof;
}

public static Button getConnectButton() {
    return connect;
}

public static Button getBbackButton() {
    return bback;
}

public static Button getPackgButton() {
    return packg;
}
    @FXML
    private javafx.scene.control.Button login1;
    @FXML
    private javafx.scene.control.Button signUp;
    @FXML
    public PasswordField passwordLogIn;
    @FXML
    private javafx.scene.control.Button forget;
    @FXML
    private Button sv;
    @FXML
    private static String z;

    public static String getZ() {

        return z;
    }

    public static void setZ(String z) {
        com.example.projectsoftware.HelloController.z = z;
    }


    @FXML
    public void login1Clicked(ActionEvent event) {
        String emailInput = gmailLogIn.getText();
        String passwordInput = passwordLogIn.getText();

    String query = "SELECT users.firstname, users.lastname, users.username, users.email, users.role, users.code " +
             "FROM software.users " +
             "WHERE email = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
             PreparedStatement preparedStatement =  connection.prepareStatement(query)) {

            preparedStatement.setString(1, emailInput);
            preparedStatement.setString(2, passwordInput);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                UserCredentials.setEmail(emailInput);
                UserCredentials.setPassword(passwordInput);

                switch (role) {
                    case "customer":
                        loadInterface(CUSTO_INTER_1_FXML, event);

                        break;
                    case "service-provider":
                        loadInterface(SERVICE_PROVIDER_PAGE_FXML, event);
                        break;
                    case "admin":
                        loadInterface("Adminlogin.fxml", event);
                        break;
                    default:
                        showAlert("Invalid Role");
                        break;
                }
            } else {
                    showAlert(INVALID_CREDENTIALS_MESSAGE);
            }
        } catch (Exception e) {
            showAlert("Error during login: " + e.getMessage());
        }
    }


    private void loadInterface(String fxmlFileName, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void signUp1Clicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("signup.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            logger.log(null, window);
        }
    }

    @FXML
    void hallsClicked(ActionEvent event) {
    try {
        Parent root;
        root = FXMLLoader.load(getClass().getResource(HALL_INTER_FXML));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
     logger.severe(CHECKING_AVAILABLE);
    }
}

    @FXML
    void serviceClicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("service.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }
    }

    @FXML
    void pakClicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("packagereserve.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }
    }

    @FXML
    void bookingClicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("booking.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }
    }

    @FXML
    void invoiceClicked(ActionEvent event) {
       try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
         InputStream input = new FileInputStream(new File("Flower_Landscape.jrxml"))) {
        DriverManager.deregisterDriver(new org.postgresql.Driver());

        JasperDesign jd = JRXmlLoader.load(input);
        JasperReport jr = JasperCompileManager.compileReport(jd);
        JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);

        JFrame frame = new JFrame("reprt product");
        frame.getContentPane().add(new JRViewer(jp));
        frame.pack();
        frame.setVisible(true);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.toString());
    }

    }

    @FXML
    void profClicked(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("prof.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }
    }

@FXML
void connectClicked(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("connectus.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
    logger.severe(CHECKING_AVAILABLE);
    }
}


 @FXML
void backto1(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource(HELLO_VIEW_FXML));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
    logger.severe(CHECKING_AVAILABLE);
    }
}


    @FXML
    void baccc(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource(HELLO_VIEW_FXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
   
            logger.severe(USER_PRINT);
        }
    }

    @FXML
    private Button backtoallhalls;

@FXML
void backktoallhalls(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource(HALL_INTER_FXML));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger log = Logger.getLogger(getClass().getName());
        log.severe("Error loading HALL_INTER_FXML: " + ex.getMessage());
    }
}


    @FXML
    private TextField capacityy;

    @FXML
    private TextField pricee;

    @FXML
    private TextField locationn;

    @FXML
    private DatePicker dat = new DatePicker();
  @FXML
    private Spinner<LocalTime> timeSpinner = new Spinner<>();

    @FXML
    private Spinner<LocalTime> timeSpinner1 = new Spinner<>();

    public void performInitialization() {
        initializeTimeSpinners();
    }

    private void initializeTimeSpinners() {
        SpinnerValueFactory<LocalTime> valueFactory1 = createTimeSpinnerValueFactory();
        timeSpinner.setValueFactory(valueFactory1);
        timeSpinner.setEditable(true);

        SpinnerValueFactory<LocalTime> valueFactory2 = createTimeSpinnerValueFactory();
        timeSpinner1.setValueFactory(valueFactory2);
        timeSpinner1.setEditable(true);
    }

   private SpinnerValueFactory<LocalTime> createTimeSpinnerValueFactory() {
    SpinnerValueFactory<LocalTime> valueFactory = new SpinnerValueFactory<LocalTime>() {
        {
            setConverter(new LocalTimeStringConverter(FormatStyle.MEDIUM));
        }

        @Override
        public void decrement(int steps) {
            setValue(getValue().minusMinutes(steps));
        }

        @Override
        public void increment(int steps) {
            setValue(getValue().plusMinutes(steps));
        }
    };

    valueFactory.setValue(LocalTime.now());

    return valueFactory;
}

    @FXML
    private Button makereser;

    @FXML
    private Button bk;

    @FXML
    private Button ros;




    @FXML
    private Button cancclllee;

    @FXML
    private DatePicker newcalender;

    @FXML
    private TextField newcapacity = new TextField();

    @FXML
    private ChoiceBox<String> newchoice;
    @FXML
    private Button ert;


    @FXML
    private TextField newhallname = new TextField();

    @FXML
    private TextField newlocation = new TextField();

    @FXML
    private TextField newprice = new TextField();
    @FXML
    private Label hallImageLabel = new Label();


    @FXML
    void choicesnew(MouseEvent event){
 logger.severe(CHECKING_AVAILABLE);


    }
final String WAIT_ACCEPT_MESSAGE = "Wait owner to accept your reservation.";

    @FXML
    private void select(ActionEvent event) {
    

        String query = "SELECT capacity, location, priceperhour FROM software.halls WHERE hallname = 'Rose'";

        try (Connection  connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
             PreparedStatement preparedStatement =  connectionDB.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                int capacity = resultSet.getInt( CAPACITY_COLUMN);
                String location = resultSet.getString(LOCATION_1);
                double price = resultSet.getDouble(PRICE_PER_HOUR_COLUMN );

                capacityy.setText(String.valueOf(capacity));
                locationn.setText(location);
                pricee.setText(String.valueOf(price));
                capacityy.setEditable(false);
                locationn.setEditable(false);
                pricee.setEditable(false);
            }

        } catch (SQLException e) {
       logger.severe(CHECKING_AVAILABLE);
        }
    }
   final String USER_ID_COLUMN = "userid";
 final String SELECT_DATE_AND_TIME_MESSAGE ="Please select date and start time.";
    @FXML
    void bookHall(ActionEvent event) {
        LocalDate chosenDate = dat.getValue();
        String startTimeStr = choicetime.getValue();

        if (chosenDate == null || startTimeStr == null) {
            showAlert(SELECT_DATE_AND_TIME_MESSAGE);
            return;


        
        }
        String hallName = newhallname.getText();

        if (hallName.isEmpty()) {
          showAlert(ENTER_HALL_NAME_MESSAGE);
            return;
        }
        LocalTime startTime = LocalTime.parse(startTimeStr);
        LocalTime endTime = startTime.plusHours(2);

        long durationHours = 2;

        try (Connection  connectionDB = DriverManager.getConnection(DB_URL, DB_USER,getPasswordFromEnvironment())) {

            int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(),  connectionDB);

            if (userId == -1) {
                showAlert(INVALID_CREDENTIALS_MESSAGE);
                return;
            }
            int hallId = gettHallId(hallName,  connectionDB);
            if (hallId == 0) {
                showAlert("Hall not found.");
                return;
            }

            if (!isHallAvailable(chosenDate, startTime, endTime, hallId,  connectionDB)) {
                showAlert(WAIT_ACCEPT_MESSAGE);
                return;
            }


            BigDecimal pricePerHour = getPricePerHour(hallId,  connectionDB);

            BigDecimal totalPrice = pricePerHour.multiply(BigDecimal.valueOf(durationHours));
            insertReservation(userId, hallId, chosenDate, startTime, endTime, totalPrice, connectionDB);

            showAlert(WAIT_ACCEPT_MESSAGE);
        } catch (SQLException e) {
  logger.severe(CHECKING_AVAILABLE);
            showAlert(BOOKING_FAILED_MESSAGE);
        }
    }
    
private static final String HALL_ID_COLUMN = "hallid";

    private int gettHallId(String hallName, Connection  connectionDB) {
       int hallId = 0;
    try (PreparedStatement statement =  connectionDB.prepareStatement(SELECT_HALL_ID_QUERY)) {
        statement.setString(1, hallName);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                hallId = resultSet.getInt(HALL_ID_COLUMN);
            }
        }
    } catch (SQLException e) {
 logger.severe(CHECKING_AVAILABLE);
    }
    return hallId;
    }

    private int getUserId(String email, String password, Connection  connectionDB) throws SQLException {
      String sql = SELECT_USER_ID_QUERY;
    try (PreparedStatement statement =  connectionDB.prepareStatement(sql)) {
        statement.setString(1, email);
        statement.setString(2, password);
        try (ResultSet resultSet = statement.executeQuery()) {
            return resultSet.next() ? resultSet.getInt(USER_ID_COLUMN) : -1;
        }
    }
    }

    private boolean isHallAvailable(LocalDate date, LocalTime startTime, LocalTime endTime, int hallId,
                                    Connection  connectionDB) throws SQLException {
        String sql = "SELECT COUNT(*) FROM software.new_table_name WHERE hallid = ? AND date = ? AND "
                + "((starttime <= ? AND endtime >= ?) OR (starttime <= ? AND endtime >= ?) AND state != 'deleted')";
        try (PreparedStatement statement =  connectionDB.prepareStatement(sql)) {
            statement.setInt(1, hallId);
            statement.setDate(2, java.sql.Date.valueOf(date));
            statement.setTime(3, java.sql.Time.valueOf(startTime));
            statement.setTime(4, java.sql.Time.valueOf(startTime));
            statement.setTime(5, java.sql.Time.valueOf(endTime));
            statement.setTime(6, java.sql.Time.valueOf(endTime));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) == 0;
        }
    }

    private BigDecimal getPricePerHour(int hallId, Connection  connectionDB) throws SQLException {
        String sql = "SELECT priceperhour FROM software.halls WHERE hallid = ?";
        try (PreparedStatement statement = connectionDB.prepareStatement(sql)) {
            statement.setInt(1, hallId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBigDecimal( PRICE_PER_HOUR_COLUMN_NAME);
        }
    }

    private void insertReservation(int userId, int hallId, LocalDate date, LocalTime startTime, LocalTime endTime,
                                   BigDecimal totalPrice, Connection  connectionDB) throws SQLException {
        String sql = "INSERT INTO software.new_table_name (userid, hallid, date, starttime, endtime, totalprice, state) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement =  connectionDB.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, hallId);
            statement.setDate(3, java.sql.Date.valueOf(date));
            statement.setTime(4, java.sql.Time.valueOf(startTime));
            statement.setTime(5, java.sql.Time.valueOf(endTime));
            statement.setBigDecimal(6, totalPrice);
            statement.setString(7, "wait");
            statement.executeUpdate();
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    private CheckBox deccheck;

    @FXML
    private CheckBox djcheck;

    @FXML
    private CheckBox opencheck;

    @FXML
    void addser(ActionEvent event) {
        double additionalPrice = 0;


        if (djcheck.isSelected()) {
            additionalPrice += 500;
        }
        if (opencheck.isSelected()) {
            additionalPrice += 1000;
        }
        if (deccheck.isSelected()) {
            additionalPrice += 1000;
        }

        String email = UserCredentials.getEmail();
        String password = UserCredentials.getPassword();
        updateTotalPrice(email, password, additionalPrice);
    }


   private void updateTotalPrice(String email, String password, double additionalPrice) {
    String getUserSql = SELECT_USER_ID_QUERY;
    String getReservationsSql = "SELECT reservationid FROM software.reservations WHERE userid = ?";
    String updateReservationSql = "UPDATE software.reservations SET totalprice = totalprice + ? WHERE reservationid = ?";
    
    try (Connection connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
         PreparedStatement getUserStatement = connectionDB.prepareStatement(getUserSql);
         PreparedStatement getReservationsStatement = connectionDB.prepareStatement(getReservationsSql);
         PreparedStatement updateReservationStatement = connectionDB.prepareStatement(updateReservationSql)) {
        
        getUserStatement.setString(1, email);
        getUserStatement.setString(2, password);
        ResultSet userResultSet = getUserStatement.executeQuery();
        
        if (userResultSet.next()) {
            int userId = userResultSet.getInt(USER_ID_COLUMN);
            getReservationsStatement.setInt(1, userId);
            ResultSet reservationsResultSet = getReservationsStatement.executeQuery();

            // Move this setter invocation out of the loop
            updateReservationStatement.setDouble(1, additionalPrice);
            
            while (reservationsResultSet.next()) {
                int reservationId = reservationsResultSet.getInt(RESERVATION_ID_COLUMN);
                updateReservationStatement.setInt(2, reservationId);
                int rowsAffected = updateReservationStatement.executeUpdate();
                
                if (rowsAffected > 0) {
                    showAlert("Total price updated successfully.");
                } else {
                    showAlert("Failed to update total price for reservation ID: " + reservationId);
                }
            }
        } else {
            showAlert(INVALID_CREDENTIALS_MESSAGE);
        }
    } catch (SQLException e) {
        logger.severe(CHECKING_AVAILABLE);
    }
}
 private static String getPasswordFromEnvironment() {
        String password = System.getenv("1482003");
        if (password == null) {
            throw new IllegalStateException("Database password not found in environment variables.");
        }
        return password;
    }

    @FXML
    void forgoooot(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("forgotpass.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            logger.log(null, window);
        }
    }

    @FXML
    private Button check;

    @FXML
    private TextField emmmail;

    @FXML
    private TextField newpass;

    @FXML
    private Button reset;

    @FXML
    private TextField vernewpass;

    @FXML
    private TextField yourcode;

    @FXML
    private void checkbutton(ActionEvent event) {
        String email = emmmail.getText();
        String code = yourcode.getText();

        if (email.isEmpty() || code.isEmpty()) {
            showAlert("Please enter both email and code.");
            return;
        }


        String query = "SELECT userid FROM software.users WHERE email = ? AND code = ?";

        try (Connection  connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
             PreparedStatement preparedStatement =  connectionDB.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, code);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                newpass.setDisable(false);
                vernewpass.setDisable(false);
                reset.setDisable(false);
                showAlert("Enter a new password please.");
            } else {
                showAlert("Invalid email or code. Please try again.");
            }

        } catch (SQLException e) {
    logger.severe(CHECKING_AVAILABLE);
            showAlert("An error occurred while checking email and code.");
        }
    }

    @FXML
    private void resetbutton(ActionEvent event) {
        String newPassword = newpass.getText();
        String verifyNewPassword = vernewpass.getText();

        if (!newPassword.equals(verifyNewPassword)) {
            showAlert("Passwords do not match. Please try again.");
            return;
        }

        String email = emmmail.getText();

        String updateQuery = "UPDATE software.users SET password = ? WHERE email = ?";

        try (Connection  connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
             PreparedStatement preparedStatement =  connectionDB.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Password reset successfully.");
                emmmail.clear();
                yourcode.clear();
                newpass.clear();
                vernewpass.clear();
                newpass.setDisable(true);
                vernewpass.setDisable(true);
                reset.setDisable(true);
            } else {
                showAlert("Failed to reset password. Please try again later.");
            }

        } catch (SQLException e) {
    logger.severe(CHECKING_AVAILABLE);
            showAlert("An error occurred while resetting password.");
        }
    }


    @FXML
    private TextField budgetTextField;

    @FXML
    private Button searchButton;
    @FXML
    private Button budgedconfirm;
    @FXML
    private Button confirmPercentage;


    private double budget;
    private double hallPercentage;
    private double servicePercentage;

    @FXML
    void searchButtonClicked(ActionEvent event) {
        showAlert("Please enter your budget.");

        hallPercentage = 0;
        servicePercentage = 0;
        budgetTextField.clear();
    }

    @FXML
    void confirmBudget(ActionEvent event) {
        try {
            budget = Double.parseDouble(budgetTextField.getText());
            if (budget <= 0) {
                showAlert("Please enter a valid budget.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid budget.");
            return;
        }

        showAlert("Please specify the percentage allocation for halls and services.");

        budgetTextField.clear();
    }

    @FXML
    void confirmPercentage(ActionEvent event) {
        try {
            String[] percentages = budgetTextField.getText().split(",");
            if (percentages.length != 2) {
                showAlert("Please enter two percentages separated by a comma.");
                return;
            }

            hallPercentage = Double.parseDouble(percentages[0]);
            servicePercentage = Double.parseDouble(percentages[1]);

            if (hallPercentage < 0 || servicePercentage < 0 || hallPercentage + servicePercentage != 100) {
                showAlert("Please enter valid percentage allocations.");
                return;
            }

            fetchHallsAndServices();

        } catch (NumberFormatException e) {
            showAlert("Please enter valid percentages.");
            return;
        }
    }

    private void fetchHallsAndServices() {
        try (Connection  connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {

            List<String> availableHalls = fetchHalls( connectionDB);
            List<String> availableServices = fetchServices( connectionDB);
            List<String> availablePackages = fetchPackages( connectionDB);

            StringBuilder message = new StringBuilder("Available options:\n");
            message.append("Halls:\n");
            for (String hall : availableHalls) {
                message.append(hall).append("\n");
            }
            message.append("\nServices:\n");
            for (String service : availableServices) {
                message.append(service).append("\n");
            }
            message.append("\nPackages:\n");
            for (String pack : availablePackages) {
                message.append(pack).append("\n");
            }
            showAlert(message.toString());

        } catch (SQLException e) {
            showAlert("Error fetching data from the database: " + e.getMessage());
        }
    }

    private List<String> fetchHalls(Connection  connectionDB) throws SQLException {
        List<String> halls = new ArrayList<>();
        String sql = "SELECT hallname FROM software.halls WHERE priceperhour <= ?";
        try (PreparedStatement statement =  connectionDB.prepareStatement(sql)) {
            statement.setDouble(1, budget * hallPercentage / 100);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                halls.add(resultSet.getString(HALL_NAME_COLUMN));
            }
        }
        return halls;
    }

    private List<String> fetchServices(Connection  connectionDB) throws SQLException {
        List<String> services = new ArrayList<>();
        String sql = "SELECT servicename FROM software.services WHERE price <= ?";
        try (PreparedStatement statement =  connectionDB.prepareStatement(sql)) {
            statement.setDouble(1, budget * servicePercentage / 100);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                services.add(resultSet.getString(SERVICE_NAME_COLUMN));
            }
        }
        return services;
    }

    private List<String> fetchPackages(Connection  connectionDB) throws SQLException {
        List<String> packages = new ArrayList<>();
        String sql = "WITH RECURSIVE service_combinations AS (" +
                "    SELECT serviceid, servicename, price, CAST(servicename AS TEXT) AS combination " +
                "    FROM software.services " +
                "    UNION ALL " +
                "    SELECT s.serviceid, s.servicename, s.price, CONCAT(sc.combination, ' + ', s.servicename) " +
                "    FROM software.services s " +
                "    JOIN service_combinations sc ON true " +
                "    WHERE s.serviceid > sc.serviceid" +
                ") " +
                "SELECT CONCAT(h.hallname, ' with ', sc.combination) AS package_name " +
                "FROM software.halls h " +
                "CROSS JOIN service_combinations sc " +
                "WHERE h.priceperhour <= ? " +
                "GROUP BY h.hallname, sc.combination " +
                "HAVING SUM(sc.price) <= ?";
        try (PreparedStatement statement =  connectionDB.prepareStatement(sql)) {
            double hallBudget = budget * hallPercentage / 100;
            double serviceBudget = budget * servicePercentage / 100;
            statement.setDouble(1, hallBudget);
            statement.setDouble(2, hallBudget + serviceBudget);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                packages.add(resultSet.getString("package_name"));
            }
        }
        return packages;
    }

    @FXML
    private Button event;

    @FXML
    private Button hallss;

    @FXML
    private Button invoices;

    @FXML
    private Button profile;

    @FXML
    private Button servicee;

    @FXML
    private Button users;

    @FXML
    void adminevent(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("adminadmin.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            logger.log(null, window);
        }

    }

   void adminhalls(ActionEvent event) {
    logger.info("0");
    try {
        logger.info("1");
        Parent root = FXMLLoader.load(getClass().getResource("HallsTabel.fxml"));
        logger.info("2");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        logger.info("3");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        logger.log(Level.SEVERE,  WINDOW_OPENING_ERROR_MESSAGE, e);
        logger.info("11");
    }
}

    @FXML
    void admininvoices(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("adminnotification.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE,  WINDOW_OPENING_ERROR_MESSAGE, e);
        }


    }

    @FXML
    void adminprofile(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE,  WINDOW_OPENING_ERROR_MESSAGE, e);
        }

    }

    @FXML
    void adminservice(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource(HELLO_VIEW_FXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }


    }

    @FXML
    void adminusers(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("PackageAdmin.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE,  WINDOW_OPENING_ERROR_MESSAGE, e);
        }

    }


  @FXML
 private Button addd;

    @FXML
    private Button deleteee;
    @FXML
    private TableView<Hall> hallTableView = new TableView<>();

    @FXML
    private TableColumn<Hall, Integer> hallidd;

    @FXML
    private TableColumn<Hall, String> hallnamee;

    @FXML
    private TableColumn<Hall, Integer> capacityyy;

    @FXML
    private TableColumn<Hall, String> locationnn;
    @FXML
    private TableColumn<Hall, Double> priceperhourr;
    private TableColumn<Hall, Integer> USERID;
   private static final String ENTER_HALL_NAME_MESSAGE = "Please enter the hall name.";


    @FXML
    private Button vieeew;

    @FXML
    void addhalls(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AddHall.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE,  WINDOW_OPENING_ERROR_MESSAGE, e);
        }


    }

    @FXML
    void deletehalls(ActionEvent event) {
        hallidd.setCellValueFactory(new PropertyValueFactory<>(HALL_ID_COLUMN));
    hallnamee.setCellValueFactory(new PropertyValueFactory<>(HALL_NAME_COLUMN));
    capacityyy.setCellValueFactory(new PropertyValueFactory<>( CAPACITY_COLUMN));
    priceperhourr.setCellValueFactory(new PropertyValueFactory<>( PRICE_PER_HOUR_COLUMN_NAME));
    locationnn.setCellValueFactory(new PropertyValueFactory<>(LOCATION_1));
    USERID.setCellValueFactory(new PropertyValueFactory<>(USER_ID_COLUMN));
    Hall selectedHall = hallTableView.getSelectionModel().getSelectedItem();
    if (selectedHall != null) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
             PreparedStatement statement = conn.prepareStatement("DELETE FROM software.halls WHERE hallid = ?")) {
            statement.setInt(1, selectedHall.getHallId());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Row deleted successfully.");
                hallTableView.getItems().remove(selectedHall);
            }
        } catch (SQLException e) {
  logger.severe(CHECKING_AVAILABLE);
        }
    } else {
        System.out.println("No row selected.");
    }
    }


    @FXML
    public void viewhalls(ActionEvent event) {
     if (hallTableView == null) {
        System.err.println("hallTableView is not initialized!");
        return;
    }
    
    hallidd.setCellValueFactory(new PropertyValueFactory<>(HALL_ID_COLUMN));
    hallnamee.setCellValueFactory(new PropertyValueFactory<>(HALL_NAME_COLUMN));
    capacityyy.setCellValueFactory(new PropertyValueFactory<>( CAPACITY_COLUMN));
    priceperhourr.setCellValueFactory(new PropertyValueFactory<>( PRICE_PER_HOUR_COLUMN_NAME));
    locationnn.setCellValueFactory(new PropertyValueFactory<>(LOCATION_1));
    USERID.setCellValueFactory(new PropertyValueFactory<>(USER_ID_COLUMN));
    
    hallTableView.getItems().clear();

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
         PreparedStatement statement = conn.prepareStatement("SELECT * FROM software.halls");
         ResultSet resultSet = statement.executeQuery()) {
        
        ObservableList<Hall> halls = FXCollections.observableArrayList();
        
        while (resultSet.next()) {
            int hallId = resultSet.getInt(HALL_ID_COLUMN);
            String hallName = resultSet.getString(HALL_NAME_COLUMN);
            int capacity = resultSet.getInt( CAPACITY_COLUMN);
            double pricePerHour = resultSet.getDouble(PRICE_PER_HOUR_COLUMN );
            String location = resultSet.getString(LOCATION_1);
            int userId = resultSet.getInt(USER_ID_COLUMN);
            Hall hall = new Hall(hallId, hallName, capacity, pricePerHour, location, userId);
            halls.add(hall);
        }
        
        for (Hall hall : halls) {
            System.out.println("Hall ID: " + hall.getHallId());
            System.out.println("Hall Name: " + hall.getHallName());
            System.out.println("Capacity: " + hall.getCapacity());
            System.out.println("Price Per Hour: " + hall.getPricePerHour());
            System.out.println("Location: " + hall.getLocation());
            System.out.println("User ID: " + hall.getUserId());
            System.out.println("---------------------------------");
        }
        
        hallTableView.setItems(halls);
        
    } catch (SQLException e) {
logger.severe(CHECKING_AVAILABLE);
    }
    
    }

    @FXML
    private Button editadmin;

    @FXML
    private TextField emailltxt;

    @FXML
    private TextField fntxt;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField lntxt;

    @FXML
    private TextField passtxt;

    @FXML
    private Button saveadmiv;

    @FXML
    private Button uploadadminoic;

    @FXML
    private TextField userntxt;

    @FXML
    private Button viewadmin;

    @FXML
    void editadmininfo(ActionEvent event) {
        try (Connection  connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
         PreparedStatement statement = connectionDB.prepareStatement("UPDATE software.users SET firstname=?, lastname=?, username=?, password=?, email=?, code=? WHERE userid=?")) {
        
        statement.setString(1, fntxt.getText());
        statement.setString(2, lntxt.getText());
        statement.setString(3, userntxt.getText());
        statement.setString(4, passtxt.getText());
        statement.setString(5, emailltxt.getText());
        statement.setString(6, codetxt.getText());
        statement.setInt(7, Integer.parseInt(idtxt.getText()));
        
        int rowsUpdated = statement.executeUpdate();
        
        if (rowsUpdated > 0) {
            showAlert("User information updated successfully!");
        } else {
            showAlert("Failed to update user information.");
        }
        
    } catch (SQLException e) {
logger.severe(CHECKING_AVAILABLE);
        showAlert("An error occurred while updating user information: " + e.getMessage());
    }
    
    }

    private File selectedImageFile;

    @FXML
    private TextField codetxt;


    @FXML
    void saveadmininfo(ActionEvent event) {
        String email = UserCredentials.getEmail();
    String password = UserCredentials.getPassword();
    try {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(IMAGE_DESCRIPTION ,PNG_EXTENSION, JPG_EXTENSION, "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(saveadmiv.getScene().getWindow());
        if (selectedFile != null) {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                 InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                 Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {

                ImageIO.write(bufferedImage, "png", outputStream);
                String sql = "UPDATE software.users SET photo = ? WHERE email = ? AND password = ? ";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setBinaryStream(1, inputStream);
                    statement.setString(2, email);
                    statement.setString(3, password);
                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        showAlert("Admin photo updated successfully!");
                    } else {
                        showAlert("Failed to update admin photo!");
                    }
                }
            }
        }
    } catch (SQLException | IOException e) {

       logger.severe(CHECKING_AVAILABLE);
        showAlert("Error occurred while updating admin photo!");
    }

    }


    @FXML
    private ImageView pictureImageView;

    @FXML
    void uplodeadminpicture(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(IMAGE_DESCRIPTION ,PNG_EXTENSION, JPG_EXTENSION, "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());

            pictureImageView.setImage(image);

            pictureImageView.setPreserveRatio(true);
            pictureImageView.setFitWidth(305);
            pictureImageView.setFitHeight(255);
        }
    }


    @FXML
    void viweadmininfo(ActionEvent event) {
        String email = UserCredentials.getEmail();
    String password = UserCredentials.getPassword();
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
        String sql = "SELECT * FROM software.users WHERE email = ? AND password = ? ";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    int userId = result.getInt(USER_ID_COLUMN);
                    String firstName = result.getString("firstname");
                    String lastName = result.getString("lastname");
                    String username = result.getString("username");
                    String userEmail = result.getString("email");
                    String userPassword = result.getString("password");
                    String userCode = result.getString("code");
                    byte[] imageData = result.getBytes("photo");
                    idtxt.setText(String.valueOf(userId));
                    fntxt.setText(firstName);
                    lntxt.setText(lastName);
                    userntxt.setText(username);
                    emailltxt.setText(userEmail);
                    passtxt.setText(userPassword);
                    codetxt.setText(userCode);
                    if (imageData != null && imageData.length > 0) {
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                        Image image = new Image(bis);
                        pictureImageView.setImage(image);
                    }
                } else {
                    showAlert("Admin not found!");
                }
            }
        }
    } catch (SQLException e) {
 logger.severe(CHECKING_AVAILABLE);
    } 
    }

    @FXML
    private Button addaa;

    @FXML
    private TextField txet1;

    @FXML
    private TextField txet2;

    @FXML
    private TextField txet3;

    @FXML
    private TextField txet4;
    @FXML
    private TextField text5;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = getPasswordFromEnvironment();

    @FXML
    void addhallbutton(ActionEvent event) {
       String hallName = txet1.getText();
    int capacity = Integer.parseInt(txet2.getText());
    double pricePerHour = Double.parseDouble(txet3.getText());
    String location = txet4.getText();
    int userId = Integer.parseInt(text5.getText());
    if (hallName.isEmpty() || location.isEmpty()) {
        showAlert("Hall name and location cannot be empty.");
        return;
    }
    if (!isUserIdValid(userId)) {
        showAlert("User ID does not exist.");
        return;
    }
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER,getPasswordFromEnvironment());
         PreparedStatement statement = conn.prepareStatement("INSERT INTO software.halls (hallname, capacity, priceperhour, location, userid, image) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, hallName);
        statement.setInt(2, capacity);
        statement.setDouble(3, pricePerHour);
        statement.setString(4, location);
        statement.setInt(5, userId);
        statement.setBytes(6, null);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            showAlert("A new hall has been added successfully.");
            clearTextFields();
            updateImage(getGeneratedHallId(statement));
        } else {
            showAlert("Failed to add a new hall.");
        }
    } catch (SQLException e) {
logger.severe(CHECKING_AVAILABLE);
        showAlert("Error: " + e.getMessage());
    } catch (NumberFormatException e) {
        showAlert("Invalid capacity, price per hour, or user ID format.");
    } 
    }

    private int getGeneratedHallId(PreparedStatement statement) throws SQLException {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        return -1;
    }

    private boolean isUserIdValid(int userId) {
        String sql = "SELECT userid FROM software.users WHERE userid = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
    logger.severe(CHECKING_AVAILABLE);
            return false;
        }
    }

    private void updateImage(int hallId) {
       if (uploadedImage != null) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
             PreparedStatement statement = conn.prepareStatement("UPDATE software.halls SET image = ? WHERE hallid = ?")) {
            
            statement.setBytes(1, imageToByteArray(uploadedImage));
            statement.setInt(2, hallId);
            
            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
                showAlert("Image saved successfully.");
            } else {
                showAlert("Failed to save image.");
            }
            
        } catch (SQLException e) {
     logger.severe(CHECKING_AVAILABLE);
            showAlert("Database error: " + e.getMessage());
        }
    }
    }

    private byte[] imageToByteArray(Image image) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

            ImageIO.write(bufferedImage, "png", outputStream);

            return outputStream.toByteArray();
        } catch (IOException e) {
logger.severe(CHECKING_AVAILABLE);
            showAlert("Error converting image to byte array: " + e.getMessage());
            return null;
        }
    }

    private void clearTextFields() {
        txet1.clear();
        txet2.clear();
        txet3.clear();
        txet4.clear();
        text5.clear();
    }

    @FXML
    private Button backwewe;

    @FXML
    void bacckkkkk(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HallsTabel.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE,  WINDOW_OPENING_ERROR_MESSAGE, e);
        }

    }

    @FXML
    private Button uploadhallpppiii;
    @FXML
    private Label hallpiclabel;
    private Image uploadedImage;


    @FXML
    void uploadhallpic(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        File selectedFile = fileChooser.showOpenDialog(uploadhallpppiii.getScene().getWindow());

        if (selectedFile != null) {
            try {
                byte[] imageData = readImageFile(selectedFile);
                if (imageData != null) {
                    uploadedImage = new Image(new ByteArrayInputStream(imageData));
                    hallpiclabel.setGraphic(new ImageView(uploadedImage));
                    showAlert("Image uploaded successfully.");
                } else {
                    showAlert("Failed to upload image.");
                }
            } catch (IOException e) {
        logger.severe(CHECKING_AVAILABLE);
                showAlert("Error reading image file: " + e.getMessage());
            }
        }
    }

    private byte[] readImageFile(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            return bos.toByteArray();
        }
    }


    @FXML
    private ChoiceBox<String> choicetime = new ChoiceBox<>();
    @FXML
    private Button choicebutton;
    @FXML
    private Button buttontime;


    private LocalDate chosenDate;


    private Connection connectionDB;
    private PreparedStatement checkReservationStatement;
    private PreparedStatement checkReservationStatementtt;


  public void initialize() {
    setupTableOnClick();
    populateHallChoiceBox();
    populateEventChoiceBox();
    setupTimeItems();
    prepareCheckReservationStatements();
    setupDateCellFactories();
}

private void setupTableOnClick() {
    tableeee.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1) {
            packge selectedPackage = tableeee.getSelectionModel().getSelectedItem();
            if (selectedPackage != null) {
                setPackageInfo(selectedPackage);
            }
        }
    });
}

private void setPackageInfo(packge selectedPackage) {
    pid.setText(String.valueOf(selectedPackage.getPackageId()));
    pname.setText(selectedPackage.getPackageName());
    des.setText(selectedPackage.getDescription());
    price.setText(String.valueOf(selectedPackage.getPrice()));
    mguest.setText(String.valueOf(selectedPackage.getMaxGuests()));
    innc.setText(String.join(",", selectedPackage.getIncludes()));
}

private void setupTimeItems() {
    r9.setItems(FXCollections.observableArrayList(DEFAULT_TIME,  DEFAULT_TIME_2000, DEFAULT_TIME_1800, "22:00:00", "24:00:00"));
    r9.getSelectionModel().selectFirst();
    r10.setItems(FXCollections.observableArrayList(DEFAULT_TIME,  DEFAULT_TIME_2000, DEFAULT_TIME_1800, "22:00:00", "24:00:00"));
    r10.getSelectionModel().selectFirst();
    choicetime.getItems().addAll(DEFAULT_TIME, DEFAULT_TIME_1800,  DEFAULT_TIME_2000);
    servicetime.getItems().addAll(DEFAULT_TIME, DEFAULT_TIME_1800,  DEFAULT_TIME_2000);
    packagetime.getItems().addAll(DEFAULT_TIME, DEFAULT_TIME_1800,  DEFAULT_TIME_2000);
}

private void prepareCheckReservationStatements() {
    try {
        connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
        checkReservationStatement = connectionDB.prepareStatement("SELECT COUNT(*) FROM software.reservations WHERE date = ? AND starttime = ? AND hallid = ?");
        checkReservationStatementt = connectionDB.prepareStatement("SELECT COUNT(*) FROM software.reservations WHERE date = ? AND starttime = ? AND serviceid = ?");
        checkReservationStatementtt = connectionDB.prepareStatement("SELECT COUNT(*) FROM software.wedding_packages WHERE date = ? AND starttime = ? AND package_id = ?");
    } catch (SQLException e) {
        logger.severe(CHECKING_AVAILABLE);
    }
}

private void setupDateCellFactories() {
    dat.setDayCellFactory(createDateCellFactory());
    datereservation.setDayCellFactory(createDateCellFactory());
    datereservatiooon.setDayCellFactory(createDateCellFactory());
}

private Callback<DatePicker, DateCell> createDateCellFactory() {
    return dp -> new DateCell() {
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
    };
}

    private int getReservedCount(LocalDate date) {
        int reservedCount = 0;
        try {
            for (String time : choicetime.getItems()) {
                checkReservationStatement.setDate(1, Date.valueOf(date));
                checkReservationStatement.setTime(2, Time.valueOf(time));
                checkReservationStatement.setInt(3, getHallId());
                ResultSet resultSet = checkReservationStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count > 0) {
                    reservedCount++;
                }
            }
        } catch (SQLException e) {
  logger.severe(CHECKING_AVAILABLE);
        }
        return reservedCount;
    }

    private int getReservedCounttt(LocalDate date) {
        int reservedCount = 0;
        try {
            for (String time : packagetime.getItems()) {
                checkReservationStatementtt.setDate(1, Date.valueOf(date));
                checkReservationStatementtt.setTime(2, Time.valueOf(time));
                checkReservationStatementtt.setInt(3, getHallIdd());
                ResultSet resultSet = checkReservationStatementtt.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count > 0) {
                    reservedCount++;
                }
            }
        } catch (SQLException e) {
   logger.severe(CHECKING_AVAILABLE);
        }
        return reservedCount;
    }

    private int getHallId() {
    String hallName = newhallname.getText();
    int hallId = 0;
    try (Connection connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
         PreparedStatement statement =  connectionDB.prepareStatement(SELECT_HALL_ID_QUERY)) {
        
        statement.setString(1, hallName);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                hallId = resultSet.getInt(HALL_ID_COLUMN);
            }
        }
        
    } catch (SQLException e) {
  logger.severe(CHECKING_AVAILABLE);
    }
    return hallId;
    }

    private int getHallIdd() {
      String hallName = mn1.getText();
    int hallId = 0;
    try (Connection  connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
         PreparedStatement statement =  connectionDB.prepareStatement("SELECT package_id FROM software.wedding_packages WHERE package_name = ?")) {
        statement.setString(1, hallName);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                hallId = resultSet.getInt("package_id");
            }
        }
    } catch (SQLException e) {
logger.severe(CHECKING_AVAILABLE);
    }
    return hallId;
    }

    public void choisetiameondate(javafx.scene.input.MouseEvent mouseEvent) {
        LocalDate chosenDate = dat.getValue();
        if (chosenDate == null) {
            return;
        }

        choicetime.getItems().clear();

        List<String> availableTimes = new ArrayList<>();

        try {
            connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());

            checkReservationStatement =  connectionDB.prepareStatement("SELECT DISTINCT starttime FROM software.reservations WHERE date = ? AND hallid = ?");

            checkReservationStatement.setDate(1, Date.valueOf(chosenDate));
            checkReservationStatement.setInt(2, getHallId());


            ResultSet resultSet = checkReservationStatement.executeQuery();

            while (resultSet.next()) {
                availableTimes.add(resultSet.getString(START_TIME_COLUMN));
            }

         connectionDB.close();
        } catch (SQLException e) {
    logger.severe(CHECKING_AVAILABLE);
        }

        List<String> allTimes = List.of(DEFAULT_TIME, DEFAULT_TIME_1800,  DEFAULT_TIME_2000);

        List<String> availableTimesFiltered = allTimes.stream()
                .filter(time -> !availableTimes.contains(time))
                .collect(Collectors.toList());

        choicetime.getItems().addAll(availableTimesFiltered);
    }

    @FXML
    private Button notifi;
  

    @FXML
    void notificbutton(ActionEvent event) {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("reservationnoti.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE,  WINDOW_OPENING_ERROR_MESSAGE, e);

        }
    }


    @FXML
    private Button bt1;

    @FXML
    private Button bt2;

    @FXML
    private Button bt3;

    @FXML
    private TableColumn<NewReservation, Integer> cc1 = new TableColumn<>();

    @FXML
    private TableColumn<NewReservation, Integer> cc2 = new TableColumn<>();

    @FXML
    private TableColumn<NewReservation, Integer> cc3 = new TableColumn<>();

    @FXML
    private TableColumn<NewReservation, Date> cc4 = new TableColumn<>();

    @FXML
    private TableColumn<NewReservation, Time> cc5 = new TableColumn<>();

    @FXML
    private TableColumn<NewReservation, Time> cc6 = new TableColumn<>();

    @FXML
    private TableColumn<NewReservation, Double> cc7 = new TableColumn<>();

    @FXML
    private TableColumn<NewReservation, String> cc8 = new TableColumn<>();

    @FXML
    private TableView<NewReservation> tabelnotification = new TableView<>();


@FXML
void logoutserviceprovider(ActionEvent event) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
        int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);

        if (userId != -1) {
            String sql = "SELECT r.reservationid, r.userid, r.hallid, r.date, r.starttime, r.endtime, r.totalprice, r.state " +
                    "FROM software.new_table_name r " +
                   HALLS_JOIN_CONDITION +
                    "WHERE h.userid = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    ArrayList<NewReservation> reservations = new ArrayList<>();
                    while (resultSet.next()) {
                        int reservationId = resultSet.getInt(RESERVATION_ID_COLUMN);
                        int userIdd = resultSet.getInt(USER_ID_COLUMN);
                        int hallId = resultSet.getInt(HALL_ID_COLUMN);
                        Date date = resultSet.getDate("date");
                        Time startTime = resultSet.getTime(START_TIME_COLUMN);
                        Time endTime = resultSet.getTime(END_TIME_COLUMN);
                        double totalPrice = resultSet.getDouble(TOTAL_PRICE_COLUMN);
                        String state = resultSet.getString(STATE_COLUMN );

                        // Use the builder pattern to create NewReservation objects
                        NewReservation reservation = new NewReservation.Builder(reservationId, userIdd, hallId, date, startTime, endTime)
                                                    .totalPrice(totalPrice)
                                                    .state(state)
                                                    .build();
                        reservations.add(reservation);
                    }

                    cc1.setCellValueFactory(new PropertyValueFactory<>(RESERVATION_ID_COLUMN));
                    cc2.setCellValueFactory(new PropertyValueFactory<>(USER_ID_COLUMN));
                    cc3.setCellValueFactory(new PropertyValueFactory<>(HALL_ID_COLUMN));
                    cc4.setCellValueFactory(new PropertyValueFactory<>("date"));
                    cc5.setCellValueFactory(new PropertyValueFactory<>(START_TIME_COLUMN));
                    cc6.setCellValueFactory(new PropertyValueFactory<>(END_TIME_COLUMN));
                    cc7.setCellValueFactory(new PropertyValueFactory<>(TOTAL_PRICE_COLUMN));
                    cc8.setCellValueFactory(new PropertyValueFactory<>(STATE_COLUMN ));

                    ArrayList<NewReservation> items = new ArrayList<>(tabelnotification.getItems());

                    tabelnotification.getItems().clear();
                    tabelnotification.getItems().addAll(reservations);
                }
            }
        } else {
            System.out.println(USER_PRINT);
        }
    } catch (SQLException e) {
logger.severe(CHECKING_AVAILABLE);
    }
}

    @FXML
    void accept(ActionEvent event) {
        ObservableList<NewReservation> selectedReservations = tabelnotification.getSelectionModel().getSelectedItems();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
            String sql = UPDATE_RESERVATION_SQL;
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, ACCEPTED_STATE );
                for (NewReservation reservation : selectedReservations) {
                    statement.setInt(2, reservation.getReservationId());

                    statement.executeUpdate();
                }

                logoutserviceprovider(new ActionEvent());
            }
        } catch (SQLException e) {
    logger.severe(CHECKING_AVAILABLE);
        }


    }


    @FXML
    void deleteres(ActionEvent event) {
        ObservableList<NewReservation> selectedReservations = tabelnotification.getSelectionModel().getSelectedItems();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
            String sql = UPDATE_RESERVATION_SQL;
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, "rejected");
                for (NewReservation reservation : selectedReservations) {
                    statement.setInt(2, reservation.getReservationId());
                    statement.executeUpdate();
                }

                logoutserviceprovider(new ActionEvent());
            }
        } catch (SQLException e) {
   logger.severe(CHECKING_AVAILABLE);
        }

    }

    @FXML
    private Button bbb1;

    @FXML
    private Button bbb2;

    @FXML
    private Button bbb3;

    @FXML
    private TableColumn<ReservationInfo, Integer> col1 = new TableColumn<>();

    @FXML
    private TableColumn<ReservationInfo, String> col2 = new TableColumn<>();

    @FXML
    private TableColumn<ReservationInfo, String> col3 = new TableColumn<>();

    @FXML
    private TableColumn<ReservationInfo, String> col4 = new TableColumn<>();

    @FXML
    private TableColumn<ReservationInfo, Date> col5 = new TableColumn<>();

    @FXML
    private TableColumn<ReservationInfo, Time> col6 = new TableColumn<>();

    @FXML
    private TableColumn<ReservationInfo, Time> col7 = new TableColumn<>();

    @FXML
    private TableColumn<ReservationInfo, Double> col8 = new TableColumn<>();

    @FXML
    private TableColumn<ReservationInfo, String> col9 = new TableColumn<>();

    @FXML
    private TableView<ReservationInfo> confirmtabel = new TableView<>();


    @FXML
    void viewstate(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
            int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);

            if (userId != -1) {
                String query = "SELECT r.reservationid, u.username, h.hallname, s.servicename, r.date, r.starttime, r.endtime, r.totalprice, r.state " +
                        "FROM software.new_table_name r " +
                        "INNER JOIN software.users u ON r.userid = u.userid " +
                        "INNER JOIN software.halls h ON r.hallid = h.hallid " +
                        "LEFT JOIN software.services s ON r.serviceid = s.serviceid " +
                        "WHERE r.userid = ?";
                try (PreparedStatement statement = conn.prepareStatement(query)) {
                    statement.setInt(1, userId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        ArrayList<ReservationInfo> reservations = new ArrayList<>();
                        while (resultSet.next()) {
                            int reservationId = resultSet.getInt(RESERVATION_ID_COLUMN);
                            String userName = resultSet.getString("username");
                            String hallName = resultSet.getString(HALL_NAME_COLUMN);
                            String serviceName = resultSet.getString(SERVICE_NAME_COLUMN);
                            Date date = resultSet.getDate("date");
                            Time startTime = resultSet.getTime(START_TIME_COLUMN);
                            Time endTime = resultSet.getTime(END_TIME_COLUMN);
                            double totalPrice = resultSet.getDouble(TOTAL_PRICE_COLUMN);
                            String state = resultSet.getString(STATE_COLUMN );
                            reservations.add(new ReservationInfo(reservationId, userName, hallName, serviceName, date, startTime, endTime, totalPrice, state));
                        }
                        col1.setCellValueFactory(new PropertyValueFactory<>(RESERVATION_ID_COLUMN));
                        col2.setCellValueFactory(new PropertyValueFactory<>("userName"));
                        col3.setCellValueFactory(new PropertyValueFactory<>(HALL_NAME_COLUMN));
                        col4.setCellValueFactory(new PropertyValueFactory<>(SERVICE_NAME_COLUMN));
                        col5.setCellValueFactory(new PropertyValueFactory<>("date"));
                        col6.setCellValueFactory(new PropertyValueFactory<>(START_TIME_COLUMN));
                        col7.setCellValueFactory(new PropertyValueFactory<>(END_TIME_COLUMN));
                        col8.setCellValueFactory(new PropertyValueFactory<>(TOTAL_PRICE_COLUMN));
                        col9.setCellValueFactory(new PropertyValueFactory<>(STATE_COLUMN ));


                        confirmtabel.getItems().clear();

                        confirmtabel.getItems().addAll(reservations);
                    }
                }
            } else {
                System.out.println(USER_PRINT);
            }
        } catch (SQLException e) {
   logger.severe(CHECKING_AVAILABLE);
        }
    }


    @FXML
    void confirnation(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
            ReservationInfo selectedReservation = confirmtabel.getSelectionModel().getSelectedItem();
            if (selectedReservation != null && selectedReservation.getState().equals(ACCEPTED_STATE )) {
                int hallId = getHallId(selectedReservation.getHallName(), conn);
                if (hallId != -1) {
                    String query = "INSERT INTO software.reservations (reservationid,userid, hallid, date, starttime, endtime, totalprice, serviceid, state) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement statement = conn.prepareStatement(query)) {
                        statement.setInt(1, selectedReservation.getReservationId());
                        statement.setInt(2, getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn));
                        statement.setInt(3, hallId);
                        statement.setDate(4, (Date) selectedReservation.getDate());
                        statement.setTime(5, selectedReservation.getStartTime());
                        statement.setTime(6, selectedReservation.getEndTime());
                        statement.setDouble(7, selectedReservation.getTotalPrice());
                        statement.setInt(8, getServiceId(selectedReservation.getServiceName(), conn));
                        statement.setString(9, selectedReservation.getState());
                        statement.executeUpdate();
                        showAlert("data inserted successfully");


                    }
                } else {
                    showAlert("Hall not found for reservation: " + selectedReservation.getReservationId());
                }
            } else {
                showAlert("No reservation selected or selected reservation cannot be confirmed.");
            }
        } catch (SQLException e) {
            showAlert("reservation is already exist");
        }
    }


    private int getHallId(String hallName, Connection conn) throws SQLException {
        int hallId = -1;
        String query = SELECT_HALL_ID_QUERY;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, hallName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    hallId = resultSet.getInt(HALL_ID_COLUMN);
                }
            }
        }
        return hallId;
    }

    private int getServiceId(String serviceName, Connection conn) throws SQLException {
        int serviceId = -1;
        String query = GET_SERVICE_ID_SQL ;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, serviceName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    serviceId = resultSet.getInt(SERVICE_ID_COLUMN );
                }
            }
        }
        return serviceId;
    }

    @FXML
    void deletestate(ActionEvent event) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
        ReservationInfo selectedReservation = confirmtabel.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {
            showAlert("No reservation selected.");
            return;
        }

        if (selectedReservation.getState().equals(DELETED_STATE)) {
            showAlert("This reservation is already deleted.");
            return;
        }

        if (selectedReservation.getState().equals("wait")) {
            deleteWaitReservation(selectedReservation, conn);
        } else if (selectedReservation.getState().equals(ACCEPTED_STATE)) {
            confirmDeleteReservation(selectedReservation, conn);
        }
    } catch (SQLException e) {
        logger.severe(CHECKING_AVAILABLE);
        showAlert("An error occurred while connecting to the database.");
    }
}

private void deleteWaitReservation(ReservationInfo selectedReservation, Connection conn) throws SQLException {
    String query = "DELETE FROM software.new_table_name WHERE reservationid = ?";
    try (PreparedStatement statement = conn.prepareStatement(query)) {
        statement.setInt(1, selectedReservation.getReservationId());
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            showAlert("Data deleted successfully");
        } else {
            showAlert("No reservation found with ID: " + selectedReservation.getReservationId());
        }
    }
}

private void confirmDeleteReservation(ReservationInfo selectedReservation, Connection conn) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Delete");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this reservation? 10% will be deducted from the price.");
    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(okButton, cancelButton);
    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && result.get() == okButton) {
        double newPrice = selectedReservation.getTotalPrice() * 0.1;
        try {
            int userId = getUserIdFromUserName(selectedReservation.getUserName(), conn);
            int hallId = getHallIdFromHallName(selectedReservation.getHallName(), conn);
            int reservationId = selectedReservation.getReservationId();
            String insertQuery = "INSERT INTO software.reservations (reservationid, userid, hallid, date, starttime, endtime, totalprice, serviceid, state) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                insertStatement.setInt(1, reservationId);
                insertStatement.setInt(2, userId);
                insertStatement.setInt(3, hallId);
                insertStatement.setDate(4, (Date) selectedReservation.getDate());
                insertStatement.setTime(5, selectedReservation.getStartTime());
                insertStatement.setTime(6, selectedReservation.getEndTime());
                insertStatement.setDouble(7, newPrice);
                insertStatement.setInt(8, selectedReservation.getServiceId());
                insertStatement.setString(9, DELETED_STATE);
                int rowsInserted = insertStatement.executeUpdate();

                if (rowsInserted > 0) {
                    showAlert("Reservation deleted. New price: " + newPrice);
                    String updateQuery = "UPDATE software.new_table_name SET state = 'deleted' WHERE reservationid = ?";
                    try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
                        updateStatement.setInt(1, reservationId);
                        updateStatement.executeUpdate();
                    }
                } else {
                    showAlert("Failed to insert reservation into the database.");
                }
            }
        } catch (SQLException e) {
            logger.severe(CHECKING_AVAILABLE);
            showAlert("An error occurred while deleting reservation.");
        }
    }
}

    private int getUserIdFromUserName(String userName, Connection  connectionDB) throws SQLException {
        String sql = "SELECT userid FROM software.users WHERE username = ?";
        try (PreparedStatement statement =  connectionDB.prepareStatement(sql)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getInt(USER_ID_COLUMN) : -1;
        }
    }

    private int getHallIdFromHallName(String hallName, Connection conn) throws SQLException {
        int hallId = -1;
        String query = SELECT_HALL_ID_QUERY;

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, hallName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    hallId = resultSet.getInt(HALL_ID_COLUMN);
                }
            }
        }
        return hallId;
    }

    public void newreserve(javafx.event.ActionEvent actionEvent) {
        logger.severe(CHECKING_AVAILABLE);
    }

    public void canclenew(javafx.event.ActionEvent actionEvent) {
        logger.severe(CHECKING_AVAILABLE);
    }

    public void eeeee(javafx.event.ActionEvent actionEvent) {
        logger.severe(CHECKING_AVAILABLE);
    }

    @FXML
    private ImageView hallImageView;

    public void populateFields(Halls selectedHall) {
        newhallname.setText(selectedHall.getName());
        newcapacity.setText(String.valueOf(selectedHall.getCapacity()));
        newprice.setText(String.valueOf(selectedHall.getPrice()));
        newlocation.setText(selectedHall.getLocation());
        Image imageData = selectedHall.getImage();
        hallImageView.setImage(imageData);
    }


    @FXML
    private Button b500;

    @FXML
    private Button b60;

    @FXML
    private DatePicker datereservation = new DatePicker();

    @FXML
    private Label lb7 = new Label();

    @FXML
    private Label lb8 = new Label();

    @FXML
    private Label lb9 = new Label();

    @FXML
    private ImageView serviceimage;

    @FXML
    private ChoiceBox<String> servicetime = new ChoiceBox<>();

    @FXML
    void cancleservice(javafx.event.ActionEvent event) {
 logger.severe(CHECKING_AVAILABLE);
    }


    private PreparedStatement checkReservationStatementt;


    public void populateFieldss(Services selectedHall) {
        lb7.setText(String.valueOf(selectedHall.getPrice()));
        lb8.setText(String.valueOf(selectedHall.getDescription()));
        lb9.setText(String.valueOf(selectedHall.getServiceName()));

        Image imageData = selectedHall.getImage();
        serviceimage.setImage(imageData);
    }

    @FXML
    private Button b2000;

    @FXML
    private DatePicker datereservatiooon = new DatePicker();

    @FXML
    private TextField mn1;

    @FXML
    private TextField mn2;

    @FXML
    private TextField mn3;

    @FXML
    private ChoiceBox<String> packagetime = new ChoiceBox<>();

    @FXML
    void clicktimepackagechoice(MouseEvent event) {
        LocalDate chosenDate = datereservatiooon.getValue();
        if (chosenDate == null) {
            return;
        }

        packagetime.getItems().clear();

        List<String> availableTimes = new ArrayList<>();

        try {
            connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());

            checkReservationStatementtt =  connectionDB.prepareStatement("SELECT DISTINCT starttime FROM software.wedding_packages WHERE date = ? AND package_id = ?");

            checkReservationStatementtt.setDate(1, Date.valueOf(chosenDate));
            checkReservationStatementtt.setInt(2, getHallIdd());


            ResultSet resultSet = checkReservationStatementtt.executeQuery();

            while (resultSet.next()) {
                availableTimes.add(resultSet.getString(START_TIME_COLUMN));
            }

             connectionDB.close();
        } catch (SQLException e) {
   logger.severe(CHECKING_AVAILABLE);
        }

        List<String> allTimes = List.of(DEFAULT_TIME, DEFAULT_TIME_1800,  DEFAULT_TIME_2000);

        List<String> availableTimesFiltered = allTimes.stream()
                .filter(time -> !availableTimes.contains(time))
                .collect(Collectors.toList());

        packagetime.getItems().addAll(availableTimesFiltered);

    }

    @FXML
    void reserpackageeee(ActionEvent event) {
        LocalDate chosenDate = datereservatiooon.getValue();
        String startTimeStr = packagetime.getValue();

        if (chosenDate == null || startTimeStr == null) {
            showAlert(SELECT_DATE_AND_TIME_MESSAGE);
            return;
        }
        String hallName = mn1.getText();

        if (hallName.isEmpty()) {
          showAlert(ENTER_HALL_NAME_MESSAGE);
            return;
        }
        LocalTime startTime = LocalTime.parse(startTimeStr);

        LocalTime endTime = startTime.plusHours(2);

        long durationHours = 2;

        try (Connection  connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {

            int userId = getUserIdd(UserCredentials.getEmail(), UserCredentials.getPassword(),  connectionDB);

            if (userId == -1) {
                showAlert(INVALID_CREDENTIALS_MESSAGE);
                return;
            }
            int hallId = getHallIdd();
            if (hallId == 0) {
                showAlert("Service not found.");
                return;
            }

            if (!isHallAvailablee(chosenDate, startTime, endTime, hallId,  connectionDB)) {
                showAlert(WAIT_ACCEPT_MESSAGE);
                return;
            }

            BigDecimal pricePerHour = getPricePerHourr(hallId, connectionDB);

            BigDecimal totalPrice = pricePerHour.multiply(BigDecimal.valueOf(durationHours));

            insertReservationn(userId, hallId, chosenDate, startTime, endTime, totalPrice,  connectionDB);

            showAlert(WAIT_ACCEPT_MESSAGE);
        } catch (SQLException e) {
     logger.severe(CHECKING_AVAILABLE);
            showAlert(BOOKING_FAILED_MESSAGE);
        }

    }

    public void populateFieldsss(packge selectedHall) {
        mn1.setText(String.valueOf(selectedHall.getPrice()));
        mn2.setText(String.valueOf(selectedHall.getDescription()));
        mn3.setText(String.valueOf(selectedHall.getPackageName()));


    }


    private int getReservedCountt(LocalDate date) {
        int reservedCount = 0;
        try {
            for (String time : servicetime.getItems()) {
                checkReservationStatementt.setDate(1, Date.valueOf(date));
                checkReservationStatementt.setTime(2, Time.valueOf(time));
                checkReservationStatementt.setInt(3, getser());
                ResultSet resultSet = checkReservationStatementt.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count > 0) {
                    reservedCount++;
                }
            }
        } catch (SQLException e) {
 logger.severe(CHECKING_AVAILABLE);
        }
        return reservedCount;
    }

    private int getser() {
     String hallName = lb9.getText();
    int hallId = 0;
    try (Connection  connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
         PreparedStatement statement =  connectionDB.prepareStatement(GET_SERVICE_ID_SQL )) {
        statement.setString(1, hallName);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                hallId = resultSet.getInt(SERVICE_ID_COLUMN );
            }
        }
    } catch (SQLException e) {
logger.severe(CHECKING_AVAILABLE);
    }
    return hallId;
    }

    public void clicktimeservicechoice(javafx.scene.input.MouseEvent mouseEvent) {
        LocalDate chosenDate = datereservation.getValue();
        if (chosenDate == null) {
            return;
        }

        servicetime.getItems().clear();

        List<String> availableTimes = new ArrayList<>();

        try {
           connectionDB = DriverManager.getConnection(DB_URL, DB_USER,getPasswordFromEnvironment());

            checkReservationStatementt =  connectionDB.prepareStatement("SELECT DISTINCT starttime FROM software.reservations WHERE date = ? AND serviceid = ?");

            checkReservationStatementt.setDate(1, Date.valueOf(chosenDate));
            checkReservationStatementt.setInt(2, getHallId());


            ResultSet resultSet = checkReservationStatementt.executeQuery();

            while (resultSet.next()) {
                availableTimes.add(resultSet.getString(START_TIME_COLUMN));
            }

       connectionDB.close();
        } catch (SQLException e) {
    logger.severe(CHECKING_AVAILABLE);
        }

        List<String> allTimes = List.of(DEFAULT_TIME, DEFAULT_TIME_1800,  DEFAULT_TIME_2000);

        List<String> availableTimesFiltered = allTimes.stream()
                .filter(time -> !availableTimes.contains(time))
                .collect(Collectors.toList());

        servicetime.getItems().addAll(availableTimesFiltered);
    }


    @FXML
    void resser(ActionEvent event) {
        LocalDate chosenDate = datereservation.getValue();
        String startTimeStr = servicetime.getValue();
        if (chosenDate == null || startTimeStr == null) {
            showAlert(SELECT_DATE_AND_TIME_MESSAGE);
            return;
        }
        String hallName = lb9.getText();

        if (hallName.isEmpty()) {
          showAlert(ENTER_HALL_NAME_MESSAGE);
            return;
        }
        LocalTime startTime = LocalTime.parse(startTimeStr);

        LocalTime endTime = startTime.plusHours(2);

        long durationHours = 2;

        try (Connection  connectionDB = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {

            int userId = getUserIdd(UserCredentials.getEmail(), UserCredentials.getPassword(), connectionDB);

            if (userId == -1) {
                showAlert(INVALID_CREDENTIALS_MESSAGE);
                return;
            }
            int hallId = gettHallIdd(hallName,  connectionDB);
            if (hallId == 0) {
                showAlert("Service not found.");
                return;
            }

            if (!isHallAvailablee(chosenDate, startTime, endTime, hallId,  connectionDB)) {
                showAlert(WAIT_ACCEPT_MESSAGE);
                return;
            }

            BigDecimal pricePerHour = getPricePerHourr(hallId, connectionDB);

            BigDecimal totalPrice = pricePerHour.multiply(BigDecimal.valueOf(durationHours));

            insertReservationn(userId, hallId, chosenDate, startTime, endTime, totalPrice,  connectionDB);

            showAlert(WAIT_ACCEPT_MESSAGE);
        } catch (SQLException e) {
  logger.severe(CHECKING_AVAILABLE);
            showAlert(BOOKING_FAILED_MESSAGE);
        }
    }

    private int gettHallIdd(String hallName, Connection  connectionDB) {
       int hallId = 0;
    try (PreparedStatement statement = connectionDB.prepareStatement(GET_SERVICE_ID_SQL )) {
        statement.setString(1, hallName);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                hallId = resultSet.getInt(SERVICE_ID_COLUMN );
            }
        }
    } catch (SQLException e) {
 logger.severe(CHECKING_AVAILABLE);
    }
    return hallId;
    }

    private int getUserIdd(String email, String password, Connection connection) throws SQLException {
        String sql = SELECT_USER_ID_QUERY;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getInt(USER_ID_COLUMN) : -1;
        }
    }

    private boolean isHallAvailablee(LocalDate date, LocalTime startTime, LocalTime endTime, int hallId,
                                     Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM software.new_table_name WHERE serviceid = ? AND date = ? AND "
                + "((starttime <= ? AND endtime >= ?) OR (starttime <= ? AND endtime >= ?))";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hallId);
            statement.setDate(2, java.sql.Date.valueOf(date));
            statement.setTime(3, java.sql.Time.valueOf(startTime));
            statement.setTime(4, java.sql.Time.valueOf(startTime));
            statement.setTime(5, java.sql.Time.valueOf(endTime));
            statement.setTime(6, java.sql.Time.valueOf(endTime));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) == 0;
        }
    }

    private BigDecimal getPricePerHourr(int hallId, Connection connection) throws SQLException {
        String sql = "SELECT price FROM software.services WHERE serviceid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hallId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBigDecimal(PRICE_COLUMN );
        }
    }

    private void insertReservationn(int userId, int hallId, LocalDate date, LocalTime startTime, LocalTime endTime,
                                    BigDecimal totalPrice, Connection connection) throws SQLException {
        String sql = "INSERT INTO software.new_table_name (userid, serviceid, date, starttime, endtime, totalprice, state) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, hallId);
            statement.setDate(3, java.sql.Date.valueOf(date));
            statement.setTime(4, java.sql.Time.valueOf(startTime));
            statement.setTime(5, java.sql.Time.valueOf(endTime));
            statement.setBigDecimal(6, totalPrice);
            statement.setString(7, "wait");
            statement.executeUpdate();
        }
    }


    @FXML
    private TextField jtxt1;

    private ObservableList<NewReservation> reservationsData = FXCollections.observableArrayList();

    @FXML
    void soso(KeyEvent event) {
        String searchText = jtxt1.getText().toLowerCase();

        ObservableList<NewReservation> filteredList = FXCollections.observableArrayList();


        for (NewReservation reservation : tabelnotification.getItems()) {
            if (String.valueOf(reservation.getHallId()).toLowerCase().contains(searchText) ||
                    reservation.getState().toLowerCase().contains(searchText) ||
                    String.valueOf(reservation.getDate()).toLowerCase().contains(searchText) ||
                    String.valueOf(reservation.getStartTime()).toLowerCase().contains(searchText) ||
                    String.valueOf(reservation.getEndTime()).toLowerCase().contains(searchText)) {
                filteredList.add(reservation);
            }
        }

        tabelnotification.getItems().clear();
        tabelnotification.getItems().addAll(filteredList);
    }

    @FXML
    private Button qq;
    @FXML
    private Button ppp;

    @FXML
    void qqq(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource(HELLO_VIEW_FXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }

    }

    @FXML
    void ppp(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource(HALL_INTER_FXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }
    }

    @FXML
    private Button ww;

    @FXML
    void www(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource(HELLO_VIEW_FXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }

    }

    @FXML
    private TableColumn<FeedbackEntry, String> FeedbackColumn;

    @FXML
    private TableColumn<FeedbackEntry, Integer> userIdColumn;

    @FXML
    private TableView<FeedbackEntry> feedbacktable;

    @FXML
    private Button viewbu;

    @FXML
    private Button wh;

    @FXML
    void buttonview(ActionEvent event) {
         if (feedbacktable == null) {
        System.err.println("feedbackTable is not initialized!");
        return;
    }
    FeedbackColumn.setCellValueFactory(new PropertyValueFactory<>("feedback"));
    userIdColumn.setCellValueFactory(new PropertyValueFactory<>(USER_ID_COLUMN));
    feedbacktable.getItems().clear();
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
         PreparedStatement statement = conn.prepareStatement("SELECT userid, feedback FROM software.new_table_name WHERE feedback IS NOT NULL");
         ResultSet resultSet = statement.executeQuery()) {

        ObservableList<FeedbackEntry> feedbackList = FXCollections.observableArrayList();
        while (resultSet.next()) {
            String feedback = resultSet.getString("feedback");
            int userId = resultSet.getInt(USER_ID_COLUMN);
            if (feedback != null) {
                FeedbackEntry feedbackEntry = new FeedbackEntry(userId, feedback);
                feedbackList.add(feedbackEntry);
            }
        }

        for (FeedbackEntry entry : feedbackList) {
            System.out.println("User ID: " + entry.getUserId());
            System.out.println("Feedback: " + entry.getFeedback());
            System.out.println("---------------------------------");
        }

        feedbacktable.setItems(feedbackList);
    } catch (SQLException e) {
 logger.severe(CHECKING_AVAILABLE);
    }
    }

    @FXML
    void whwh(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("Adminlogin.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }

    }

    @FXML
    private Button sennd;

    @FXML
    private TextArea textareaaa;

    @FXML
    private Button vb;

    @FXML
    void bvbv(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource(CUSTO_INTER_1_FXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }

    }

    @FXML
    void sendclick(ActionEvent event) {
        String feedback = textareaaa.getText();
    try {
        int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(),conn);
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
             PreparedStatement statement = conn.prepareStatement("INSERT INTO software.new_table_name (userid, feedback) VALUES (?, ?)")) {

            statement.setInt(1, userId);
            statement.setString(2, feedback);
            statement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Feedback sent successfully!");
            alert.showAndWait();
        }
    } catch (SQLException e) {
 logger.severe(CHECKING_AVAILABLE);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Failed to save feedback. Please try again.");
        alert.showAndWait();
    }

    }

    @FXML
    private Button serrr;

    @FXML
    void serser(ActionEvent event) {

        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("serviceview.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            logger.log(null, window);
        }
    }

    @FXML
    private TableView<Services> serviceviewtable;

    @FXML
    private TableColumn<Services, Integer> colm1;
    @FXML
    private TableColumn<Services, String> colm2;

    @FXML
    private TableColumn<Services, String> colm3;

    @FXML
    private TableColumn<Services, Double> colm4;

    @FXML
    private Button view;


    @FXML
    void viewsertable(ActionEvent event) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER,getPasswordFromEnvironment())) {

            int userId = getUserIdd(UserCredentials.getEmail(), UserCredentials.getPassword(), connection);
            ObservableList<Services> servicesList = FXCollections.observableArrayList();
            String servicesQuery = "SELECT * FROM software.services WHERE userid = ?";
            try (PreparedStatement servicesStatement = connection.prepareStatement(servicesQuery)) {
                servicesStatement.setInt(1, userId);
                ResultSet servicesResultSet = servicesStatement.executeQuery();
                while (servicesResultSet.next()) {
                    Services service;
                    String description = servicesResultSet.getString(DESCRIPTION_COLUMN);
                    if (description != null) {
                        service = new Services(
                                servicesResultSet.getInt(SERVICE_ID_COLUMN ),
                                servicesResultSet.getString(SERVICE_NAME_COLUMN),
                                description,
                                servicesResultSet.getDouble(PRICE_COLUMN ),
                                servicesResultSet.getInt(USER_ID_COLUMN),
                                servicesResultSet.getBytes(IMAGE_COLUMN),
                                servicesResultSet.getString(LOCATION_1)
                        );
                    } else {
                        int descriptionn = servicesResultSet.getInt(DESCRIPTION_COLUMN);
                        service = new Services(
                                servicesResultSet.getInt(SERVICE_ID_COLUMN ),
                                servicesResultSet.getString(SERVICE_NAME_COLUMN),
                                String.valueOf(descriptionn),
                                servicesResultSet.getDouble(PRICE_COLUMN ),
                                servicesResultSet.getInt(USER_ID_COLUMN),
                                servicesResultSet.getBytes(IMAGE_COLUMN),
                                servicesResultSet.getString(LOCATION_1)
                        );
                    }
                    servicesList.add(service);
                }
            }

            ObservableList<Services> hallsList = FXCollections.observableArrayList();
            String hallsQuery = "SELECT * FROM software.halls WHERE userid = ?";
            try (PreparedStatement hallsStatement = connection.prepareStatement(hallsQuery)) {
                hallsStatement.setInt(1, userId);
                ResultSet hallsResultSet = hallsStatement.executeQuery();
                while (hallsResultSet.next()) {
                    Services hall;
                    int capacity = hallsResultSet.getInt( CAPACITY_COLUMN);
                    hall = new Services(
                            hallsResultSet.getInt(HALL_ID_COLUMN),
                            hallsResultSet.getString(HALL_NAME_COLUMN),
                            String.valueOf(capacity),
                            hallsResultSet.getDouble(PRICE_PER_HOUR_COLUMN ),
                            hallsResultSet.getInt(USER_ID_COLUMN),
                            hallsResultSet.getBytes(IMAGE_COLUMN),
                            hallsResultSet.getString(LOCATION_1)
                    );
                    hallsList.add(hall);
                }
            }
            colm1.setCellValueFactory(new PropertyValueFactory<>(SERVICE_ID_COLUMNNN ));
            colm2.setCellValueFactory(new PropertyValueFactory<>(SERVICE_NAME_COLUMN));
            colm3.setCellValueFactory(new PropertyValueFactory<>(DESCRIPTION_COLUMN));
            colm4.setCellValueFactory(new PropertyValueFactory<>(PRICE_COLUMN ));
            colm5.setCellValueFactory(new PropertyValueFactory<>("imageBytes"));
            colm6.setCellValueFactory(new PropertyValueFactory<>(LOCATION_1));
            colm7.setCellValueFactory(new PropertyValueFactory<>(USER_ID_COLUMN));
            serviceviewtable.getItems().clear();
            serviceviewtable.getItems().addAll(servicesList);
            serviceviewtable.getItems().addAll(hallsList);

        } catch (SQLException e) {
    logger.severe(CHECKING_AVAILABLE);
        }
    }


    @FXML
    void handleRowSelect(MouseEvent event) {
        int selectedIndex = serviceviewtable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Object value2 = colm2.getCellData(selectedIndex);
            Object value3 = colm3.getCellData(selectedIndex);
            Object value5 = colm4.getCellData(selectedIndex);
            Object value6 = colm6.getCellData(selectedIndex);
            Object value7 = colm5.getCellData(selectedIndex);

            sernametxt.setText(value2 != null ? value2.toString() : "");
            serdes.setText(value3 != null ? value3.toString() : "");
            serpricetxt.setText(value5 != null ? value5.toString() : "");
            seridtxt.setText(value6 != null ? value6.toString() : "");

            if (value7 instanceof Blob) {
                Blob blob = (Blob) value7;
                try {
                    byte[] imageData = blob.getBytes(1, (int) blob.length());
                    Image image = new Image(new ByteArrayInputStream(imageData));
                    imageView.setImage(image);
                } catch (SQLException e) {
            logger.severe(CHECKING_AVAILABLE);
                }
            } else {
            }
        }
    }


    @FXML
    private TableColumn<Services, Integer> colm5;
    @FXML
    private TableColumn<Services, byte[]> colm6;

    @FXML
    private TableColumn<Services, String> colm7;

    @FXML
    private Button s1;

    @FXML
    private Button s2;

    @FXML
    private Button s3;

    @FXML
    private Button s4;

    @FXML
    private Button s6;

    @FXML
    private TextField serdes;

    @FXML
    private TextField seridtxt;

    @FXML
    private TextField sernametxt;

    @FXML
    private TextField serpricetxt;


    @FXML
    void addserr(ActionEvent event) {
        String description = serdes.getText();
        if (description.equalsIgnoreCase("hall")) {
            saveToHallsTable();
        } else if (description.equalsIgnoreCase("service")) {
            saveToServicesTable();
        } else {
            showAlert("Handle invalid description");
        }

    }

    private void saveToHallsTable() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Capacity");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter the capacity:");

        dialog.showAndWait().ifPresent(capacity -> {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
                int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);
                String sql = "INSERT INTO software.halls (hallname, capacity, priceperhour, location, userid, image) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, sernametxt.getText());
                statement.setInt(2, Integer.parseInt(capacity));
                statement.setBigDecimal(3, new BigDecimal(serpricetxt.getText()));
                statement.setString(4, seridtxt.getText());
                statement.setInt(5, userId);
                statement.setBytes(6, getImageBytes());
                statement.executeUpdate();
                showAlert(" halls added successfully");


                statement.close();
            } catch (SQLException e) {
                showAlert("enter image for service please");

            }
        });

    }

    private void saveToServicesTable() {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
         PreparedStatement statement = conn.prepareStatement("INSERT INTO software.services (servicename, description, price, userid, location,image) VALUES (?,?, ?, ?, ?, ?)")) {

        int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);

        statement.setString(1, sernametxt.getText());
        statement.setString(2, serdes.getText());
        statement.setBigDecimal(3, new BigDecimal(serpricetxt.getText()));
        statement.setInt(4, userId);
        statement.setString(5, seridtxt.getText());
        statement.setBytes(6, getImageBytes());
        statement.executeUpdate();

        showAlert("Service added successfully");
    } catch (SQLException e) {
logger.severe(CHECKING_AVAILABLE);
        showAlert("An error occurred while saving the service.");
    }
    }

    private byte[] getImageBytes() {
        byte[] imageData = null;
        try {
            Image image = imageView.getImage();
            if (image != null) {
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", outputStream);
                imageData = outputStream.toByteArray();
                outputStream.close();
            }

        } catch (IOException e) {
            showAlert("enter image for service please");
        }
        return imageData;
    }

    @FXML
    void deleteserr(ActionEvent event) {
       logger.severe(CHECKING_AVAILABLE);  
    }

    @FXML
   void editserr(ActionEvent event) {
    String serviceName = sernametxt.getText();
    String description = serdes.getText();
    String price = serpricetxt.getText();
    String location = seridtxt.getText();

    if (serviceName.isEmpty() || description.isEmpty() || price.isEmpty() || location.isEmpty()) {
        showAlert("Please fill in all fields");
        return;
    }

    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
        int id = getSelectedItemId();

        if (id == -1) {
            showAlert("Please select a row");
            return;
        }

        String query;
        if (description.equalsIgnoreCase("hall")) {
            query = "UPDATE software.halls SET hallname = ?, priceperhour = ? WHERE hallid = ?";
        } else if (description.equalsIgnoreCase("service")) {
            query = "UPDATE software.services SET servicename = ?, price = ? WHERE serviceid = ?";
        } else {
            showAlert("Invalid description");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, serviceName);
            statement.setBigDecimal(2, new BigDecimal(price));
            statement.setInt(3, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Data updated successfully");
            } else {
                showAlert("Failed to update data");
            }
        }
    } catch (SQLException e) {
        logger.severe(CHECKING_AVAILABLE);
        showAlert("Error: " + e.getMessage());
    }
}
    private int getSelectedItemId() {
        int selectedIndex = serviceviewtable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Object value1 = colm1.getCellData(selectedIndex);
            if (value1 != null) {
                return Integer.parseInt(value1.toString());
            }
        }
        return -1;
    }



    @FXML
    void gogo(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource(SERVICE_PROVIDER_PAGE_FXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            logger.log(null,window);
        }

    }


    @FXML
    private ImageView imageView;

    @FXML
    void uploadserp(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(IMAGE_DESCRIPTION ,PNG_EXTENSION, JPG_EXTENSION, "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(s1.getScene().getWindow());

        if (selectedFile != null) {
            try {
                Image image = new Image(new FileInputStream(selectedFile));
                imageView.setImage(image);
            } catch (IOException e) {
        logger.severe(CHECKING_AVAILABLE);
            }
        }
    }


    @FXML
    private TableColumn<Reservation, Integer> e1 = new TableColumn<>();

    @FXML
    private TableColumn<Reservation, Integer> e2 = new TableColumn<>();

    @FXML
    private TableColumn<Reservation, Integer> e3 = new TableColumn<>();

    @FXML
    private TableColumn<Reservation, Date> e4 = new TableColumn<>();

    @FXML
    private TableColumn<Reservation, Time> e5 = new TableColumn<>();

    @FXML
    private TableColumn<Reservation, Time> e6 = new TableColumn<>();

    @FXML
    private TableColumn<Reservation, Double> e7 = new TableColumn<>();

    @FXML
    private TableColumn<Reservation, Integer> e8 = new TableColumn<>();

    @FXML
    private TableColumn<Reservation, String> e9 = new TableColumn<>();

    @FXML
    private TableView<Reservation> eventtable = new TableView<>();

    @FXML
    private Button m1;

    @FXML
    private Button m2;

    @FXML
    private Button m3;

    @FXML
    private Button m4;

    @FXML
    private Button m5;


    @FXML
    void addevents(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("AddEvents.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            logger.log(null,window);
        }

    }


    @FXML
void deleteevents(ActionEvent event) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
        Reservation selectedReservation = eventtable.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {
            showAlert("No reservation selected.");
            return;
        }

        if (selectedReservation.getState().equals(DELETED_STATE)) {
            showAlert("This reservation is already deleted.");
            return;
        }

        int reservationId = selectedReservation.getReservationId();

        if (selectedReservation.getState().equals(ACCEPTED_STATE)) {
            updateReservationState(conn, reservationId, DELETED_STATE, 0);
        } else if (selectedReservation.getState().equals("wait")) {
            deleteReservation(conn, reservationId);
        }
    } catch (SQLException e) {
        logger.severe(CHECKING_AVAILABLE);
        showAlert("An error occurred while connecting to the database.");
    }
}

private void updateReservationState(Connection conn, int reservationId, String state, double totalPrice) throws SQLException {
    String updateQuery = "UPDATE software.reservations SET state = ?, totalprice = ? WHERE reservationid = ?";
    try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
        updateStatement.setString(1, state);
        updateStatement.setDouble(2, totalPrice);
        updateStatement.setInt(3, reservationId);
        int rowsUpdated = updateStatement.executeUpdate();

        if (rowsUpdated > 0) {
            showAlert("Reservation successfully deleted.");
            updateStateInNewTableName(conn, reservationId, state);
        } else {
            showAlert("Failed to update reservation state.");
        }
    }
}

private void deleteReservation(Connection conn, int reservationId) throws SQLException {
    String deleteQuery = "DELETE FROM software.reservations WHERE reservationid = ?";
    try (PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery)) {
        deleteStatement.setInt(1, reservationId);
        int rowsDeleted = deleteStatement.executeUpdate();

        if (rowsDeleted > 0) {
            showAlert("Reservation successfully deleted.");
            updateStateInNewTableName(conn, reservationId, DELETED_STATE);
        } else {
            showAlert("Failed to delete reservation.");
        }
    }
}

    private void updateStateInNewTableName(Connection conn, int reservationId, String state) throws SQLException {
        String updateQuery = UPDATE_RESERVATION_SQL;
        try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
            updateStatement.setString(1, state);
            updateStatement.setInt(2, reservationId);
            updateStatement.executeUpdate();
        }
    }

    @FXML
    void ogog(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource(SERVICE_PROVIDER_PAGE_FXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            logger.log(null, window);
        }


    }

    @FXML
    void reportevents(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("reportt.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            logger.log(null, window);
        }


    }

@FXML
void viewevents(ActionEvent event) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
        int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);

        if (userId != -1) {
            String sql = "(SELECT r.* \n" +
                    "FROM software.reservations r\n" +
                    "LEFT JOIN software.halls h ON r.hallid = h.hallid\n" +
                    "LEFT JOIN software.services s ON r.serviceid = s.serviceid\n" +
                    "WHERE h.userid = ? OR s.userid = ?)";

            ObservableList<Reservation> reservationsList = FXCollections.observableArrayList();

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, userId);
                statement.setInt(2, userId);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Reservation reservation = new Reservation.ReservationBuilder(
                                resultSet.getInt(RESERVATION_ID_COLUMN),
                                resultSet.getInt(USER_ID_COLUMN),
                                resultSet.getInt(HALL_ID_COLUMN),
                                resultSet.getDate("date"),
                                resultSet.getTime(START_TIME_COLUMN),
                                resultSet.getTime(END_TIME_COLUMN)
                            )
                            .totalPrice(resultSet.getDouble(TOTAL_PRICE_COLUMN))
                            .serviceId(resultSet.getInt(SERVICE_ID_COLUMN ))
                            .state(resultSet.getString(STATE_COLUMN ))
                            .build();
                        reservationsList.add(reservation);
                    }
                }

                e1.setCellValueFactory(new PropertyValueFactory<>(RESERVATION_ID_COLUMN));
                e2.setCellValueFactory(new PropertyValueFactory<>(USER_ID_COLUMN));
                e3.setCellValueFactory(new PropertyValueFactory<>(HALL_ID_COLUMN));
                e4.setCellValueFactory(new PropertyValueFactory<>("date"));
                e5.setCellValueFactory(new PropertyValueFactory<>(START_TIME_COLUMN));
                e6.setCellValueFactory(new PropertyValueFactory<>(END_TIME_COLUMN));
                e7.setCellValueFactory(new PropertyValueFactory<>(TOTAL_PRICE_COLUMN));
                e8.setCellValueFactory(new PropertyValueFactory<>(SERVICE_ID_COLUMNNN  ));
                e9.setCellValueFactory(new PropertyValueFactory<>(STATE_COLUMN ));

                eventtable.getItems().clear();
                eventtable.getItems().addAll(reservationsList);
            }
        } else {
            System.out.println(USER_PRINT);
        }
    } catch (SQLException e) {
   logger.severe(CHECKING_AVAILABLE);
    }
}


    @FXML
    private Button to;

    @FXML
    void toot(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("eventsprov.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            logger.log(null,window);
        }

    }


    @FXML
    private Label timerlabel;
    @FXML
    private Button timerrr;


    @FXML
    private Button bvbvbvbv;

    @FXML
    void weeee(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("booking.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            logger.log(Level.SEVERE,  WINDOW_OPENING_ERROR_MESSAGE, e);
        }


    }

    @FXML
    private Button asass;

    @FXML
    void sasa(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("reservationnoti.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            logger.log(Level.SEVERE,  WINDOW_OPENING_ERROR_MESSAGE, e);
        }


    }


    @FXML
    private TableView<Reservation> adminviewstable;

    @FXML
    private TableColumn<Reservation, Integer> cc10;

    @FXML
    private TableColumn<Reservation, Integer> cc11;

    @FXML
    private TableColumn<Reservation, Integer> cc22;

    @FXML
    private TableColumn<Reservation, Integer> cc33;

    @FXML
    private TableColumn<Reservation, Data> cc44;

    @FXML
    private TableColumn<Reservation, Time> cc55;

    @FXML
    private TableColumn<Reservation, Time> cc66;

    @FXML
    private TableColumn<Reservation, Double> cc77;

    @FXML
    private TableColumn<Reservation, Integer> cc88;

    @FXML
    private TableColumn<Reservation, String> cc99;
    @FXML
    private Button showbuttonn;

  void showshow(ActionEvent event) {
    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {

        ObservableList<Reservation> reservationsList = FXCollections.observableArrayList();
        String query = "SELECT * FROM software.reservations";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            // Setup cell value factories and clear existing items from TableView
            cc11.setCellValueFactory(new PropertyValueFactory<>(RESERVATION_ID_COLUMN));
            cc22.setCellValueFactory(new PropertyValueFactory<>(USER_ID_COLUMN));
            cc33.setCellValueFactory(new PropertyValueFactory<>(HALL_ID_COLUMN));
            cc44.setCellValueFactory(new PropertyValueFactory<>("date"));
            cc55.setCellValueFactory(new PropertyValueFactory<>(START_TIME_COLUMN));
            cc66.setCellValueFactory(new PropertyValueFactory<>(END_TIME_COLUMN));
            cc77.setCellValueFactory(new PropertyValueFactory<>(TOTAL_PRICE_COLUMN));
            cc88.setCellValueFactory(new PropertyValueFactory<>(SERVICE_ID_COLUMNNN  ));
            cc99.setCellValueFactory(new PropertyValueFactory<>(STATE_COLUMN ));
            adminviewstable.getItems().clear();
            
            // Iterate over the result set
            while (resultSet.next()) {
                Reservation reservation = new Reservation.ReservationBuilder(
                        resultSet.getInt(RESERVATION_ID_COLUMN),
                        resultSet.getInt(USER_ID_COLUMN),
                        resultSet.getInt(HALL_ID_COLUMN),
                        resultSet.getDate("date"),
                        resultSet.getTime(START_TIME_COLUMN),
                        resultSet.getTime(END_TIME_COLUMN)
                    )
                    .totalPrice(resultSet.getDouble(TOTAL_PRICE_COLUMN))
                    .serviceId(resultSet.getInt(SERVICE_ID_COLUMN ))
                    .state(resultSet.getString(STATE_COLUMN ))
                    .build();
                reservationsList.add(reservation);
            }
        }
        
        // Add all reservations to the TableView
        adminviewstable.getItems().addAll(reservationsList);
    } catch (SQLException e) {
        System.err.println("Error while checking availability: " + e.getMessage());
    }
}



    @FXML
    private Button backkc;

    @FXML
    private TableView<HallReportData> hallreport;

    @FXML
    private TableColumn<HallReportData, String> hnr;

    @FXML
    private TableColumn<HallReportData, Integer> numberhr;

    @FXML
    private TableColumn<HallReportData, Integer> numbersrr;

    @FXML
    private TableColumn<HallReportData, Double> phr;

    @FXML
    private TableColumn<HallReportData, Double> psr;

    @FXML
    private Label reportlabel;

    @FXML
    private TextField reporttext;

    @FXML
    private Button seeeeee;

    @FXML
    private TableView<HallReportData> servicereport;

    @FXML
    private TableColumn<HallReportData, String> snr;

    @FXML
    private TableColumn<HallReportData, Double> tphr;

    @FXML
    private TableColumn<HallReportData, Double> tpsr;

    @FXML
    void backreport(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("eventsprov.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE,  WINDOW_OPENING_ERROR_MESSAGE, e);
        }


    }

    @FXML
    private Button popp;

    @FXML
    void poppp(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("service.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            logger.log(Level.SEVERE,  WINDOW_OPENING_ERROR_MESSAGE, e);
        }

    }

    @FXML
    void shoereport(ActionEvent event) {
        populateHallReport();

        populateServiceReport();
    }

    private void populateHallReport() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER,getPasswordFromEnvironment())) {
            int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);

            if (userId != -1) {

                String sql = "SELECT h.hallname, h.priceperhour, COUNT(r.reservationid) AS num_reservations, SUM(r.totalprice) AS total_price " +
                        "FROM software.halls h " +
                        "LEFT JOIN software.reservations r ON h.hallid = r.hallid " +
                        "WHERE h.userid = ? " +
                        "GROUP BY h.hallname, h.priceperhour";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, userId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        ObservableList<HallReportData> hallData = FXCollections.observableArrayList();

                        while (resultSet.next()) {
                            String hallName = resultSet.getString(HALL_NAME_COLUMN);
                            double pricePerHour = resultSet.getDouble(PRICE_PER_HOUR_COLUMN );
                            int numberOfReservations = resultSet.getInt("num_reservations");
                            double totalPrice = resultSet.getDouble("total_price");
                            hallData.add(new HallReportData(hallName, pricePerHour, numberOfReservations, totalPrice));
                        }

                        hnr.setCellValueFactory(new PropertyValueFactory<>(HALL_NAME_COLUMN));
                        phr.setCellValueFactory(new PropertyValueFactory<>( PRICE_PER_HOUR_COLUMN_NAME));
                        tphr.setCellValueFactory(new PropertyValueFactory<>(TOTAL_PRICE_COLUMN));
                        numberhr.setCellValueFactory(new PropertyValueFactory<>("numberOfReservations"));

                        hallreport.setItems(hallData);
                    }
                }
            } else {
                 logger.severe(USER_PRINT);
            
            }
        } catch (SQLException e) {
  logger.severe(CHECKING_AVAILABLE);
        }
    }

    private void populateServiceReport() {

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
            int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);

            if (userId != -1) {

                String sql = "SELECT s.servicename, s.price, COUNT(r.reservationid) AS num_reservations, SUM(r.totalprice) AS total_price " +
                        "FROM software.services s " +
                        "LEFT JOIN software.reservations r ON s.serviceid = r.serviceid " +
                        "WHERE s.userid = ? " +
                        "GROUP BY s.servicename, s.price";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, userId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        ObservableList<HallReportData> serviceData = FXCollections.observableArrayList();

                        while (resultSet.next()) {
                            String serviceName = resultSet.getString(SERVICE_NAME_COLUMN);
                            double price = resultSet.getDouble(PRICE_COLUMN );
                            int numberOfReservations = resultSet.getInt("num_reservations");
                            double totalPrice = resultSet.getDouble("total_price");
                            serviceData.add(new HallReportData(serviceName, price, numberOfReservations, totalPrice));
                        }

                        snr.setCellValueFactory(new PropertyValueFactory<>(HALL_NAME_COLUMN));
                        psr.setCellValueFactory(new PropertyValueFactory<>( PRICE_PER_HOUR_COLUMN_NAME));
                        tpsr.setCellValueFactory(new PropertyValueFactory<>(TOTAL_PRICE_COLUMN));
                        numbersrr.setCellValueFactory(new PropertyValueFactory<>("numberOfReservations"));

                        servicereport.setItems(serviceData);
                    }
                }
            } else {
                logger.severe(USER_PRINT);
            }
        } catch (SQLException e) {
     logger.severe(CHECKING_AVAILABLE);
        }
    }


    private Connection conn;
    @FXML
    private Button callccuulTE;

    private Connection getConnection() throws Exception {

        return DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
    }

    @FXML
    void calculate(ActionEvent event) {
           try {
        String dateString = reporttext.getText().trim();
        if (dateString.isEmpty()) {
            reportlabel.setText("Please enter a date.");
            return;
        }
        String[] parts = dateString.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        try (Connection conn = getConnection();
             PreparedStatement hallsStmt = conn.prepareStatement(
                     "SELECT SUM(r.totalprice) " +
                             "FROM software.reservations r " +
                             "INNER JOIN software.halls h ON r.hallid = h.hallid " +
                             "WHERE h.userid = ? AND EXTRACT(YEAR FROM r.date) = ? AND EXTRACT(MONTH FROM r.date) = ?");
             PreparedStatement servicesStmt = conn.prepareStatement(
                     "SELECT SUM(r.totalprice) " +
                             "FROM software.reservations r " +
                             "INNER JOIN software.services s ON r.serviceid = s.serviceid " +
                             "WHERE s.userid = ? AND EXTRACT(YEAR FROM r.date) = ? AND EXTRACT(MONTH FROM r.date) = ?")) {

            int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);

            hallsStmt.setInt(1, userId);
            hallsStmt.setInt(2, year);
            hallsStmt.setInt(3, month);
            try (ResultSet hallsRs = hallsStmt.executeQuery()) {
                double hallsTotalPrice = 0;
                if (hallsRs.next()) {
                    hallsTotalPrice = hallsRs.getDouble(1);
                }
                double servicesTotalPrice = 0;
                servicesStmt.setInt(1, userId);
                servicesStmt.setInt(2, year);
                servicesStmt.setInt(3, month);
                try (ResultSet servicesRs = servicesStmt.executeQuery()) {
                    if (servicesRs.next()) {
                        servicesTotalPrice = servicesRs.getDouble(1);
                    }
                }
                double totalPrice = hallsTotalPrice + servicesTotalPrice;
                reportlabel.setText(String.valueOf(totalPrice));
            }
        }
    } catch (SQLException e) {
  logger.severe(CHECKING_AVAILABLE);
        // Handle SQLException appropriately
    } catch (Exception e) {
 logger.severe(CHECKING_AVAILABLE);
    }
    }

    @FXML
    private Button p;

    @FXML
    void pp(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource(CUSTO_INTER_1_FXML));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }

    }

    @FXML
    void calenderclick(MouseEvent event) {
          logger.severe(CHECKING_AVAILABLE); 
       
    }


    @FXML
    private ImageView eventsimage;

    @FXML
    private TextField r1;

    @FXML
    private TextField r2;

    @FXML
    private TextField r3;

    @FXML
    private TextField r4;

    @FXML
    private TextField r5;

    @FXML
    private TextField r6;

    @FXML
    private Button x1;

    @FXML
    private Button x2;

    @FXML
    private Button x3;
    private File selectedFile;
    private byte[] imageBytes;

    @FXML
    void uploadimageevents(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        selectedFile = fileChooser.showOpenDialog(x1.getScene().getWindow());

        if (selectedFile != null) {
            javafx.scene.image.Image image = new javafx.scene.image.Image(selectedFile.toURI().toString());
            eventsimage.setImage(image);

            try (FileInputStream fis = new FileInputStream(selectedFile)) {
                imageBytes = fis.readAllBytes();
            } catch (Exception e) {
   logger.severe(CHECKING_AVAILABLE);
            }
        }
    }


    @FXML
    void populateHallChoiceBox() {
     try (Connection conn = getConnection();
         PreparedStatement statement = conn.prepareStatement("SELECT hallid, hallname FROM software.halls WHERE userid = ?")) {
        r8.getItems().clear();
        int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);
        statement.setInt(1, userId);
        try (ResultSet resultSet = statement.executeQuery()) {
            List<String> hallList = new ArrayList<>();
            while (resultSet.next()) {
                int hallId = resultSet.getInt(HALL_ID_COLUMN);
                String hallName = resultSet.getString(HALL_NAME_COLUMN);
                hallList.add(hallId + "-" + hallName);
            }
            r8.getItems().addAll(hallList);
            r8.setOnAction(event -> {
                String selectedHall = r8.getValue();
                if (selectedHall != null) {
                    r8.setValue(selectedHall);
                }
            });
        }
    } catch (SQLException e) {
logger.severe(CHECKING_AVAILABLE);
        // Handle SQLException appropriately
    } catch (Exception e) {
   logger.severe(CHECKING_AVAILABLE);
    }
    }


    @FXML
    void saveevents(ActionEvent event) {
String eventName = r1.getText();
    String eventLocation = r3.getText();
    String eventDescription = r4.getText();
    String selectedHall = r8.getValue();
    if (selectedHall != null) {
        int hallId = Integer.parseInt(selectedHall.split("-")[0]);
        try (Connection conn = getConnection()) {
            String eventSql = "INSERT INTO software.events (event_name, event_date, description, location, hallid, organizer_id, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String reservationSql = "INSERT INTO software.reservations (userid, hallid, date, starttime, endtime, totalprice, state, eventsid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);
            try (PreparedStatement eventStatement = conn.prepareStatement(eventSql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement reservationStatement = conn.prepareStatement(reservationSql)) {
                eventStatement.setString(1, eventName);
                eventStatement.setDate(2, Date.valueOf(r2.getText()));
                eventStatement.setString(3, eventDescription);
                eventStatement.setString(4, eventLocation);
                eventStatement.setInt(5, hallId);
                eventStatement.setInt(6, userId);
                byte[] imageData = imageToByteArray(eventsimage.getImage());
                eventStatement.setBytes(7, imageData);
                int eventRowsInserted = eventStatement.executeUpdate();
                int eventId = 0;
                ResultSet generatedKeys = eventStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    eventId = generatedKeys.getInt(1);
                }
                if (eventRowsInserted > 0 && eventId != 0) {
                    reservationStatement.setInt(1, userId);
                    reservationStatement.setInt(2, hallId);
                    reservationStatement.setDate(3, Date.valueOf(r2.getText()));
                    reservationStatement.setTime(4, Time.valueOf(r9.getValue()));
                    reservationStatement.setTime(5, Time.valueOf(r10.getValue()));
                    reservationStatement.setBigDecimal(6, BigDecimal.valueOf(0));
                    reservationStatement.setString(7, ACCEPTED_STATE );
                    reservationStatement.setInt(8, eventId);
                    int reservationRowsInserted = reservationStatement.executeUpdate();
                    if (reservationRowsInserted > 0) {
                        showAlert("Reservation saved successfully!");
                    }
                }
            }
        } catch (SQLException e) {
logger.severe(CHECKING_AVAILABLE);
        } catch (Exception e) {
       logger.severe(CHECKING_AVAILABLE);
        }
    } else {
        showAlert("Please select a hall.");
    }
    }

    @FXML
    void cancleevents(ActionEvent event) {
        showTicketDialog();
    }

    private void showTicketDialog() {

        TextField ticketTypeField = new TextField();
        TextField priceField = new TextField();
        TextField availableQuantityField = new TextField();
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();

        GridPane ticketDialogGrid = new GridPane();
        ticketDialogGrid.addRow(0, new javafx.scene.control.Label("Ticket Type:"), ticketTypeField);
        ticketDialogGrid.addRow(1, new javafx.scene.control.Label("Price:"), priceField);
        ticketDialogGrid.addRow(2, new javafx.scene.control.Label("Available Quantity:"), availableQuantityField);
        ticketDialogGrid.addRow(3, new javafx.scene.control.Label("Start Date:"), startDatePicker);
        ticketDialogGrid.addRow(4, new javafx.scene.control.Label("End Date:"), endDatePicker);

        Alert ticketDialog = new Alert(Alert.AlertType.NONE);
        ticketDialog.setTitle("Add Ticket");
        ticketDialog.setHeaderText("Enter Ticket Details");
        ticketDialog.getDialogPane().setContent(ticketDialogGrid);

        ButtonType saveButtonType = new ButtonType("Save");
        ButtonType closeButtonType = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        ticketDialog.getButtonTypes().addAll(saveButtonType, closeButtonType);

        Stage stage = (Stage) ticketDialog.getDialogPane().getScene().getWindow();
        Button saveButton = (Button) ticketDialog.getDialogPane().lookupButton(saveButtonType);
        Button closeButton = (Button) ticketDialog.getDialogPane().lookupButton(closeButtonType);
        saveButton.addEventFilter(ActionEvent.ACTION, e -> {
            e.consume();
            String eventname = r1.getText();
            saveTicketToDatabase(ticketTypeField.getText(), priceField.getText(), availableQuantityField.getText(), startDatePicker.getValue(), endDatePicker.getValue(), eventname);
            ticketDialog.close();
        });
        closeButton.addEventFilter(ActionEvent.ACTION, e -> ticketDialog.close());

        ticketDialog.showAndWait();
    }


    private void saveTicketToDatabase(String ticketType, String price, String availableQuantity, LocalDate startDate, LocalDate endDate, String eventId) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER,getPasswordFromEnvironment())) {

            String sql = "INSERT INTO software.tickets (ticket_type, price, available_quantity, start_date, end_date, event_name) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, ticketType);
                statement.setBigDecimal(2, new BigDecimal(price));
                statement.setInt(3, Integer.parseInt(availableQuantity));
                statement.setDate(4, Date.valueOf(startDate));
                statement.setDate(5, Date.valueOf(endDate));
                statement.setString(6, eventId);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
 logger.severe(CHECKING_AVAILABLE);

        }
    }

    private Stage primaryStage;


    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Ticket Management");


            Button addButton = new Button("Add Ticket");
            addButton.setOnAction(this::cancleevents);

            GridPane root = new GridPane();
            root.add(addButton, 0, 0);

            Scene scene = new Scene(root, 300, 200);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
logger.severe(CHECKING_AVAILABLE);
        }
    }


    @FXML
    private Button x10;
    @FXML
    private ChoiceBox<String> r8 = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> r10 = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> r9 = new ChoiceBox<>();


    @FXML
    private TableColumn<Event, Timestamp> creationdate;

    @FXML
    private TableColumn<Event, String> eventdescription;

    @FXML
    private TableColumn<Event, Integer> eventhallid;

    @FXML
    private TableColumn<Event, Integer> eventid;

    @FXML
    private TableColumn<Event, byte[]> eventimage;

    @FXML
    private TableColumn<Event, String> eventlocation;

    @FXML
    private TableColumn<Event, Integer> eventorgid;

    @FXML
    private TableColumn<Event, Date> eventsdate;

    @FXML
    private TableColumn<Event, String> eventsname;

    @FXML
    private TableView<Event> eventsviewtable;
    @FXML
    private Button bbbbbb;

    @FXML
    void eventsshow(ActionEvent event) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {

            int userId = getUserId(UserCredentials.getEmail(), UserCredentials.getPassword(), conn);

            if (userId != -1) {
                String sql = "SELECT e.event_id, e.event_name, e.event_date, e.location, e.description, e.organizer_id, e.creation_date, e.hallid \n" +
                        "FROM software.events e\n" +
                        "JOIN software.halls h ON e.hallid = h.hallid\n" +
                        "WHERE h.userid = ?";

                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, userId);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        ArrayList<Event> reservations = new ArrayList<>();
                        while (resultSet.next()) {
                            int eventId = resultSet.getInt("event_id");
                            String eventName = resultSet.getString("event_name");
                            Date eventDate = resultSet.getDate("event_date");
                            String location = resultSet.getString(LOCATION_1);
                            String description = resultSet.getString(DESCRIPTION_COLUMN);
                            int organizerId = resultSet.getInt("organizer_id");
                            Timestamp creationDate = resultSet.getTimestamp("creation_date");

                            int hallId = resultSet.getInt(HALL_ID_COLUMN);


                            reservations.add(new Event(eventId, eventName, eventDate, location, description, organizerId, creationDate, hallId));

                        }
                        eventid.setCellValueFactory(new PropertyValueFactory<>("eventId"));
                        eventsname.setCellValueFactory(new PropertyValueFactory<>("eventName"));
                        eventdescription.setCellValueFactory(new PropertyValueFactory<>(DESCRIPTION_COLUMN));
                        eventlocation.setCellValueFactory(new PropertyValueFactory<>(LOCATION_1));
                        eventsdate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
                        eventorgid.setCellValueFactory(new PropertyValueFactory<>("organizerId"));
                        creationdate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

                        eventhallid.setCellValueFactory(new PropertyValueFactory<>(HALL_ID_COLUMN));



                        eventsviewtable.getItems().clear();


                        eventsviewtable.getItems().addAll(reservations);
                    }
                }
            } else {
          logger.severe(USER_PRINT);


               
            }
        } catch (SQLException e) {
    logger.severe(CHECKING_AVAILABLE);
        }

    }

    @FXML
    private Button bnbn;

    @FXML
    void gotoevent(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("eventspage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }

    }


    @FXML
    private ChoiceBox<Event> Eventname = new ChoiceBox<Event>();

    @FXML
    private TextField avqu;

    @FXML
    private Button buyyiinngg;

    @FXML
    private Button cancllleeee;

    @FXML
    private TextField entxt;

    @FXML
    private TextField pricece;

    @FXML
    private ChoiceBox<String> quantity = new ChoiceBox<>();

    @FXML
    private ChoiceBox<String> tickettype = new ChoiceBox<>();

    @FXML
    private TextField tickittype;

    @FXML
    private TextField totalpavailability;

    private void populateEventChoiceBox() {
       try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
         PreparedStatement statement = conn.prepareStatement("SELECT event_id, event_name, event_date FROM software.events");
         ResultSet resultSet = statement.executeQuery()) {
        ObservableList<Event> events = FXCollections.observableArrayList();
        while (resultSet.next()) {
            int eventId = resultSet.getInt("event_id");
            String eventName = resultSet.getString("event_name");
            LocalDate eventDate = resultSet.getDate("event_date").toLocalDate();
            events.add(new Event(eventId, eventName, eventDate));
        }
        Eventname.setItems(events);
    } catch (SQLException e) {
  logger.severe(CHECKING_AVAILABLE);
    }
    }

    @FXML
    void handleEventSelection(MouseEvent event) {
        Event selectedEvent = Eventname.getValue();
        if (selectedEvent != null) {
            populateTicketChoiceBox(selectedEvent.getEventName());
        }
    }

    private void populateTicketChoiceBox(String eventName) {
         try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment());
         PreparedStatement ticketStatement = conn.prepareStatement("SELECT ticket_type, price FROM software.tickets WHERE event_name = ?")) {
        
        ticketStatement.setString(1, eventName);
        try (ResultSet resultSet = ticketStatement.executeQuery()) {
            ObservableList<String> tickets = FXCollections.observableArrayList();
            while (resultSet.next()) {
                String ticketType = resultSet.getString("ticket_type");
                double price = resultSet.getDouble(PRICE_COLUMN );
                String ticketInfo = ticketType + " - $" + price;
                tickets.add(ticketInfo);
            }
            tickettype.setItems(tickets);
        }
    } catch (SQLException e) {
logger.severe(CHECKING_AVAILABLE);
    }
    }

    @FXML
    void buying(ActionEvent event) {
    logger.severe(CHECKING_AVAILABLE);
    }

    @FXML
    void canclebuu(ActionEvent event) {
    logger.severe(CHECKING_AVAILABLE);
    }


    @FXML
    private Button ticketcu;

    @FXML
    void customerTicket(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("reservetecket.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }

    }

    public void ticketclick(MouseEvent mouseEvent) {
       logger.severe(CHECKING_AVAILABLE);
}

    @FXML
    private TextField des;

    @FXML
    private TableColumn<packge, String> descriptionColumn;

    @FXML
    private Button discounttt;

    @FXML
    private Button f;

    @FXML
    private Button ff;

    @FXML
    private TableColumn<packge, String> includesColumn;

    @FXML
    private TextField innc;

    @FXML
    private TableColumn<packge, Integer> maxGuestsColumn;

    @FXML
    private TextField mguest;

    @FXML
    private TableColumn<packge, Integer> packageIdColumn;

    @FXML
    private TextField pid;

    @FXML
    private TextField pname;

    @FXML
    private TableColumn<packge, String> pnameecolumn;

  
  final String PRICE_PER_HOUR_COLUMN =  PRICE_PER_HOUR_COLUMN_NAME;
      @FXML
    private TextField price;

    @FXML
    private TableColumn<packge, Double> priceColumn;

    @FXML
    private Button showwwwwwwwwwww;

    @FXML
    private TableView<packge> tableeee=new TableView<>();

    @FXML
    void deleteepac(ActionEvent event) {
   logger.severe(CHECKING_AVAILABLE);

    }

    @FXML
    void getadd(ActionEvent event) {
        try {
            Parent root;

            root = FXMLLoader.load(getClass().getResource("Addpackage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene;
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
       logger.severe(CHECKING_AVAILABLE);
        }

    }



    @FXML
    void getshow(ActionEvent event) {
       ObservableList<packge> data = FXCollections.observableArrayList();
    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
        String query = "SELECT * FROM software.wedding_packages";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int packageId = resultSet.getInt("package_id");
                String packageName = resultSet.getString("package_name");
                String description = resultSet.getString(DESCRIPTION_COLUMN);
                double price = resultSet.getDouble(PRICE_COLUMN );
                int maxGuests = resultSet.getInt("max_guests");
                String includes = resultSet.getString("includes");
                String[] includesArray = includes.split(",");
                data.add(new packge(packageId, packageName, description, price, maxGuests, includesArray));
            }
            packageIdColumn.setCellValueFactory(new PropertyValueFactory<>("packageId"));
            pnameecolumn.setCellValueFactory(new PropertyValueFactory<>("packageName"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>(DESCRIPTION_COLUMN));
            priceColumn.setCellValueFactory(new PropertyValueFactory<>(PRICE_COLUMN ));
            maxGuestsColumn.setCellValueFactory(new PropertyValueFactory<>("maxGuests"));
            includesColumn.setCellValueFactory(new PropertyValueFactory<>("includes"));
            tableeee.setItems(data);
        }
    } catch (SQLException e) {
logger.severe(CHECKING_AVAILABLE);
    }
    }


    @FXML
    private Button ppadd;

    @FXML
    private TextField ppdesc;

    @FXML
    private TextField ppid;

    @FXML
    private TextField ppincludes;

    @FXML
    private TextField ppmax;

    @FXML
    private TextField ppname;

    @FXML
    private TextField ppprice;

    @FXML
    void getppadd(ActionEvent event) {
     try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
        String query = "INSERT INTO software.wedding_packages (package_id, package_name, description, price, max_guests, includes) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(ppid.getText()));
            preparedStatement.setString(2, ppname.getText());
            preparedStatement.setString(3, ppdesc.getText());
            preparedStatement.setDouble(4, Double.parseDouble(ppprice.getText()));
            preparedStatement.setInt(5, Integer.parseInt(ppmax.getText()));
            String[] includesArray = ppincludes.getText().split(",");
            Array array = connection.createArrayOf("text", includesArray);
            preparedStatement.setArray(6, array);
            int rowsInserted = preparedStatement.executeUpdate();
            showAlert("package added successfully");
            if (rowsInserted > 0) {
                ppid.clear();
                ppname.clear();
                ppdesc.clear();
                ppprice.clear();
                ppmax.clear();
                ppincludes.clear();
            }
        }
    } catch (SQLException e) {
         logger.severe(CHECKING_AVAILABLE);
    }  
    }

    @FXML
    void getdiss(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, getPasswordFromEnvironment())) {
        double currentPrice = Double.parseDouble(price.getText());
        double discountedPrice;
        if (currentPrice >= 5000) {
            discountedPrice = currentPrice * 0.9;
            showAlert("Discount applied: 10% off");
        } else if (currentPrice == 8000) {
            discountedPrice = currentPrice * 0.8;
            showAlert("Discount applied: 20% off");
        } else if (currentPrice < 4000) {
            showAlert("Cannot apply discount for prices less than 4000.");
            return;
        } else {
            discountedPrice = currentPrice;
        }
        String updateQuery = "UPDATE software.wedding_packages SET price = ? WHERE price = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setDouble(1, discountedPrice);
            updateStatement.setDouble(2, currentPrice);
            updateStatement.executeUpdate();
        }
        getshow(event);
    } catch (SQLException e) {
    logger.severe(CHECKING_AVAILABLE);
       
    }
    }
}








