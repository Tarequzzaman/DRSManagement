/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.drsmanagement;

import Model.DisasterDetails;
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
    private TableView<EmergencyResponderHalperController> tableView;

    @FXML
    private TableColumn<EmergencyResponderHalperController, Integer> disesterId;

    @FXML
    private TableColumn<EmergencyResponderHalperController, String> disasterTitle;

    @FXML
    private TableColumn<EmergencyResponderHalperController, Button> details;

    @FXML
    private TableColumn<EmergencyResponderHalperController, String> priority;

    @FXML
    private TableColumn<EmergencyResponderHalperController, ComboBox<String>> status;

    @FXML
    private TableColumn<EmergencyResponderHalperController, Button> action;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        disesterId.setCellValueFactory(new PropertyValueFactory<>("disasterId"));
        disasterTitle.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));

        details.setCellFactory(new Callback<>() {
            @Override
            public TableCell<EmergencyResponderHalperController, Button> call(TableColumn<EmergencyResponderHalperController, Button> param) {
                return new TableCell<>() {
                    private final Button viewButton = new Button("View Details");

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            EmergencyResponderHalperController EmergencyResponderHalperController = getTableView().getItems().get(getIndex());
                            viewButton.setOnAction(event -> {
                                // Handle button click here
                                System.out.println("View Details button clicked for ID: " + EmergencyResponderHalperController.getDisasterId());

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

        priority.setCellValueFactory(new PropertyValueFactory<>("disasterTitle"));

        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        status.setCellFactory(new Callback<TableColumn<EmergencyResponderHalperController, ComboBox<String>>, TableCell<EmergencyResponderHalperController, ComboBox<String>>>() {
            @Override
            public TableCell<EmergencyResponderHalperController, ComboBox<String>> call(TableColumn<EmergencyResponderHalperController, ComboBox<String>> param) {
                return new TableCell<EmergencyResponderHalperController, ComboBox<String>>() {
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
            public TableCell<EmergencyResponderHalperController, Button> call(TableColumn<EmergencyResponderHalperController, Button> param) {
                return new TableCell<>() {
                    private final Button ActionButton = new Button("Send");

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            EmergencyResponderHalperController EmergencyResponderHalperController = getTableView().getItems().get(getIndex());
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

        ObservableList<EmergencyResponderHalperController> data = FXCollections.observableArrayList(
                new EmergencyResponderHalperController(1, "Flood in Area A", "High", disesterStatus, "High"),
                new EmergencyResponderHalperController(2, "Fire in Area B", "Low", disesterStatus, "High")
        );

        tableView.setItems(data);

    }

}
