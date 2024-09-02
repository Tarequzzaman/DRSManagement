/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 *
 * @author tarequzzamankhan
 */
public class EmergencyTableView {
    
    private SimpleIntegerProperty disasterId;
    private SimpleStringProperty disasterTitle;
    private Button details;
    private SimpleStringProperty priority;
    private ComboBox<String> status;
    private Button action;


    public EmergencyTableView(int disasterId, String disasterTitle, String priority,  String[] groups, String selectedGroup) {
        this.disasterId = new SimpleIntegerProperty(disasterId);
        this.disasterTitle = new SimpleStringProperty(disasterTitle);
        this.details = new Button("View Details");
        this.priority = new SimpleStringProperty(priority);
        this.status = new ComboBox<>();
        this.status.getItems().addAll(groups);
        this.status.setValue(selectedGroup);
        this.action = new Button("Send");
    }
    

    public int getDisasterId() {
        return disasterId.get();
    }

    public SimpleIntegerProperty disasterIdProperty() {
        return disasterId;
    }

    public String getDisasterTitle() {
        return disasterTitle.get();
    }

    public SimpleStringProperty disasterTitleProperty() {
        return disasterTitle;
    }

    public Button getDetails() {
        return details;
    }

    public ComboBox<String> getStatus() {
        return status;
    }
    
     public String getPriority() {
        return disasterTitle.get();
    }
    

    public Button getAction() {
        return action;
    }
    
    
}
