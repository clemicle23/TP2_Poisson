package com.example.tp2_poisson.modele.factory;
import com.example.tp2_poisson.modele.Poisson;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Instantie tous les types de poissons.
 */
public class PoissonFactory{

    /**
     * Crée un poisson du type désiré
     * @param c Sous-classe de Poisson à créer
     * @param speed vitesse du poisson
     * @param X position horizontale initiale du poisson
     * @param Y position verticale initiale du poisson
     * @return le poisson créé
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Poisson newPoisson(Class c, float speed, float X, float Y) throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //Vérification que le type désiré est un sous-type de Poisson
        if (c.getSuperclass().getCanonicalName() != Poisson.class.getCanonicalName()){
            throw new IllegalArgumentException("Invalid Poisson " + c.getSimpleName());
        }
        //Récupération du constructeur et création du poisson
        Class[] parameterTypes = {float.class, float.class, float.class};
        try{
        Constructor constructor = c.getConstructor(parameterTypes);
            return (Poisson) constructor.newInstance(speed, X, Y);
        }
        catch(NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
            throw(e);
        }
    }
}
