package com.drsmanagement;

import Model.DisesterTableView;
import Model.DisasterDetails;
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
import java.util.ResourceBundle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AdminDashboardController implements Initializable {

    @FXML
    private TableView<DisesterTableView> tableView;

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
    private Button logOut;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disesterId.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        disasterTitle.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));

        assignedGroup.setCellValueFactory(new PropertyValueFactory<>("assignedGroup"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        details.setCellValueFactory(new PropertyValueFactory<>("details"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));

        // Load initial data into the table
        loadTableData();
    }

    private void loadTableData() {
        String[] assignedDepartment = {"Wildfire", "Severe Thunderstorm", "Flash Flood", "Extreme Heat", "Coastal Erosion", "Severe Wind", "Volcanic Activity", "Hailstorm", "Severe Weather"};
        String[] priorities = {"High", "Medium", "Low"};

        // Randomly shuffle the array and pick the first three elements
        List<String> departmentList = Arrays.asList(assignedDepartment);
        Collections.shuffle(departmentList, new Random());
        String[] selectedDepartments = departmentList.subList(0, 3).toArray(new String[0]);

        ObservableList<DisesterTableView> data = FXCollections.observableArrayList(
                new DisesterTableView(1, "Flood in Area A", selectedDepartments, selectedDepartments[0], priorities, "High"),
                new DisesterTableView(2, "Fire in Area B", selectedDepartments, selectedDepartments[1], priorities, "High")
        );

        // Add action handling for buttons in each row
        for (DisesterTableView item : data) {
            // Handle View Details button
            item.getDetails().setOnAction(event -> {
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
                openDetailsWindow(disasterDetails);
            });

            // Handle Send button
            item.getAction().setOnAction(event -> {
                System.out.println("Send button clicked for disaster ID: " + item.getDisasterId());
                // Implement the action you want to perform when the Send button is clicked.
                reloadTableData();
            });
        }

        tableView.setItems(data);
    }

    private void reloadTableData() {
        // Clear existing data
        tableView.getItems().clear();

        // Reload the table data
        loadTableData();
    }

    private void openDetailsWindow(DisasterDetails disasterDetails) {
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
