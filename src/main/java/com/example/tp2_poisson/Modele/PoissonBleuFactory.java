package com.example.tp2_poisson.Modele;

public class PoissonBleuFactory implements ConcretePoissonFactory{

    public PoissonBleu build(float speed, float X, float Y){
        return new PoissonBleu(speed, X, Y);
    }
}
