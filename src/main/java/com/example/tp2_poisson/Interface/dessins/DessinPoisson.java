package com.example.tp2_poisson.Interface.dessins;

import com.example.tp2_poisson.Modèle.Poisson;
import javafx.scene.shape.Rectangle;

public class DessinPoisson {

    private Poisson poisson;
    private Rectangle poissonRectangle;

    public DessinPoisson(Poisson poisson, Rectangle poissonRectangle){
        this.poisson = poisson;
        this.poissonRectangle = poissonRectangle;
    }

    public Poisson getPoisson(){
        return poisson;
    }
    public Rectangle getPoissonRectangle(){
        return poissonRectangle;
    }


}
