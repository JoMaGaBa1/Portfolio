module org.Visor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires jasperreports;

    opens org.controlador to javafx.fxml;
    exports org.controlador;
}