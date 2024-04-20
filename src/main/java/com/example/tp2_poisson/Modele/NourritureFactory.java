package com.example.tp2_poisson.Modele;

import java.util.Map;

public class NourritureFactory {
    private static Map<Class, ConcreteNourritureFactory> NourritureClasses;

    static{
        NourritureClasses.put(NourritureRouge.class, new NourritureRougeFactory());
        NourritureClasses.put(NourritureBleu.class, new NourritureBleuFactory());
    }

    public static Nourriture newNourriture(Class c, float X, float Y) throws IllegalArgumentException{
        if (!NourritureClasses.containsKey(c)){
            throw new IllegalArgumentException("Invalid Nourriture " + c);
        }
        return (Nourriture) NourritureClasses.get(c).build(X, Y);
    }
}
