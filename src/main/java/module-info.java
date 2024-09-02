module com.drsmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    opens Model to javafx.base;
    opens com.drsmanagement to javafx.fxml;

    exports com.drsmanagement;
    exports Model;
}