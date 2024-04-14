package com.example.salhi_imen_mesure_glycemie.controller;

import com.example.salhi_imen_mesure_glycemie.model.Patient;

public final class Controller {
    private static Patient patient ;
    private static Controller instance = null;
    private Controller() {
        super ();
    }
    public static final Controller getInstance ()
    {
        if(instance == null) {
            instance =new Controller ();
        }
        return Controller.instance;
    }
    // Fleche "UserAction" View --> Controller
    public void createPatient (int age, float valeurMesuree, boolean isFasting)
    {
        // Fleche "Update" Controller --> Model
    patient = new Patient(age,valeurMesuree,isFasting);
    }
    // Fleche "Notify" Controller -->View
    public String getResult()
    {
        // Fleche "Notify" Model --> Controller
     return patient.getResult();
    }
}
