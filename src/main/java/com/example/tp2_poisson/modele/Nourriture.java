package com.example.tp2_poisson.modele;

import javafx.scene.paint.Color;

public class Nourriture {
    public float coordX;
    public float coordY;
    private boolean available = true;
    private Color color;

    public Nourriture(float coordX, float coordY){
        this.coordX = coordX;
        this.coordY = coordY;
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

    public synchronized boolean eat(){

        if (available){
            System.out.println(available);
            available = false;
            return true;
        }
        return false;
    }

    public boolean isAvailable(){
        return available;
    }
}
