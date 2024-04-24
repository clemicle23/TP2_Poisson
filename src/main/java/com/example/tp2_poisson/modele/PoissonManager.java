package com.example.tp2_poisson.modele;

/**
 * Thread qui contient un poisson et gère son exécution
 */
public class PoissonManager implements Runnable {
    private Poisson poisson;
    private Lac lac;
    public PoissonManager(Lac lac){
        this.lac = lac;
    }

    public void SetPoisson(Poisson newPoisson){
        poisson = newPoisson;
    }

    /**
     * Routine d'exécution du poisson :
     * - Trouver la nourriture la plus proche
     * - Avancer vers celle-ci
     * - Essayer de la manger si elle est proche
     */
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try{
                lac.plusProcheNourriture(poisson);
                poisson.step();
                Thread.sleep(10);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
