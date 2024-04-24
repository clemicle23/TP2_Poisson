package com.example.tp2_poisson.modele;

import javafx.scene.paint.Color;

/**
 * Représente un poisson et fournit les méthodes nécessaires à sa simulation
 *
 */

public class Poisson {
    private static int jitterDuration = 50;
    private static float jitterChance = 0.005f;

    private float speed;
    private float coordX;
    private float coordY;
    private Nourriture plusProcheNourriture;
    private int currentJitterFrame;
    private double currentJitterX;
    private double currentJitterY;
    private Class nourriturePreferee;
    private Color color;

    public Poisson(float speed, float coordX, float coordY) {
        this.speed = speed;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    /**
     * Simule une frame
     * Le poisson essaie de faire un sursaut aléatoire
     * Si il ne sursaute pas, il avance vers la nourriture la plus proche si elle existe
     * Si il arrive assez proche de la nourriture, il essaie de la manger
     */
    public void step() {
        if (plusProcheNourriture == null)
            return;
        if (!plusProcheNourriture.isAvailable()){
            plusProcheNourriture = null;
            return;
        }
        if (currentJitterFrame == 0){
            if (Math.random() <= jitterChance){
                currentJitterFrame = jitterDuration;
                currentJitterX = Math.random();
                currentJitterY = Math.random();
                double magnitude = Math.sqrt(currentJitterX * currentJitterX + currentJitterY * currentJitterY);
                if (magnitude >= 0.01)
                {
                    currentJitterX /= magnitude;
                    currentJitterY /= magnitude;
                }
            }
        }
        if (currentJitterFrame != 0){
            currentJitterFrame -= 1;
            coordX += speed * currentJitterX;
            coordY += speed * currentJitterY;
            return;
        }

        float deltaX = plusProcheNourriture.coordX - coordX;
        float deltaY = plusProcheNourriture.coordY - coordY;
        double magnitude = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if (magnitude >= 0.001) {
            deltaX /= magnitude;
            deltaY /= magnitude;
            coordX += speed * deltaX;
            coordY += speed * deltaY;
        }
        if (Lac.distance(this, plusProcheNourriture) < 0.5) {
            tryEatFood(plusProcheNourriture);
        }
    }

    public float getCoordX(){
        return coordX;
    }
    public float getCoordY(){
        return coordY;
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }

    public Class getNourriturePreferee(){
        return nourriturePreferee;
    }

    public void setNourriturePreferee(Class nourriturePreferee){
        this.nourriturePreferee = nourriturePreferee;
    }

    public void setPlusProcheNourriture(Nourriture nourriture){
        this.plusProcheNourriture = nourriture;
    }
    public Nourriture getPlusProcheNourriture(){
        return plusProcheNourriture;
    }

    /**
     * Mange la nourriture ssi elle est disponible
     * @param nourriture la nourriture à manger
     */
    public void tryEatFood(Nourriture nourriture){
        if (nourriture.eat()){
            System.out.println("Poisson " + Thread.currentThread().getId() + " a mangé");
            plusProcheNourriture = null;
        }
    }


}
