package com.example.tp2_poisson.modele.factory;

import com.example.tp2_poisson.modele.NourritureBleu;

public class NourritureBleuFactory implements ConcreteNourritureFactory{
    public NourritureBleu build(float X, float Y){
        return new NourritureBleu(X, Y);
    }
}
