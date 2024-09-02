package Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 *
 * @author tarequzzamankhan
 */



public class DisesterTableView {
    private SimpleIntegerProperty disasterId;
    private SimpleStringProperty disasterTitle;
    private Button details;
    private ComboBox<String> assignedGroup;
    private ComboBox<String> priority;

    private Button action;


    public DisesterTableView(int disasterId, String disasterTitle, String[] groups, String selectedGroup, String[] priority, String selectedPriorities) {
        this.disasterId = new SimpleIntegerProperty(disasterId);
        this.disasterTitle = new SimpleStringProperty(disasterTitle);
        this.details = new Button("View Details");
        this.assignedGroup = new ComboBox<>();
        this.assignedGroup.getItems().addAll(groups);
        this.assignedGroup.setValue(selectedGroup);
        
        
        this.priority = new ComboBox<>();
        this.priority.getItems().addAll(priority);
        this.priority.setValue(selectedPriorities);
      
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

    public ComboBox<String> getAssignedGroup() {
        return assignedGroup;
    }
    
    public ComboBox<String> getPriority() {
        return priority;
    }
    
    
    public Button getAction() {
        return action;
    }
}
