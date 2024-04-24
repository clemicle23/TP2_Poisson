package com.example.tp2_poisson.modele.factory;
import com.example.tp2_poisson.modele.Nourriture;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Instantie tous les types de nourriture
 */
public class NourritureFactory {

    public static Nourriture newNourriture(Class c, float X, float Y) throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //Vérification que le type désiré est un sous-type de Nourriture
        if (c.getSuperclass().getCanonicalName() != Nourriture.class.getCanonicalName()){
            throw new IllegalArgumentException("Invalid Nourriture " + c.getSimpleName());
        }
        //Récupération du constructeur et instantiation de l'objet
        Class[] parameterTypes = {float.class, float.class,};
        try{
            Constructor constructor = c.getConstructor(parameterTypes);
            return (Nourriture) constructor.newInstance(X, Y);
        }
        catch(NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
            throw(e);
        }
    }
}
