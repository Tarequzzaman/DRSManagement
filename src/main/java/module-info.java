module cqu.drsmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    opens cqu.drsmanagement to javafx.fxml;
    exports cqu.drsmanagement;
}
