package com.example.tp2_poisson.Interface;

import com.example.tp2_poisson.Modèle.Lac;
import com.example.tp2_poisson.Modèle.Nourriture;
import com.example.tp2_poisson.Modèle.Poisson;
import com.example.tp2_poisson.Interface.dessins.DessinNourriture;
import com.example.tp2_poisson.Interface.dessins.DessinPoisson;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Fenetre extends Application {


    private ArrayList<DessinPoisson> dessinsPoissons;
    private ArrayList<DessinNourriture> dessinsNourritures;
    private Lac lac;
    private Pane drawPane;
    private GridPane buttonPane;
    private float taillePoisson = 5;
    private float tailleNourriture = 3;

    @Override
    public void start(Stage stage) throws IOException {

        dessinsPoissons = new ArrayList<DessinPoisson>();
        dessinsNourritures = new ArrayList<DessinNourriture>();

        lac = new Lac(this);

        BorderPane screen = new BorderPane();

        buttonPane = new GridPane();
        setupButtonPane();

        drawPane = new Pane();
        drawPane.setStyle("-fx-background-color: #4b4bfc;");

        screen.setLeft(buttonPane);
        screen.setCenter(drawPane);

        Scene scene = new Scene(screen, 320, 240);

        stage.setTitle("Hello!");

        stage.setScene(scene);
        stage.show();

        FenetreMannager newFenetre = new FenetreMannager(this);
        Thread t = new Thread(newFenetre);
        t.start();

    }

    public void setupButtonPane(){
        TextField poissonX = new TextField("Coordonnée X Poisson");
        TextField poissonY = new TextField("Coordonnée Y Poisson");

        TextField nourritureX = new TextField("Coordonnée X Nourriture");
        TextField nourritureY = new TextField("Coordonnée Y Nourriture");

        Button createPoisson = new Button("Crée un poisson");
        createPoisson.setOnAction((e) -> {
            Poisson nouveauPoisson = lac.addPoisson(0.1f, Float.parseFloat(poissonX.getText()), Float.parseFloat(poissonY.getText()));

            Rectangle poissonRectangle = new Rectangle(nouveauPoisson.getCoordX(),nouveauPoisson.getCoordY(),taillePoisson,taillePoisson);
            poissonRectangle.setFill(Color.RED);
            drawPane.getChildren().add(poissonRectangle);

            DessinPoisson nouveauDessin = new DessinPoisson(nouveauPoisson, poissonRectangle);
            dessinsPoissons.add(nouveauDessin);
        });

        Button createNourriture = new Button("Crée une nourriture");
        createNourriture.setOnAction((e) -> {
            Nourriture nouvelleNourriture = lac.addNourriture(Float.parseFloat(nourritureX.getText()), Float.parseFloat(nourritureY.getText()));

            Rectangle nourritureRectangle = new Rectangle(nouvelleNourriture.getCoordX(),nouvelleNourriture.getCoordY(),tailleNourriture,tailleNourriture);
            nourritureRectangle.setFill(Color.BROWN);
            drawPane.getChildren().add(nourritureRectangle);

            DessinNourriture nouveauDessin = new DessinNourriture(nouvelleNourriture, nourritureRectangle);
            dessinsNourritures.add(nouveauDessin);

        });

        buttonPane.add(poissonX,3,2);
        buttonPane.add(poissonY,3,3);
        buttonPane.add(createPoisson,3,4);
        buttonPane.add(nourritureX,3,5);
        buttonPane.add(nourritureY,3,6);
        buttonPane.add(createNourriture,3,7);
    }

    public void afficheElements(){
        if (lac.getPoissons() != null){
            for (DessinPoisson dessinPoisson: dessinsPoissons) {
                    dessinPoisson.getPoissonRectangle().setX(dessinPoisson.getPoisson().getCoordX());
                    dessinPoisson.getPoissonRectangle().setY(dessinPoisson.getPoisson().getCoordY());
            }
        }

        if (lac.getNourritures() != null) {
            for (DessinNourriture dessinNourriture : dessinsNourritures) {
                dessinNourriture.getNourritureRectangle().setX(dessinNourriture.getNourriture().getCoordX());
                dessinNourriture.getNourritureRectangle().setY(dessinNourriture.getNourriture().getCoordY());

            }
        }
    }

    public void clean(){
        for (DessinNourriture dessinNourriture : dessinsNourritures){
            if (!dessinNourriture.getNourriture().isAvailable()){
                drawPane.getChildren().remove(dessinNourriture.getNourritureRectangle());
            }
        }
        dessinsNourritures.removeIf(n -> !n.getNourriture().isAvailable());
        lac.cleanNourriture();
    }

    public static void main(String[] args) {
        launch();
    }
}