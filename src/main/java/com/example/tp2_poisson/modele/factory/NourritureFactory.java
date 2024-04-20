package com.example.tp2_poisson.modele.factory;

import com.example.tp2_poisson.modele.Nourriture;
import com.example.tp2_poisson.modele.NourritureBleu;
import com.example.tp2_poisson.modele.NourritureRouge;
import com.example.tp2_poisson.modele.NourritureVert;

import java.util.HashMap;
import java.util.Map;

public class NourritureFactory {
    private static Map<Class, ConcreteNourritureFactory> NourritureClasses = new HashMap<>();

    static{
        NourritureClasses.put(NourritureRouge.class, new NourritureRougeFactory());
        NourritureClasses.put(NourritureBleu.class, new NourritureBleuFactory());
        NourritureClasses.put(NourritureVert.class, new NourritureVertFactory());
    }

    public static Nourriture newNourriture(Class c, float X, float Y) throws IllegalArgumentException{
        if (!NourritureClasses.containsKey(c)){
            throw new IllegalArgumentException("Invalid Nourriture " + c);
        }
        return (Nourriture) NourritureClasses.get(c).build(X, Y);
    }
}
