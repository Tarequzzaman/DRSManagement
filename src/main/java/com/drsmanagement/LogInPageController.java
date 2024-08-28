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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tarequzzamankhan
 */
public class LogInPageController implements Initializable {

    @FXML
    private Button signUp;
    @FXML
    private Button logIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SignUpButtonHandlller(ActionEvent event) throws IOException {
        
        // Load the SignUpPage FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpPage.fxml"));
        Parent signUpRoot = fxmlLoader.load();

        // Create a new scene with the SignUpPage
        Scene signUpScene = new Scene(signUpRoot,1060, 700);

        // Create a new stage
        Stage signUpStage = new Stage();
        signUpStage.setScene(signUpScene);
        signUpStage.setTitle("Sign Up");

        // Show the new stage
        signUpStage.show();

        // Close the current stage (Login stage)
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void LogInHandler(ActionEvent event) throws IOException {
        
                // Load the SignUpPage FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserDashboard.fxml"));
        Parent signUpRoot = fxmlLoader.load();

        // Create a new scene with the SignUpPage
        Scene signUpScene = new Scene(signUpRoot,1060, 700);

        // Create a new stage
        Stage signUpStage = new Stage();
        signUpStage.setScene(signUpScene);
        signUpStage.setTitle("Dashboard");

        // Show the new stage
        signUpStage.show();

        // Close the current stage (Login stage)
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        
        
        
        
        
    }
    
}
