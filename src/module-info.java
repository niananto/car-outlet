module myjfx {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    opens fx to javafx.graphics, javafx.fxml, javafx.base;
}