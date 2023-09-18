package com.example.backend.support;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class Residenza implements Serializable{

    private String nazione;
    private String regione;
    private int CAP;
    private String via;


    public Residenza(String nazione, String regione, int CAP, String via) {
        this.nazione = nazione;
        this.regione = regione;
        this.CAP = CAP;
        this.via = via;
    } // AllArgsConstructor



/*
    public Residenza() {
        // Inizializza gli attributi con valori predefiniti o vuoti
        this.nazione = "";
        this.regione = "";
        this.CAP = 0;
        this.via = "";
    }
    */

}
