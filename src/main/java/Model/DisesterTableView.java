package Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class DisesterTableView {
    private final SimpleIntegerProperty disasterId;
    private final SimpleStringProperty disasterTitle;
    private final ComboBox<String> assignedGroup;
    private final ComboBox<String> priority;
    private final Button details;
    private final Button action;
    private String selectedGroup;
    private String selectedPriority;

   
    // Constructor for "Pending" tab with ComboBoxes and buttons
    public DisesterTableView(int disasterId, String disasterTitle, String[] groups, String selectedGroup, String[] priority, String selectedPriority) {
        this.disasterId = new SimpleIntegerProperty(disasterId);
        this.disasterTitle = new SimpleStringProperty(disasterTitle);
        this.assignedGroup = new ComboBox<>();
        this.assignedGroup.getItems().addAll(groups);
        this.assignedGroup.setValue(selectedGroup);
        this.priority = new ComboBox<>();
        this.priority.getItems().addAll(priority);
        this.priority.setValue(selectedPriority);
        this.details = new Button("View Details");
        this.action = new Button("Send");
    }

    // Constructor for "Assessed" tab with plain text fields and no action button
    public DisesterTableView(int disasterId, String disasterTitle, String selectedGroup, String priority) {
        this.disasterId = new SimpleIntegerProperty(disasterId);
        this.disasterTitle = new SimpleStringProperty(disasterTitle);
        this.assignedGroup = null;
        this.priority = null;
        
        this.details = new Button("View Details");
        this.action = null;  // No action button for the "Assessed" tab
        this.selectedGroup = selectedGroup;
        this.selectedPriority = priority;
    }

    public int getDisasterId() {
        return disasterId.get();
    }

    public String getDisasterTitle() {
        return disasterTitle.get();
    }

    public ComboBox<String> getAssignedGroup() {
        return assignedGroup;
    }

    public ComboBox<String> getPriority() {
        return priority;
    }

    public Button getDetails() {
        return details;
    }

    public Button getAction() {
        return action;
    }

    public String getAssignedGroupText() {
        return assignedGroup == null ? this.getSelectedGroup() : assignedGroup.getValue();
    }

    public String getPriorityText() {
        return priority == null ? this.getSelectedPriority() : priority.getValue();
    }
    
    public String getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public String getSelectedPriority() {
        return selectedPriority;
    }

    public void setSelectedPriority(String selectedPriority) {
        this.selectedPriority = selectedPriority;
    }

}
