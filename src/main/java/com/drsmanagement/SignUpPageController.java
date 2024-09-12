/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.drsmanagement;

import enam.UserRole;
import utils.ValidationUtil;
import Model.User;
import database.UserService;
import java.io.IOException;
import utils.AlertUtil;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.regex.Pattern;

/**
 * FXML Controller class
 *
 * @author tarequzzamankhan
 */
public class SignUpPageController implements Initializable {

    @FXML
    private Button signIn;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SignInHandler(ActionEvent event) throws IOException {
        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("LogInPage.fxml");
        misc.setSceneName("Log In");
        misc.openCloseScene(event);

    }

    @FXML
    private void SubmitHandler(ActionEvent event) {

        String fName = firstName.getText();
        String lName = lastName.getText();
        String number = phoneNumber.getText();
        String mail = email.getText();
        String pass = password.getText();
        

        // Validate input fields
        if (fName == null || fName.trim().isEmpty()
                || lName == null || lName.trim().isEmpty()
                || number == null || number.trim().isEmpty()
                || mail == null || mail.trim().isEmpty()
                || pass == null || pass.trim().isEmpty()) {

            AlertUtil.showAlert(AlertType.ERROR, "Validation Error", "Invalid Input", "Please fill in all the fields.");
            return; // Exit the method if validation fails
        }


        // Use the new validation functions
        if (!ValidationUtil.isValidPhoneNumber(number)) {

            AlertUtil.showAlert(AlertType.ERROR, "Validation Error", "Invalid Phone Number", "Phone number must be a valid Australian number starting with 04 and have 10 digits.");
            return; // Exit if phone number validation fails
        }


        if (!ValidationUtil.isValidEmail(mail)) {

            AlertUtil.showAlert(AlertType.ERROR, "Validation Error", "Invalid Email", "Please enter a valid email address.");
            return; // Exit if email validation fails
        }

        // Instantiate UserService to handle user operations
        UserService userService = new UserService();

        // Check if user already exists by email
        User existingUser = userService.findUserByEmail(mail);
        if (existingUser != null) {
            AlertUtil.showAlert(AlertType.ERROR, "User Exists", "Duplicate Email", "A user with this email already exists. Please use a different email.");
            return; // Exit the method if the user already exists
        }

        // Generate a unique ID for the new user
        String newUserId = userService.generateNextId();

        // Hash the password
        String hashedPassword = userService.hashPassword(pass);

        // Create a new User object
        User newUser = new User(newUserId, fName, lName, number, mail, hashedPassword, UserRole.USER);

        // Save the new user to the CSV file
        boolean success = userService.saveUser(newUser);

        if (success) {
            AlertUtil.showAlert(AlertType.INFORMATION, "Signup Successful", null, "You have successfully signed up!");

            // Redirect to the login page or another page
            try {
                MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
                misc.setSceneFileName("LogInPage.fxml");
                misc.setSceneName("Log In");
                misc.openCloseScene(event);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            AlertUtil.showAlert(AlertType.ERROR, "Signup Failed", "Failed to Save User", "There was an error saving your details. Please try again.");
        }
    }

}
