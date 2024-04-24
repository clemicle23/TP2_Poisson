package com.example.tp2_poisson.modele;

import com.example.tp2_poisson.Fenetre;
import com.example.tp2_poisson.modele.factory.NourritureFactory;
import com.example.tp2_poisson.modele.factory.PoissonFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.locks.*;

/**
 * Représente le lac qui contient la simulation
 */
public class Lac{
    private final ReadWriteLock rwlock = new ReentrantReadWriteLock(true);
    private final Lock rlock = rwlock.readLock();
    private final Lock wlock = rwlock.writeLock();
    private ArrayList<Poisson> poissons;
    private ArrayList<Nourriture> nourritures;
    private Fenetre fenetre;

    public Lac(Fenetre fenetre){
        this.fenetre = fenetre;
        poissons = new ArrayList<Poisson>();
        nourritures = new ArrayList<Nourriture>();
    }

    /**
     * Ajoute un poisson au lac
     * @param desiredType la classe de poisson désirée
     * @param speed la vitesse du poisson
     * @param X la position horizontale initiale du poisson
     * @param Y le position verticale initiale du poisson
     * @return le poisson créé
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public Poisson addPoisson(Class desiredType, float speed, float X, float Y) throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //Crée le poisson et l'englobe dans un Thread qui va le simuler
        try {
            PoissonManager newPoisson = new PoissonManager(this);
            Poisson poisson = PoissonFactory.newPoisson(desiredType, speed, X, Y);
            poissons.add(poisson);
            newPoisson.SetPoisson(poisson);
            Thread t = new Thread(newPoisson);
            t.start();
            return poisson;
        }
        catch(NoSuchMethodException | InstantiationException
              | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
            throw(e);
        }
    }

    /**
     * Ajoute une nourriture au lac
     * @param desiredType le type de nourriture desiré
     * @param X la position horizontale de la nourriture
     * @param Y la position verticale de la nourriture
     * @return la nourriture créée
     */
    public Nourriture addNourriture(Class desiredType, float X, float Y) throws NoSuchMethodException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        try {
            Nourriture nourriture = NourritureFactory.newNourriture(desiredType, X, Y);
            nourritures.add(nourriture);
            return nourriture;
        }
        catch(NoSuchMethodException | InstantiationException
              | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw(e);
        }
    }

    public ArrayList<Poisson> getPoissons(){
        return poissons;
    }

    public ArrayList<Nourriture> getNourritures(){
        return nourritures;
    }

    /**
     * Supprime les nourritures déjà mangées du lac
     * Cette méthode utilise un WriteLock pour assurer que la fonction plusProcheNourriture ne soit pas appellée en même temps,
     * et avoir la priorité dessus
     */
    public void cleanNourriture(){
        wlock.lock();
        try {
            if (nourritures == null) return;
            nourritures.removeIf(n -> !n.isAvailable());
        }
        finally{
            wlock.unlock();
        }
    }

    /**
     * Indique au poisson fourni la nourriture intéressante la plus proche de lui
     * Cette méthode utilise un ReadLock pour empêcher cleanNourriture de s'éxecuter en même temps,
     * tout en permettant à plusieurs threads d'appeller cette méthode simultanément.
     * @param poisson le poisson dont on souhaite trouver la nourriture intéressante la plus proche
     */
    public void plusProcheNourriture(Poisson poisson){
        rlock.lock();
        try{
            poisson.setPlusProcheNourriture(null);
            Nourriture nourritureActuelle = poisson.getPlusProcheNourriture();
            double minDistance = nourritureActuelle != null ? distance(poisson, nourritureActuelle) : Double.MAX_VALUE;
            for (Nourriture nourriture : nourritures) {
                if (nourriture == null)
                    continue;
                if (!nourriture.isAvailable())
                    continue;
                if (poisson.getNourriturePreferee() != nourriture.getClass())
                    continue;
                double nouvelleDistance = distance(poisson, nourriture);
                if (minDistance > nouvelleDistance) {
                    minDistance = nouvelleDistance;
                    poisson.setPlusProcheNourriture(nourriture);
                }
            }
        }
        finally{
            rlock.unlock();
        }
    }

    public static double distance(Poisson poisson, Nourriture nourriture){
        return(Math.sqrt(Math.pow((poisson.getCoordX()-nourriture.getCoordX()),2)+Math.pow((poisson.getCoordY()-nourriture.getCoordY()),2)));
    }


}
