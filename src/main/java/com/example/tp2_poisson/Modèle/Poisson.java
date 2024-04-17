package com.example.tp2_poisson.Modèle;

public class Poisson {
    private float speed;
    private float coordX;
    private float coordY;
    private Nourriture plusProcheNourriture;

    public Poisson(float speed, float coordX, float coordY) {
        this.speed = speed;
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public void step() {
        if (plusProcheNourriture == null)
            return;
        if (!plusProcheNourriture.isAvailable()){
            plusProcheNourriture = null;
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

    public void setPlusProcheNourriture(Nourriture nourriture){
        this.plusProcheNourriture = nourriture;
    }
    public Nourriture getPlusProcheNourriture(){
        return plusProcheNourriture;
    }

    public void tryEatFood(Nourriture nourriture){
        //System.out.println("Poisson " + Thread.currentThread().getId() + " essaie de manger en position " + coordX + " " + coordY);
        if (nourriture.eat()){
            System.out.println("Poisson " + Thread.currentThread().getId() + " a mangé");
            plusProcheNourriture = null;
        }
    }
}
