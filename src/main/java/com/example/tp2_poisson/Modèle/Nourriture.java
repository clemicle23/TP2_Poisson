package com.example.tp2_poisson.Modèle;

public class Nourriture {
    public float coordX;
    public float coordY;
    private boolean available = true;

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
