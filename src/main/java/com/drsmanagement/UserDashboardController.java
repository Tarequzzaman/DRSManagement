/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.drsmanagement;

import Model.DisasterDetails;
import Model.SessionManager;
import Model.User;
import database.DisasterService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tarequzzamankhan
 */
public class UserDashboardController implements Initializable {

    @FXML
    private Button disaster;
    @FXML
    private Button logOut;

    @FXML
    private Label userName;

    @FXML
    private TableView<DisasterDetails> tableView; // The table to display disaster reports

    @FXML
    private TableColumn<DisasterDetails, Integer> id; // Column for Disaster ID
    @FXML
    private TableColumn<DisasterDetails, String> title; // Column for Disaster Title
    @FXML
    private TableColumn<DisasterDetails, String> priority; // Column for Priority
    @FXML
    private TableColumn<DisasterDetails, String> status; // Column for Disaster Status

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (SessionManager.isLoggedIn()) {
            User currentUser = SessionManager.getCurrentUser();
            userName.setText("Welcome, " + currentUser.getFirstName() + " " + currentUser.getLastName());
            loadDisasterData();
        } else {
            // Handle case where no user is signed in (optional)
            userName.setText("Please sign in.");
        }
    }

    @FXML
    private void DisasterHandler(ActionEvent event) throws IOException {
     
        
        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("ReportDisasterPage.fxml");
        misc.setSceneName("Report Disaster");
        misc.openCloseScene(event);
    }

    @FXML
    private void LogoutHandaler(ActionEvent event) throws IOException {
        // Sign out the user
        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("LogInPage.fxml");
        misc.setSceneName("Log In");
        misc.openCloseScene(event);
       
    }

    private void loadDisasterData() {
        DisasterService disasterService = new DisasterService();
        User currentUser = SessionManager.getCurrentUser();
        
        System.out.println(disasterService.getDisastersByUser(currentUser.getId()));
        
        System.out.print(currentUser.getId());
        ObservableList<DisasterDetails> userDisasters = FXCollections.observableArrayList(disasterService.getDisastersByUser(currentUser.getId()));

        tableView.setItems(userDisasters);

        id.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        title.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
}
