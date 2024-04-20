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
    exports com.example.tp2_poisson.graphismes;
    opens com.example.tp2_poisson.graphismes to javafx.fxml;
    exports com.example.tp2_poisson.graphismes.dessins;
    opens com.example.tp2_poisson.graphismes.dessins to javafx.fxml;
    exports com.example.tp2_poisson.modele;
    opens com.example.tp2_poisson.modele to javafx.fxml;
    exports com.example.tp2_poisson.modele.factory;
    opens com.example.tp2_poisson.modele.factory to javafx.fxml;
}