package com.example.tp2_poisson.modele.factory;

import com.example.tp2_poisson.modele.PoissonBleu;

public class PoissonBleuFactory implements ConcretePoissonFactory{

    public PoissonBleu build(float speed, float X, float Y){
        return new PoissonBleu(speed, X, Y);
    }
}
