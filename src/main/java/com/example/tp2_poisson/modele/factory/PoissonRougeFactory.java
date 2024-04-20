package com.example.tp2_poisson.modele.factory;

import com.example.tp2_poisson.modele.PoissonRouge;

public class PoissonRougeFactory implements ConcretePoissonFactory{
    public PoissonRouge build(float speed, float X, float Y){
        return new PoissonRouge(speed, X, Y);
    }
}
