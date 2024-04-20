package com.example.tp2_poisson.graphismes;

import com.example.tp2_poisson.Fenetre;
import javafx.application.Platform;

public class FenetreMannager implements Runnable {

    private Fenetre fenetre;
    public FenetreMannager(Fenetre fenetre){
        this.fenetre = fenetre;
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try{
                Platform.runLater(()->{fenetre.afficheElements();
                fenetre.clean();});
                Thread.sleep(10);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
