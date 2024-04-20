package com.example.tp2_poisson.graphismes.dessins;

import com.example.tp2_poisson.modele.Nourriture;
import javafx.scene.shape.Rectangle;

public class DessinNourriture {

    private Nourriture nourriture;
    private Rectangle nourritureRectangle;

    public DessinNourriture(Nourriture nourriture, Rectangle nourritureRectangle){
        this.nourriture = nourriture;
        this.nourritureRectangle = nourritureRectangle;
    }
    public Nourriture getNourriture(){
        return nourriture;
    }
    public Rectangle getNourritureRectangle(){
        return nourritureRectangle;
    }


}
