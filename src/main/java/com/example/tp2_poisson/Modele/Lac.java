package com.example.tp2_poisson.Modele;

import com.example.tp2_poisson.Interface.Fenetre;

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

    public Poisson addPoisson(float speed, float X, float Y){
        PoissonManager newPoisson = new PoissonManager(this);
        Poisson poisson = new Poisson(speed, X, Y);
        poissons.add(poisson);
        newPoisson.SetPoisson(poisson);
        Thread t = new Thread(newPoisson);
        t.start();
        return poisson;
    }

    public Nourriture addNourriture(float X, float Y){
        Nourriture nourriture = new Nourriture(X,Y);
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
            for (Nourriture nourriture : nourritures){
                if (!nourriture.isAvailable())
                    continue;
                if (poisson.getNourriturePreferee() != nourritureActuelle.getClass())
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
