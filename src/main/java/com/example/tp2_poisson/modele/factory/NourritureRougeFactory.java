package com.example.tp2_poisson.modele.factory;

import com.example.tp2_poisson.modele.NourritureRouge;

public class NourritureRougeFactory implements ConcreteNourritureFactory{
    public NourritureRouge build(float X, float Y){
        return new NourritureRouge(X, Y);
    }
}
