package com.example.tp2_poisson.modele.factory;

import com.example.tp2_poisson.modele.Nourriture;

public interface ConcreteNourritureFactory {
    Nourriture build(float X, float Y);
}
