package com.example.backend.model;

import com.example.backend.support.Residenza;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente", schema = "sisinf")
public class Cliente extends Utente implements Serializable {

    @Basic
    @Column(name = "saldo_gioco", nullable = false/*, columnDefinition = "Decimal(10,2) default '0.0'"*/)
    private Double saldoGioco = 0.0;

    @Basic
    @Column(name = "saldo_punti", nullable = false/*,columnDefinition = "Decimal(10,2) default '0.0'"*/)
    private Integer saldoPunti = 0;

    @Basic
    @Column(name = "stato", nullable = false)
    private String statoFedelta = "base";

}
