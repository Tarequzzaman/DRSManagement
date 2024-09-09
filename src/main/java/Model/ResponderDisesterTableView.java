package Model;

import javafx.scene.control.Button;

public class ResponderDisesterTableView {

    private int disasterId;
    private String disasterTitle;
    private String status;
    private String priority;
    private Button startButton;
    private Button detailsButton;

    public ResponderDisesterTableView(int disasterId, String disasterTitle, String status, String priority) {
        this.disasterId = disasterId;
        this.disasterTitle = disasterTitle;
        this.status = status;
        this.priority = priority;
        this.startButton = new Button("Start");
        this.detailsButton = new Button("View Details");
    }

    public int getDisasterId() {
        return disasterId;
    }

    public String getDisasterTitle() {
        return disasterTitle;
    }

    public String getStatus() {
        return status;
    }

    public String getPriority() {
        return priority;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getDetailsButton() {
        return detailsButton;
    }
}
