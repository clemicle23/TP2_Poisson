module com.example.tp2_poisson {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tp2_poisson to javafx.fxml;
    exports com.example.tp2_poisson;
    exports com.example.tp2_poisson.Interface.dessins;
    opens com.example.tp2_poisson.Interface.dessins to javafx.fxml;
    exports com.example.tp2_poisson.Interface;
    opens com.example.tp2_poisson.Interface to javafx.fxml;
    exports com.example.tp2_poisson.Modele;
    opens com.example.tp2_poisson.Modele to javafx.fxml;
}