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
public class SignUpPageController implements Initializable {

    @FXML
    private Button signIn;
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
        // Load the SignUpPage FXML

        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("LogInPage.fxml");
        misc.setSceneName("Log In");
        misc.openCloseScene(event);

    }

    @FXML
    private void SubmitHandler(ActionEvent event) throws IOException {

        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("UserDashboard.fxml");
        misc.setSceneName("Dashboard");
        misc.openCloseScene(event);

    }

}
