package com.drsmanagement;

import Model.DisesterTableView;
import Model.DisasterDetails;
import constant.AdminViewConstants;


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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        
        // Randomly shuffle and pick three departments
        List<String> departmentList = Arrays.asList(AdminViewConstants.ASSIGNED_DEPARTMENTS);
        Collections.shuffle(departmentList, new Random());
        String[] selectedDepartments = departmentList.subList(0, 3).toArray(new String[0]);

        ObservableList<DisesterTableView> data = FXCollections.observableArrayList(
                new DisesterTableView(1, "Flood in Area A", selectedDepartments, selectedDepartments[0], AdminViewConstants.PRIORITY_LEVELS, "High"),
                new DisesterTableView(2, "Fire in Area B", selectedDepartments, selectedDepartments[1], AdminViewConstants.PRIORITY_LEVELS, "High")
        );

        // Add action handling for buttons in each row
        for (DisesterTableView item : data) {
            item.getDetails().setOnAction(event -> openDetailsWindow(item));
            item.getAction().setOnAction(event -> {
                System.out.println("Send button clicked for disaster ID: " + item.getDisasterId());
                reloadTableData();
            });
        }

        tableView.setItems(data);
    }
    

    private void setupAssessedTab() {
        disesterId1.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        disasterTitle1.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));
        assignedGroup1.setCellValueFactory(new PropertyValueFactory<>("assignedGroupText"));
        priority1.setCellValueFactory(new PropertyValueFactory<>("priorityText"));
        details1.setCellValueFactory(new PropertyValueFactory<>("details"));

        // Load initial data into the "Assessed" tab
        loadAssessedTableData();
    }

    
    private void loadAssessedTableData() {
        ObservableList<DisesterTableView> data = FXCollections.observableArrayList(
                new DisesterTableView(3, "Flood in Area C", "Flash Flood", "Medium"),
                new DisesterTableView(4, "Earthquake in Area D", "Seismic Activity", "High")
        );

        // Add action handling for the "View Details" button in each row
        for (DisesterTableView item : data) {
            item.getDetails().setOnAction(event -> openDetailsWindow(item));
        }

        tableView1.setItems(data);
    }

    
    private void reloadTableData() {
        tableView.getItems().clear();
        loadPendingTableData();
    }
    

    private void openDetailsWindow(DisesterTableView item) {
        DisasterDetails disasterDetails = new DisasterDetails(
                item.getDisasterId(),
                item.getDisasterTitle(),
                "Description of the disaster",
                "04723892382",
                "Unit 1",
                "123",
                "Area A",
                "NSW"
        );
        

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisasterDetails.fxml"));
            Parent root = loader.load();

            DisasterDetailsController controller = loader.getController();
            controller.setDetails(disasterDetails.getDisasterTitle(),
                    disasterDetails.getDetail(),
                    disasterDetails.getPhone(),
                    disasterDetails.getLocation(),
                    disasterDetails.getState());

            Scene detailsScene = new Scene(root);
            Stage detailsStage = new Stage();
            detailsStage.setScene(detailsScene);
            detailsStage.setTitle("Disaster Details");
            detailsStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logOutHandler(ActionEvent event) throws IOException {
        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("LogInPage.fxml");
        misc.setSceneName("Log In");
        misc.openCloseScene(event);
    }
}
