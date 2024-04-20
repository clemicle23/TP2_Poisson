package com.example.tp2_poisson.modele;
import javafx.scene.paint.Color;

public class PoissonBleu extends Poisson{

    public PoissonBleu(float speed, float coordX, float coordY){
        super(speed, coordX, coordY);
        this.setNourriturePreferee(NourritureBleu.class);
        this.setColor(Color.BLUE);
    }

}
