package com.example.backend.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Residenza implements Serializable {

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

}
