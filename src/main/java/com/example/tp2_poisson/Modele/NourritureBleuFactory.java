package com.example.tp2_poisson.Modele;

public class NourritureBleuFactory implements ConcreteNourritureFactory{
    public NourritureBleu build(float X, float Y){
        return new NourritureBleu(X, Y);
    }
}
