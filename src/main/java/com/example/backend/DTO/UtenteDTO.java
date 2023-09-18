package com.example.backend.DTO;

import com.example.backend.model.Utente;
import lombok.Data;

@Data
public class UtenteDTO {
    private Utente utente;

    private String password;
}