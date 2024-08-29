/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.drsmanagement;

import Model.DisasterDetails;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author tarequzzamankhan
 */
public class AdminDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<DisesterTableView> tableView;

    @FXML
    private TableColumn<DisesterTableView, Integer> disesterId;

    @FXML
    private TableColumn<DisesterTableView, String> disasterTitle;

    @FXML
    private TableColumn<DisesterTableView, Button> details;

    @FXML
    private TableColumn<DisesterTableView, ComboBox<String>> assignedGroup;
    
    @FXML
    private TableColumn<DisesterTableView, ComboBox<String>> priority;

    @FXML
    private TableColumn<DisesterTableView, Button> action;
    @FXML
    private Button logOut;
    @FXML
    private Button userManagement;
    @FXML
    private Button accessedReort;
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the columns to use the DisesterTableView properties
        disesterId.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        disasterTitle.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));

        details.setCellFactory(new Callback<>() {
            @Override
            public TableCell<DisesterTableView, Button> call(TableColumn<DisesterTableView, Button> param) {
                return new TableCell<>() {
                    private final Button viewButton = new Button("View Details");

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            DisesterTableView disesterTableView = getTableView().getItems().get(getIndex());
                            viewButton.setOnAction(event -> {
                                // Handle button click here
                                System.out.println("View Details button clicked for ID: " + disesterTableView.getDisasterId());

                                /// dummy data this data need to comes from database via model
                                DisasterDetails disester = new DisasterDetails(1, "Flood in Area A", "Heavy rain caused flooding", "04723892382", "Unit 1", "123", "Area A", "NSW");

                                openDetailsWindow(disester);

                            });
                            setGraphic(viewButton);
                        }
                    }

                    private void openDetailsWindow(DisasterDetails disasterDetails) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("DisasterDetails.fxml"));
                            Parent root = loader.load();

                            DisasterDetailsController controller = loader.getController();
                            controller.setDetails(disasterDetails.getDisasterTitle(),
                                    disasterDetails.getDetail(),
                                    disasterDetails.getPhone(),
                                    disasterDetails.getLocation(),
                                    disasterDetails.getState());

                            // Create a new scene with the SignUpPage
                            Scene signUpScene = new Scene(root);

                            // Create a new stage
                            Stage signUpStage = new Stage();
                            signUpStage.setScene(signUpScene);
                            signUpStage.setTitle("Disaster Details");

                            // Show the new stage
                            signUpStage.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                };
            }
        });

        assignedGroup.setCellValueFactory(new PropertyValueFactory<>("assignedGroup"));
        assignedGroup.setCellFactory(new Callback<TableColumn<DisesterTableView, ComboBox<String>>, TableCell<DisesterTableView, ComboBox<String>>>() {
            @Override
            public TableCell<DisesterTableView, ComboBox<String>> call(TableColumn<DisesterTableView, ComboBox<String>> param) {
                return new TableCell<DisesterTableView, ComboBox<String>>() {
                    @Override
                    protected void updateItem(ComboBox<String> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(getTableView().getItems().get(getIndex()).getAssignedGroup());
                        }
                    }
                };
            }
        });

        
        
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        priority.setCellFactory(new Callback<TableColumn<DisesterTableView, ComboBox<String>>, TableCell<DisesterTableView, ComboBox<String>>>() {
            @Override
            public TableCell<DisesterTableView, ComboBox<String>> call(TableColumn<DisesterTableView, ComboBox<String>> param) {
                return new TableCell<DisesterTableView, ComboBox<String>>() {
                    @Override
                    protected void updateItem(ComboBox<String> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(getTableView().getItems().get(getIndex()).getPriority());
                        }
                    }
                };
            }
        });
       
        
        
        
        
         action.setCellFactory(new Callback<>() {
            @Override
            public TableCell<DisesterTableView, Button> call(TableColumn<DisesterTableView, Button> param) {
                return new TableCell<>() {
                    private final Button ActionButton = new Button("Send");

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            DisesterTableView disesterTableView = getTableView().getItems().get(getIndex());
                            ActionButton.setOnAction(event -> {
                                // Handle button click here
                                System.out.println("View Details button clicked for ID: " + disesterTableView.getDisasterId());

                               

                            });
                            setGraphic(ActionButton);
                        }
                    }
                };
            }
        });
        
        
        String[] assignedDepartment = {"Wildfire", "Severe Thunderstorm", "Flash Flood", "Extreme Heat", "Coastal Erosion", "Severe Wind", "Volcanic Activity", "Hailstorm", "Severe Weather"};
        String[] priorities = {"High", "Medium", "low"};
        
        
        // Add some sample data to the table
        ObservableList<DisesterTableView> data = FXCollections.observableArrayList(
                new DisesterTableView(1, "Flood in Area A", assignedDepartment, "Flash Flood", priorities, "High"),
                new DisesterTableView(2, "Fire in Area B", assignedDepartment, "Fire in Area B", priorities, "High")

       
        );

        tableView.setItems(data);
    }

    @FXML
    private void logOutHandler(ActionEvent event) throws IOException {
        MiscSceneCloseAndOpen misc = new MiscSceneCloseAndOpen();
        misc.setSceneFileName("LogInPage.fxml");
        misc.setSceneName("Log In");
        misc.openCloseScene(event);  
    }

    @FXML
    private void UserManagementHandler(ActionEvent event) {
    }

    @FXML
    private void AccessedReortHandler(ActionEvent event) {
    }
    
    
    

}
