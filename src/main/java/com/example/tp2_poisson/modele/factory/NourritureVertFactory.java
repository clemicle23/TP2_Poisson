package com.example.tp2_poisson.modele.factory;

import com.example.tp2_poisson.modele.NourritureVert;

public class NourritureVertFactory implements ConcreteNourritureFactory{
    public NourritureVert build(float X, float Y){
        return new NourritureVert(X, Y);
    }
}
