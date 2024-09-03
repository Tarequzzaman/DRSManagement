/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.drsmanagement;

import Model.SessionManager;
import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tarequzzamankhan
 */
public class DisasterDetailsController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private TextArea detail;
    @FXML
    private Label phone;
    @FXML
    private Text location;
    @FXML
    private Text state;
    @FXML
    private Button okayButton;
    @FXML
    private Label status;
    @FXML
    private Label reporterName;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    // Method to set the details in the UI
    public void setDetails(String titleText, String detailText, String phoneText, String locationText, String stateText, String statusText, String reporterNameText) {
        title.setText(titleText);
        detail.setText(detailText);
        phone.setText(phoneText);
        location.setText(locationText);
        state.setText(stateText);
        status.setText(statusText);
        reporterName.setText(reporterNameText);
    }

    @FXML
    private void okayButtonHandler(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
