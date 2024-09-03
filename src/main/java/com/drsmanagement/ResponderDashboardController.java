/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.drsmanagement;

import Model.EmergencyTableView;
import Model.DisasterDetails;
import Model.SessionManager;
import Model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author tarequzzamankhan
 */
public class ResponderDashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<EmergencyTableView> tableView;

    @FXML
    private TableColumn<EmergencyTableView, Integer> disesterId;

    @FXML
    private TableColumn<EmergencyTableView, String> disasterTitle;

    @FXML
    private TableColumn<EmergencyTableView, Button> details;

    @FXML
    private TableColumn<EmergencyTableView, String> priority;

    @FXML
    private TableColumn<EmergencyTableView, ComboBox<String>> status;

    @FXML
    private TableColumn<EmergencyTableView, Button> action;
    
     @FXML
    private Label userName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (SessionManager.isLoggedIn()) {
            User currentUser = SessionManager.getCurrentUser();
            userName.setText("Welcome, " + currentUser.getFirstName());
        } else {
            // Handle case where no user is signed in (optional)
            userName.setText("Please sign in.");
        }

        disesterId.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        disasterTitle.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));

        details.setCellFactory(new Callback<>() {
            @Override
            public TableCell<EmergencyTableView, Button> call(TableColumn<EmergencyTableView, Button> param) {
                return new TableCell<>() {
                    private final Button viewButton = new Button("View Details");

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            EmergencyTableView EmergencyResponderHalperController = getTableView().getItems().get(getIndex());
                            viewButton.setOnAction(event -> {
                                // Handle button click here
                                System.out.println("View Details button clicked for ID: " + EmergencyResponderHalperController.getDisasterId());

                                /// dummy data this data need to comes from database via model

                                DisasterDetails disester = new DisasterDetails(1, "Flood in Area A", "Heavy rain caused flooding", "04723892382", "Unit 1", "123", "Area A", "NSW", "0", "pending", "N/A", "Flood");

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
//                            controller.setDetails(disasterDetails.getDisasterTitle(),
//                                    disasterDetails.getDetail(),
//                                    disasterDetails.getPhone(),
//                                    disasterDetails.getLocation(),
//                                    disasterDetails.getState());

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

        priority.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));

        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        status.setCellFactory(new Callback<TableColumn<EmergencyTableView, ComboBox<String>>, TableCell<EmergencyTableView, ComboBox<String>>>() {
            @Override
            public TableCell<EmergencyTableView, ComboBox<String>> call(TableColumn<EmergencyTableView, ComboBox<String>> param) {
                return new TableCell<EmergencyTableView, ComboBox<String>>() {
                    @Override
                    protected void updateItem(ComboBox<String> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(getTableView().getItems().get(getIndex()).getStatus());
                        }
                    }
                };
            }
        });

        action.setCellFactory(new Callback<>() {
            @Override
            public TableCell<EmergencyTableView, Button> call(TableColumn<EmergencyTableView, Button> param) {
                return new TableCell<>() {
                    private final Button ActionButton = new Button("Send");

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            EmergencyTableView EmergencyResponderHalperController = getTableView().getItems().get(getIndex());
                            ActionButton.setOnAction(event -> {
                                // Handle button click here
                                System.out.println("View Details button clicked for ID: " + EmergencyResponderHalperController.getDisasterId());

                            });
                            setGraphic(ActionButton);
                        }
                    }
                };
            }
        });

        // (int disasterId, String disasterTitle, String priority,  String[] groups, String selectedGroup)
        String[] disesterStatus = {"pending", "Ongoing", "Complete"};

        ObservableList<EmergencyTableView> data = FXCollections.observableArrayList(new EmergencyTableView(1, "Flood in Area A", "High", disesterStatus, "High"),
                new EmergencyTableView(2, "Fire in Area B", "Low", disesterStatus, "High")
        );

        tableView.setItems(data);

    }

}
