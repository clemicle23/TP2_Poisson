package com.example.tp2_poisson.modele.factory;
import com.example.tp2_poisson.modele.Poisson;
import com.example.tp2_poisson.modele.PoissonBleu;
import com.example.tp2_poisson.modele.PoissonRouge;
import com.example.tp2_poisson.modele.PoissonVert;

import java.util.HashMap;
import java.util.Map;

public class PoissonFactory{
    private static Map<Class, ConcretePoissonFactory> poissonClasses = new HashMap<>();

    static{
        poissonClasses.put(PoissonRouge.class, new PoissonRougeFactory());
        poissonClasses.put(PoissonBleu.class, new PoissonBleuFactory());
        poissonClasses.put(PoissonVert.class, new PoissonVertFactory());
    }

    public static Poisson newPoisson(Class c, float speed, float X, float Y) throws IllegalArgumentException{
        if (!poissonClasses.containsKey(c)){
            throw new IllegalArgumentException("Invalid Poisson " + c);
        }
        return (Poisson) poissonClasses.get(c).build(speed, X, Y);
    }
}
