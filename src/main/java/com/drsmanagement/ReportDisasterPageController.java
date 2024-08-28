/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.drsmanagement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] disasterTypes = {"Bushfire", "Flood", "Cyclone", "Drought", "Heatwave", "Earthquake", "Tsunami", "Storm", "Landslide"};

        this.disasterType.getItems().addAll(disasterTypes);
        String[] stateInitials = {"NSW", "VIC", "QLD", "WA", "SA", "TAS", "NT", "ACT"};

        this.state.getItems().addAll(stateInitials);

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
