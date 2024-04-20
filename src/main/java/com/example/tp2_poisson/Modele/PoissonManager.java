package com.example.tp2_poisson.Modele;

public class PoissonManager implements Runnable {
    private Poisson poisson;
    private Lac lac;
    public PoissonManager(Lac lac){
        this.lac = lac;
    }

    public void SetPoisson(Poisson newPoisson){
        poisson = newPoisson;
    }
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
