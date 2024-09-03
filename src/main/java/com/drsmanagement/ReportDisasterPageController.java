/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.drsmanagement;

import Model.SessionManager;
import Model.DisasterDetails;
import Model.User;
import constant.ReportDisaster;
import database.DisasterService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utils.AlertUtil;

/**
 * FXML Controller class
 *
 * @author tarequzzamankhan
 */
public class ReportDisasterPageController implements Initializable {

    @FXML
    private Button backbutton;
    @FXML
    private TextField titleDisaster;
    @FXML
    private TextField contractNumber;
    @FXML
    private TextArea disasterDescription;
    @FXML
    private ComboBox<String> disasterType;
    @FXML
    private TextField street;
    @FXML
    private TextField houseNo;
    @FXML
    private TextField suburb;
    @FXML
    private TextField unit;
    @FXML
    private ComboBox<String> state;
    @FXML
    private Button submit;
    @FXML
    private Label userName;
    @FXML
    private Button logout;
    private DisasterService disasterService = new DisasterService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (SessionManager.isLoggedIn()) {
            User currentUser = SessionManager.getCurrentUser();
            userName.setText("Welcome, " + currentUser.getFirstName() + " " + currentUser.getLastName());
        } else {
            // Handle case where no user is signed in (optional)
            userName.setText("Please sign in.");
        }
        // TODO

        this.disasterType.getItems().addAll(ReportDisaster.disasterTypes);

        this.state.getItems().addAll(ReportDisaster.stateInitials);

    }

    @FXML
    private void BackButtonHandler(ActionEvent event) throws IOException {

        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("UserDashboard.fxml");
        misc.setSceneName("Dashboard");
        misc.openCloseScene(event);
    }

    @FXML
    private void submitButtonHandler(ActionEvent event) throws IOException {

        // Get the current user's ID
        String submittedBy = SessionManager.getCurrentUser().getId();

        // Generate the next disaster ID
        int disasterId = disasterService.generateNextId();
        System.out.print("The id is : " +disasterId);

        DisasterDetails disasterDetails = new DisasterDetails(
                disasterId,
                titleDisaster.getText(),
                disasterDescription.getText(),
                contractNumber.getText(),
                unit.getText(),
                houseNo.getText(),
                suburb.getText(),
                state.getValue(),
                submittedBy,
                "Pending", // Default status
                "High", // Default priority
                disasterType.getValue() // Designated department/type
        );

        // Save the disaster details
        if (disasterService.saveDisaster(disasterDetails)) {
            MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
            misc.setSceneFileName("UserDashboard.fxml");
            misc.setSceneName("Dashboard");
            misc.openCloseScene(event);
        } else {

            // Show an error alert if saving fails
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Save Failed", "", "Could not save disaster report. Please try again.");

        }

        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("UserDashboard.fxml");
        misc.setSceneName("Dashboard");
        misc.openCloseScene(event);
    }

    @FXML
    private void LogoutHandler(ActionEvent event) throws IOException {
        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("LogInPage.fxml");
        misc.setSceneName("Log In");
        misc.openCloseScene(event);
    }

}
