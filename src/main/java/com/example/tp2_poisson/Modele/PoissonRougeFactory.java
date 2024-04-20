package com.example.tp2_poisson.Modele;

public class PoissonRougeFactory implements ConcretePoissonFactory{
    public PoissonRouge build(float speed, float X, float Y){
        return new PoissonRouge(speed, X, Y);
    }
}
