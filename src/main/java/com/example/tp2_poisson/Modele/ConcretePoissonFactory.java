package com.example.tp2_poisson.Modele;

public interface ConcretePoissonFactory {
    Poisson build(float speed, float X, float Y);
}
