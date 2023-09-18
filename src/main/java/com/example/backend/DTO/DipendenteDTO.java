package com.example.backend.DTO;

import com.example.backend.model.Dipendente;
import lombok.Data;

@Data
public class DipendenteDTO {

    private Dipendente dipendente;

    private String password;

}
