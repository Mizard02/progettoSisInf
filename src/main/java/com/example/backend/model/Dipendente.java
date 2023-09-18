package com.example.backend.model;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dipendente", schema = "sisinf")
public class Dipendente extends Utente implements Serializable {

    @Basic
    @Column(name = "ruolo", nullable = false)
    private String ruolo;

}
