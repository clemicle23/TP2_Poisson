package com.example.tp2_poisson.modele;

import com.example.tp2_poisson.Fenetre;
import com.example.tp2_poisson.modele.factory.NourritureFactory;
import com.example.tp2_poisson.modele.factory.PoissonFactory;

import java.util.ArrayList;

public class Lac {
    private ArrayList<Poisson> poissons;
    private ArrayList<Nourriture> nourritures;
    private Fenetre fenetre;

    public Lac(Fenetre fenetre){
        this.fenetre = fenetre;
        poissons = new ArrayList<Poisson>();
        nourritures = new ArrayList<Nourriture>();
    }

    public Poisson addPoisson(Class desiredType, float speed, float X, float Y){
        PoissonManager newPoisson = new PoissonManager(this);
        Poisson poisson = PoissonFactory.newPoisson(desiredType, speed, X, Y);
        poissons.add(poisson);
        newPoisson.SetPoisson(poisson);
        Thread t = new Thread(newPoisson);
        t.start();
        return poisson;
    }

    public Nourriture addNourriture(Class desiredType, float X, float Y){
        Nourriture nourriture = NourritureFactory.newNourriture(desiredType, X, Y);
        nourritures.add(nourriture);
        return nourriture;
    }

    public ArrayList<Poisson> getPoissons(){
        return poissons;
    }

    public ArrayList<Nourriture> getNourritures(){
        return nourritures;
    }

    public void cleanNourriture(){
        if (nourritures == null) return;
        nourritures.removeIf(n -> !n.isAvailable());
    }

    public void plusProcheNourriture(Poisson poisson){
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
                double nouvelleDistance = distance(poisson,nourriture);
                if (minDistance > nouvelleDistance){
                    minDistance = nouvelleDistance;
                    poisson.setPlusProcheNourriture(nourriture);
                }
            }
    }

    public static double distance(Poisson poisson, Nourriture nourriture){
        return(Math.sqrt(Math.pow((poisson.getCoordX()-nourriture.getCoordX()),2)+Math.pow((poisson.getCoordY()-nourriture.getCoordY()),2)));
    }


}
