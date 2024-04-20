package com.example.tp2_poisson.Modele;

public class NourritureRougeFactory implements ConcreteNourritureFactory{
    public NourritureRouge build(float X, float Y){
        return new NourritureRouge(X, Y);
    }
}
