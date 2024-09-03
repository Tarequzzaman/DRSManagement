package com.drsmanagement;

import Model.DisesterTableView;
import Model.DisasterDetails;
import constant.AdminViewConstants;
import constant.UsersConstant;
import Model.SessionManager;
import Model.User;
import database.DisasterService;
import database.UserService;
import enam.UserRole;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import utils.AlertUtil;

public class AdminDashboardController implements Initializable {

    @FXML
    private TableView<DisesterTableView> tableView; // For "Pending" tab

    @FXML
    private TableColumn<DisesterTableView, Integer> disesterId;
    @FXML
    private TableColumn<DisesterTableView, String> disasterTitle;
    @FXML
    private TableColumn<DisesterTableView, ComboBox<String>> assignedGroup;
    @FXML
    private TableColumn<DisesterTableView, ComboBox<String>> priority;
    @FXML
    private TableColumn<DisesterTableView, Button> details;
    @FXML
    private TableColumn<DisesterTableView, Button> action;

    @FXML
    private TableView<DisesterTableView> tableView1; // For "Assessed" tab

    @FXML
    private TableColumn<DisesterTableView, Integer> disesterId1;
    @FXML
    private TableColumn<DisesterTableView, String> disasterTitle1;
    @FXML
    private TableColumn<DisesterTableView, String> assignedGroup1;
    @FXML
    private TableColumn<DisesterTableView, String> priority1;
    @FXML
    private TableColumn<DisesterTableView, Button> details1;

    @FXML
    private Button logOut;

    @FXML
    private Label userName;
    @FXML
    private Tab userManagementTab;
    @FXML
    private TextField findUser;
    @FXML
    private Button find;
    @FXML
    private Label FindId;
    @FXML
    private Button update;
    @FXML
    private TextField FindFirstName;
    @FXML
    private TextField FindLastName;
    @FXML
    private TextField FindPhone;
    @FXML
    private ComboBox<String> FindUserType;

    private UserService userService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (SessionManager.isLoggedIn()) {
            User currentUser = SessionManager.getCurrentUser();
            userName.setText("Welcome, " + currentUser.getFirstName());
        } else {
            // Handle case where no user is signed in (optional)
            userName.setText("Please sign in.");
        }

        userService = new UserService();
        this.FindUserType.getItems().addAll(UsersConstant.userRole);

        setupPendingTab();
        setupAssessedTab();

    }

    private void setupPendingTab() {
        disesterId.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        disasterTitle.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));
        assignedGroup.setCellValueFactory(new PropertyValueFactory<>("assignedGroup"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        details.setCellValueFactory(new PropertyValueFactory<>("details"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));

        // Load initial data into the "Pending" tab
        loadPendingTableData();
    }

    private void loadPendingTableData() {
        DisasterService disasterService = new DisasterService();

        // Fetch all pending disasters from the database (CSV)
        List<DisasterDetails> pendingDisasters = disasterService.getPendingDisasters();

        // Create an observable list for TableView data
        ObservableList<DisesterTableView> data = FXCollections.observableArrayList();
        String[] allDepartment = AdminViewConstants.ASSIGNED_DEPARTMENTS;
        String[] priorities = AdminViewConstants.PRIORITY_LEVELS;

        for (DisasterDetails disaster : pendingDisasters) {
            DisesterTableView disesterTableView = new DisesterTableView(
                    disaster.getDisasterId(),
                    disaster.getDisasterTitle(),
                    allDepartment,
                    allDepartment[0],
                    AdminViewConstants.PRIORITY_LEVELS,
                    priorities[0]
            );
            data.add(disesterTableView);
        }

        tableView.setItems(data);

        // Add action handling for buttons in each row
        for (DisesterTableView item : data) {
            item.getDetails().setOnAction(event -> openDetailsWindow(item));

            item.getAction().setOnAction(event -> {
                updateDisaster(item);
                reloadTableData();
            });
        }
    }

    private void updateDisaster(DisesterTableView item) {
        // Get the selected values from the ComboBoxes
        String assignedGroup = item.getAssignedGroup().getValue();
        String priority = item.getPriority().getValue();

        // Create a DisasterService instance to update the disaster in the CSV
        DisasterService disasterService = new DisasterService();

        // Update the disaster in the CSV
        disasterService.updateDisaster(item.getDisasterId(), assignedGroup, priority, "dispatched");
    }

    private void setupAssessedTab() {
        disesterId1.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        disasterTitle1.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));
        assignedGroup1.setCellValueFactory(new PropertyValueFactory<>("assignedGroupText"));
        priority1.setCellValueFactory(new PropertyValueFactory<>("priorityText"));
        details1.setCellValueFactory(new PropertyValueFactory<>("details"));
        loadAssessedTableData();
    }

    private void loadAssessedTableData() {
        DisasterService disasterService = new DisasterService();

        // Fetch all assessed disasters from the database (CSV)
        List<DisasterDetails> assessedDisasters = disasterService.getAssessedDisasters();

        // Create an observable list for TableView data
        ObservableList<DisesterTableView> data = FXCollections.observableArrayList();

        for (DisasterDetails disaster : assessedDisasters) {
            DisesterTableView disesterTableView = new DisesterTableView(
                    disaster.getDisasterId(),
                    disaster.getDisasterTitle(),
                    disaster.getDesignatedDepartment(),
                    disaster.getPriority()
            );
            data.add(disesterTableView);
        }

        tableView1.setItems(data);

        // Add action handling for the "View Details" button in each row
        for (DisesterTableView item : data) {
            item.getDetails().setOnAction(event -> openDetailsWindow(item));
        }
    }

    private void reloadTableData() {
        tableView.getItems().clear();
        tableView1.getItems().clear();
        loadPendingTableData();
        loadAssessedTableData();

    }

    private void openDetailsWindow(DisesterTableView item) {
        DisasterService disasterService = new DisasterService();
        UserService userService = new UserService();

        // Fetch the disaster details using the disaster ID
        DisasterDetails disasterDetails = disasterService.getDisasterById(item.getDisasterId());

        if (disasterDetails != null) {
            // Fetch the user details using the submittedBy field
            User reporter = userService.findUserById(disasterDetails.getSubmittedBy());

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DisasterDetails.fxml"));
                Parent root = loader.load();

                DisasterDetailsController controller = loader.getController();
                controller.setDetails(
                        disasterDetails.getDisasterTitle(),
                        disasterDetails.getDetail(),
                        disasterDetails.getPhone(),
                        disasterDetails.getLocation(),
                        disasterDetails.getState(),
                        disasterDetails.getStatus(),
                        reporter != null ? reporter.getFirstName() + " " + reporter.getLastName() : "Unknown");

                Scene detailsScene = new Scene(root);
                Stage detailsStage = new Stage();
                detailsStage.setScene(detailsScene);
                detailsStage.setTitle("Disaster Details");
                detailsStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void logOutHandler(ActionEvent event) throws IOException {

        SessionManager.signOut();
        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("LogInPage.fxml");
        misc.setSceneName("Log In");
        misc.openCloseScene(event);
    }

    @FXML
    private void findHandler(ActionEvent event) {

        String email = findUser.getText().trim();

        if (!email.isEmpty()) {
            User user = this.userService.findUserByEmail(email);
            if (user != null) {
                // Populate the fields with the user's details
                FindId.setText(String.valueOf(user.getId()));
                FindFirstName.setText(user.getFirstName());
                FindLastName.setText(user.getLastName());
                FindPhone.setText(user.getPhoneNumber());
                FindUserType.getItems().clear();  // Clear previous items
                FindUserType.getItems().addAll(UsersConstant.userRole);  // Assuming getUserType returns a String

                FindUserType.setValue(user.getRole().name());
            } else {
                // Show an alert if the user is not found
                AlertUtil.showAlert(AlertType.WARNING, "User Not Found", null, "No user found with the provided email.");
            }
        } else {
            // Show an alert if the email field is empty
            AlertUtil.showAlert(AlertType.WARNING, "Input Error", null, "Please enter a valid email address.");

        }

    }

    @FXML
    private void UpdateHandler(ActionEvent event) {
        try {
            // Gather updated information from the UI fields
            String userId = FindId.getText();
            String firstName = FindFirstName.getText();
            String lastName = FindLastName.getText();
            String phone = FindPhone.getText();
            String email = findUser.getText();
            String userType = FindUserType.getValue();
            UserRole userTypeEnam = UserRole.valueOf(userType.toUpperCase()); // Convert string to enum

            // Create a new User object with the updated information
            User updatedUser = new User(userId, firstName, lastName, phone, email, userTypeEnam);

            // Update the user in the CSV file
            boolean isUpdated = userService.updateUser(updatedUser);

            // Show an alert depending on the update result
            if (isUpdated) {

                AlertUtil.showAlert(AlertType.INFORMATION, "Success", null, "User information updated successfully.");
            } else {
                AlertUtil.showAlert(AlertType.ERROR, "Error", null, "Failed to update user information. User not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
