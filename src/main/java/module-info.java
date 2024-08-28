module com.drsmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.drsmanagement to javafx.fxml;
    exports com.drsmanagement;
}
