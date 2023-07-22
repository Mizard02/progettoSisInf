package com.example.backend.model;

import com.example.backend.support.Residenza;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "utente", schema = "sisinf")
public class Utente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Basic
    @Column(name = "nome", nullable = false)
    private String nome;

    @Basic
    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Basic
    @Column(name = "data_nascita", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date data_nascita;

    @Basic
    @Column(name = "residenza", nullable = false)
    private Residenza residenza;

    @Basic
    @Column(name = "IBAN", nullable = false)
    private String IBAN;

    @Basic
    @Column(name = "documento_identita", nullable = false)
    private String documento_identita;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;

    @Basic
    @Column(name = "username", nullable = false)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

}
