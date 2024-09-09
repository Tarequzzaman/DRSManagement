package com.drsmanagement;

import Model.ResponderDisesterTableView;
import Model.DisasterDetails;
import Model.DisesterTableView;
import Model.SessionManager;
import Model.User;
import database.DisasterService;
import database.UserService;
import constant.Responder;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class ResponderDashboardController implements Initializable {

    @FXML
    private TableView<ResponderDisesterTableView> tableView; // For "Pending" tab

    @FXML
    private TableColumn<ResponderDisesterTableView, Integer> disesterId;
    @FXML
    private TableColumn<ResponderDisesterTableView, String> disasterTitle;
    @FXML
    private TableColumn<ResponderDisesterTableView, String> status;
    @FXML
    private TableColumn<ResponderDisesterTableView, String> priority;
    @FXML
    private TableColumn<ResponderDisesterTableView, Button> action;
    @FXML
    private TableColumn<ResponderDisesterTableView, Button> details;

    @FXML
    private TableView<ResponderDisesterTableView> tableView1; // For "Ongoing" tab

    @FXML
    private TableColumn<ResponderDisesterTableView, Integer> disesterId1;
    @FXML
    private TableColumn<ResponderDisesterTableView, String> disasterTitle1;
    @FXML
    private TableColumn<ResponderDisesterTableView, String> status1;
    @FXML
    private TableColumn<ResponderDisesterTableView, String> priority1;
    @FXML
    private TableColumn<ResponderDisesterTableView, Button> action1;
    @FXML
    private TableColumn<ResponderDisesterTableView, Button> details1;
    

    @FXML
    private Label userName;

    private DisasterService disasterService;
    @FXML
    private Tab pending;
    @FXML
    private Tab ongoing;

    @FXML
    private TableColumn<ResponderDisesterTableView, Integer> disesterId11;
    @FXML
    private TableColumn<ResponderDisesterTableView, String> disasterTitle11;
    @FXML
    private TableColumn<ResponderDisesterTableView, String> status11;
    @FXML
    private TableColumn<ResponderDisesterTableView, String> priority11;
    @FXML
    private TableColumn<ResponderDisesterTableView, Button> details11;
    @FXML
    private TableView<ResponderDisesterTableView> tableView11;
    @FXML
    private Tab completed;
    
    @FXML
    private Button logout;
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (SessionManager.isLoggedIn()) {
            User currentUser = SessionManager.getCurrentUser();
            userName.setText("Welcome, " + currentUser.getFirstName());
        } else {
            // Handle case where no user is signed in (optional)
            userName.setText("Please sign in.");
        }
        disasterService = new DisasterService();
        setupPendingTab();
        setupOngoingTab();
        setupCompletedTab();

    }

    private void setupPendingTab() {
        disesterId.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        disasterTitle.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        action.setCellValueFactory(new PropertyValueFactory<>("startButton"));
        details.setCellValueFactory(new PropertyValueFactory<>("detailsButton"));

        // Load initial data into the "Pending" tab
        loadPendingTableData();
    }

    private void loadPendingTableData() {
        // Assuming you have a way to get the current user's role

        User currentUser = SessionManager.getCurrentUser();

        System.out.println(currentUser.getRole().name());

        List<DisasterDetails> pendingDisasters = disasterService.getDisastersByStatusAndRole("dispatched", currentUser.getRole().name());

        ObservableList<ResponderDisesterTableView> data = FXCollections.observableArrayList();

        for (DisasterDetails disaster : pendingDisasters) {
            ResponderDisesterTableView responderItem = new ResponderDisesterTableView(
                    disaster.getDisasterId(),
                    disaster.getDisasterTitle(),
                    disaster.getStatus(),
                    disaster.getPriority()
            );

            responderItem.getStartButton().setOnAction(event -> {
                // Handle the start action, e.g., update the status to "ongoing"
                disasterService.updateDisasterStatus(responderItem.getDisasterId(), "ongoing");

                reloadTableData(); // Refresh the table
            });

            responderItem.getDetailsButton().setOnAction(event -> openDetailsWindow(responderItem));

            data.add(responderItem);
        }

        tableView.setItems(data);
    }

    private void setupOngoingTab() {
        disesterId1.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        disasterTitle1.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));
        status1.setCellValueFactory(new PropertyValueFactory<>("status"));
        priority1.setCellValueFactory(new PropertyValueFactory<>("priority"));
        action1.setCellValueFactory(new PropertyValueFactory<>("startButton"));
        details1.setCellValueFactory(new PropertyValueFactory<>("detailsButton"));


        loadOngoingTableData();
    }

    private void loadOngoingTableData() {
        User currentUser = SessionManager.getCurrentUser();

        String currentUserRole = currentUser.getRole().name(); // Example role

        List<DisasterDetails> ongoingDisasters = disasterService.getDisastersByStatusAndRole("ongoing", currentUserRole);

        ObservableList<ResponderDisesterTableView> data = FXCollections.observableArrayList();

        for (DisasterDetails disaster : ongoingDisasters) {
            ResponderDisesterTableView responderItem = new ResponderDisesterTableView(
                    disaster.getDisasterId(),
                    disaster.getDisasterTitle(),
                    disaster.getStatus(),
                    disaster.getPriority()
            );
            
            responderItem.getStartButton().setText("Complete");
            responderItem.getStartButton().setOnAction(event -> {
                disasterService.updateDisasterStatus(responderItem.getDisasterId(), "complete");
                reloadTableData();
            });
            
            responderItem.getDetailsButton().setOnAction(event -> openDetailsWindow(responderItem));
            data.add(responderItem);
        }

        tableView1.setItems(data);
    }

    private void reloadTableData() {
        tableView.getItems().clear();
        tableView1.getItems().clear();
        tableView11.getItems().clear();
        loadPendingTableData();
        loadCompletedTableData();
        loadOngoingTableData();
    }


    private void setupCompletedTab() {
        disesterId11.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        disasterTitle11.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));
        status11.setCellValueFactory(new PropertyValueFactory<>("status"));
        priority11.setCellValueFactory(new PropertyValueFactory<>("priority"));
        details11.setCellValueFactory(new PropertyValueFactory<>("detailsButton"));

        loadCompletedTableData();
    }

    private void loadCompletedTableData() {
        User currentUser = SessionManager.getCurrentUser();
        
        System.out.print(currentUser.getRole().name());

        // Fetch all completed disasters for the current user
        List<DisasterDetails> completedDisasters = disasterService.getDisastersByStatusAndRole("complete", currentUser.getRole().name());
        
        System.out.println(completedDisasters);

        ObservableList<ResponderDisesterTableView> data = FXCollections.observableArrayList();

        for (DisasterDetails disaster : completedDisasters) {
            ResponderDisesterTableView responderItem = new ResponderDisesterTableView(
                    disaster.getDisasterId(),
                    disaster.getDisasterTitle(),
                    disaster.getStatus(),
                    disaster.getPriority()
            );

            responderItem.getDetailsButton().setOnAction(event -> openDetailsWindow(responderItem));

            data.add(responderItem);
        }

        tableView11.setItems(data);
    }
    
    @FXML
    private void LogoutHandler(ActionEvent event) throws IOException {

        SessionManager.signOut();
        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("LogInPage.fxml");
        misc.setSceneName("Log In");
        misc.openCloseScene(event);
    }
    
     private void openDetailsWindow(ResponderDisesterTableView item) {
        DisasterService disasterService = new DisasterService();
        UserService userService = new UserService();

        // Fetch the disaster details using the disaster ID
        DisasterDetails disasterDetails = disasterService.getDisasterById(item.getDisasterId());

        if (disasterDetails != null) {
            // Fetch the user details using the submittedBy field
            User reporter = userService.findUserById(disasterDetails.getSubmittedBy());

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DisasterDetails.fxml"));
                Parent root = loader.load();

                DisasterDetailsController controller = loader.getController();
                controller.setDetails(
                        disasterDetails.getDisasterTitle(),
                        disasterDetails.getDetail(),
                        disasterDetails.getPhone(),
                        disasterDetails.getLocation(),
                        disasterDetails.getState(),
                        disasterDetails.getStatus(),
                        reporter != null ? reporter.getFirstName() + " " + reporter.getLastName() : "Unknown");

                Scene detailsScene = new Scene(root);
                Stage detailsStage = new Stage();
                detailsStage.setScene(detailsScene);
                detailsStage.setTitle("Disaster Details");
                detailsStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
