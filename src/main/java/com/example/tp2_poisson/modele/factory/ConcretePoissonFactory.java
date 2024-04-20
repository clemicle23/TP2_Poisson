package com.example.tp2_poisson.modele.factory;

import com.example.tp2_poisson.modele.Poisson;

public interface ConcretePoissonFactory {
    Poisson build(float speed, float X, float Y);
}
