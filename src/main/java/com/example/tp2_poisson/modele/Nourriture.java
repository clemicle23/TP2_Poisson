package com.example.tp2_poisson.modele;

import javafx.scene.paint.Color;

/**
 * Représente une nourriture dans le lac
 */
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

    /**
     * Mange la nourriture ssi elle n'est pas déjà mangée
     * Ne peut être appellée que par un thread à la fois grâce au mot-clef synchronised.
     * Cela assure qe 2 poissons ne peuvent manger la même nourriture, même si ils arrivent en même temps
     * @return true si la nourriture a été mangée, false sinon (car déjà mangée)
     */
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
