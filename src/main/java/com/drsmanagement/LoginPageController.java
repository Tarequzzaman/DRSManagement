/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.drsmanagement;
import utils.AlertUtil;
import Model.User;
import database.UserService;
import enam.UserRole;
import Model.SessionManager;


import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author tarequzzamankhan
 */
public class LoginPageController implements Initializable {

    @FXML
    private Button signUp;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logIn;
    
    private UserService userService;
    MiscSceneCloseAndOpen misc;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        userService = new UserService();
        misc = new MiscSceneCloseAndOpen();

    }    

    @FXML
    private void SignUpButtonHandlller(ActionEvent event) throws IOException {
        misc.setSceneFileName("SignUpPage.fxml"); // Redirect to Admin Dashboard
        misc.setSceneName("Sign Up");
        misc.openCloseScene(event);

    }

    @FXML
    private void LogInHandler(ActionEvent event) {
        
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validate input fields
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid Input", "Please fill in all the required fields.");
            return;
        }

        // Find the user by email
        User user = userService.findUserByEmail(email);

        // Check if user exists and if the hashed password matches
        if (user == null || !user.getPassword().equals(userService.hashPassword(password))) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Credentials", "The email or password is incorrect.");
            return;
        }

        // Sign in the user by setting the session
        SessionManager.signIn(user);

        // Redirect the user based on their role
        try {
            if (user.getRole() == UserRole.ADMIN) {
                misc.setSceneFileName("AdminDashboard.fxml"); // Redirect to Admin Dashboard
                misc.setSceneName("Admin Dashboard");
            } else if (user.getRole() == UserRole.USER)
           {
                misc.setSceneFileName("UserDashboard.fxml"); // Redirect to User Dashboard
                misc.setSceneName("User Dashboard");
            }
            else if(user.getRole() == UserRole.RESPONDER_FLOOD_DEPARTMENT){
                misc.setSceneFileName("ResponderDashboard.fxml"); // Redirect to User Dashboard
                misc.setSceneName("Emergency Responder");
            }
            
            else if(user.getRole() == UserRole.RESPONDER_FIRE_DEPARTMENT){
                misc.setSceneFileName("ResponderDashboard.fxml"); // Redirect to User Dashboard
                misc.setSceneName("Emergency Responder");
            }
            
            else if(user.getRole() == UserRole.RESPONDER_HEALTH_DEPARTMENT){
                misc.setSceneFileName("ResponderDashboard.fxml"); // Redirect to User Dashboard
                misc.setSceneName("Emergency Responder");
            }
            
            
            misc.openCloseScene(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
