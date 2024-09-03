/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.drsmanagement;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tarequzzamankhan
 */
public class MiscSceneCloseAndOpen {

    private String sceneName;
    private String sceneFileName;

    public MiscSceneCloseAndOpen() {

    }
    

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneFileName() {
        return sceneFileName;
    }

    public void setSceneFileName(String sceneFileName) {
        this.sceneFileName = sceneFileName;
    }

    public void openCloseScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getSceneFileName()));
        Parent signUpRoot = fxmlLoader.load();

        // Create a new scene with the SignUpPage
        Scene signUpScene = new Scene(signUpRoot, 1060, 700);

        // Create a new stage
        Stage signUpStage = new Stage();
        signUpStage.setScene(signUpScene);
        signUpStage.setTitle(this.getSceneName());

        // Show the new stage
        signUpStage.show();

        // Close the current stage (Login stage)
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

    }
    
    
    
    
    public void openScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.getSceneFileName()));
        Parent root = fxmlLoader.load();

        // Create a new scene
        Scene scene = new Scene(root, 1060, 700);

        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the existing stage
        stage.setScene(scene);
        stage.setTitle(this.getSceneName());
        stage.show();
    }

    public void closeCurrentStage(ActionEvent event) {
        // Get the current stage from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
