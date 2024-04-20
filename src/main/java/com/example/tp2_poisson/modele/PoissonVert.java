package com.example.tp2_poisson.modele;
import javafx.scene.paint.Color;

public class PoissonVert extends Poisson{

    public PoissonVert(float speed, float coordX, float coordY){
        super(speed, coordX, coordY);
        this.setNourriturePreferee(NourritureVert.class);
        this.setColor(Color.GREEN);
    }

}
