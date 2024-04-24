package com.example.tp2_poisson;

import com.example.tp2_poisson.graphismes.FenetreMannager;
import com.example.tp2_poisson.graphismes.dessins.DessinNourriture;
import com.example.tp2_poisson.graphismes.dessins.DessinPoisson;
import com.example.tp2_poisson.modele.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Fenetre extends Application {


    private ArrayList<DessinPoisson> dessinsPoissons;
    private ArrayList<DessinNourriture> dessinsNourritures;
    private Lac lac;
    private Pane drawPane;
    private GridPane buttonPane;
    private float taillePoisson = 6;
    private float tailleNourriture = 3;
    private String paletteType;
    private String paletteCouleur;

    @Override
    public void start(Stage stage) throws IOException{

        dessinsPoissons = new ArrayList<DessinPoisson>();
        dessinsNourritures = new ArrayList<DessinNourriture>();

        paletteType = "poisson";
        paletteCouleur = "rouge";

        lac = new Lac(this);

        BorderPane screen = new BorderPane();

        buttonPane = new GridPane();
        setupButtonPane();

        drawPane = new Pane();
        drawPane.setStyle("-fx-background-color: #5a5af6;");

        screen.setLeft(buttonPane);
        screen.setCenter(drawPane);

        Scene scene = new Scene(screen, 600, 400);

        stage.setTitle("Poissons dans un lac");

        /**
         * Détection du clic de la souris sur le lac, l'effet change en fonction de
         * la palette qui a été sélectionnée grâce aux boutons
         */
        drawPane.setOnMouseClicked(event -> {
            double mousex = event.getSceneX() - drawPane.getLayoutX();
            double mousey = event.getSceneY() - - drawPane.getLayoutY();


            switch (paletteType){
                case ("poisson"):
                    Poisson nouveauPoisson = null;
                    try{
                        switch (paletteCouleur) {
                            case ("rouge"):
                                nouveauPoisson = lac.addPoisson(PoissonRouge.class, 0.1f, (float) mousex, (float) mousey);
                                break;
                            case ("bleu"):
                                nouveauPoisson = lac.addPoisson(PoissonBleu.class, 0.1f, (float) mousex, (float) mousey);
                                break;
                            case ("vert"):
                                nouveauPoisson = lac.addPoisson(PoissonVert.class, 0.1f, (float) mousex, (float) mousey);
                                break;
                        }
                        Rectangle poissonRectangle = new Rectangle(nouveauPoisson.getCoordX(),nouveauPoisson.getCoordY(),taillePoisson,taillePoisson);
                        poissonRectangle.setFill(nouveauPoisson.getColor());
                        drawPane.getChildren().add(poissonRectangle);

                        DessinPoisson nouveauDessinPoisson = new DessinPoisson(nouveauPoisson, poissonRectangle);
                        dessinsPoissons.add(nouveauDessinPoisson);
                        break;
                    }
                    catch(NoSuchMethodException | InstantiationException |
                            IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
                        System.out.println(e.getMessage());
                    }
                case ("nourriture"):
                    Nourriture nouvelleNourriture = null;
                    try {
                        switch (paletteCouleur) {
                            case ("rouge"):
                                nouvelleNourriture = lac.addNourriture(NourritureRouge.class, (float) mousex, (float) mousey);
                                break;
                            case ("bleu"):
                                nouvelleNourriture = lac.addNourriture(NourritureBleu.class, (float) mousex, (float) mousey);
                                break;
                            case ("vert"):
                                nouvelleNourriture = lac.addNourriture(NourritureVert.class, (float) mousex, (float) mousey);
                                break;
                        }
                        Rectangle nourritureRectangle = new Rectangle(mousex, mousey,tailleNourriture,tailleNourriture);
                        nourritureRectangle.setFill(nouvelleNourriture.getColor());
                        drawPane.getChildren().add(nourritureRectangle);

                        DessinNourriture nouveauDessinNourriture = new DessinNourriture(nouvelleNourriture, nourritureRectangle);
                        dessinsNourritures.add(nouveauDessinNourriture);
                        break;
                    }
                    catch(NoSuchMethodException | InstantiationException |
                          IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        System.out.println(e.getMessage());
                    }
            }
        });

        stage.setScene(scene);
        stage.show();

        FenetreMannager newFenetreManager = new FenetreMannager(this);
        Thread t = new Thread(newFenetreManager);
        t.start();

    }

    /**
     * Met en place la partie de l'interface avec les boutons
     */
    public void setupButtonPane(){
        Label type = new Label("Type d'entité :");
        Label couleur = new Label("Couleur de l'entité :");

        Button setPalettePoisson = new Button("Poisson");
        setPalettePoisson.setOnAction((e) -> {
            this.paletteType = "poisson";
        });

        Button setPaletteNourriture = new Button("Nourriture");
        setPaletteNourriture.setOnAction((e) -> {
            this.paletteType = "nourriture";
        });

        Button setPaletteRouge = new Button("Rouge");
        setPaletteRouge.setOnAction((e) -> {
            this.paletteCouleur = "rouge";
        });

        Button setPaletteBleu = new Button("Bleu");
        setPaletteBleu.setOnAction((e) -> {
            this.paletteCouleur = "bleu";
        });

        Button setPaletteVert = new Button("Vert");
        setPaletteVert.setOnAction((e) -> {
            this.paletteCouleur = "vert";
        });

        buttonPane.add(type,3,3);
        buttonPane.add(setPalettePoisson,3,4);
        buttonPane.add(setPaletteNourriture,4,4);
        buttonPane.add(couleur,3,5);
        buttonPane.add(setPaletteRouge,3,6);
        buttonPane.add(setPaletteBleu,4,6);
        buttonPane.add(setPaletteVert,5,6);

    }

    /**
     * Permet d'actualiser l'affichage en mettant à jour
     * la position du rectangle sur l'interface graphique
     */
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

    /**
     * Permet de supprimer une nourriture de l'affichage
     * lorsqu'elle a été mangée, ainsi que de la supprimer
     * de la liste des nourritures du lac.
     */
    public void clean(){
        for (DessinNourriture dessinNourriture : dessinsNourritures){
            if (!dessinNourriture.getNourriture().isAvailable()){
                drawPane.getChildren().remove(dessinNourriture.getNourritureRectangle());
            }
        }
        dessinsNourritures.removeIf(n -> !n.getNourriture().isAvailable());
        lac.cleanNourriture();
    }

}