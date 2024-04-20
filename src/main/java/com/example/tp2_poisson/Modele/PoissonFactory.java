package com.example.tp2_poisson.Modele;
import java.util.Map;

public class PoissonFactory{
    private static Map<Class, ConcretePoissonFactory> poissonClasses;

    static{
        poissonClasses.put(PoissonRouge.class, new PoissonRougeFactory());
        poissonClasses.put(PoissonBleu.class, new PoissonBleuFactory());
    }

    public static Poisson newPoisson(Class c, float speed, float X, float Y) throws IllegalArgumentException{
        if (!poissonClasses.containsKey(c)){
            throw new IllegalArgumentException("Invalid Poisson " + c);
        }
        return (Poisson) poissonClasses.get(c).build(speed, X, Y);
    }
}
