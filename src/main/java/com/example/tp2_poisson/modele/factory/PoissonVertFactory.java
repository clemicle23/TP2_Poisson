package com.example.tp2_poisson.modele.factory;

import com.example.tp2_poisson.modele.PoissonVert;

public class PoissonVertFactory implements ConcretePoissonFactory{
    public PoissonVert build(float speed, float X, float Y){
        return new PoissonVert(speed, X, Y);
    }
}
