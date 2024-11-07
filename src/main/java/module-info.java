module org.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens org.example.demo2 to javafx.fxml;
    exports org.example.demo2;
    exports org.example.demo2.GUI;
    opens org.example.demo2.GUI to javafx.fxml;
    opens org.example.demo2.Models to javafx.fxml;
}