package com.example.backend.DTO;

import com.example.backend.model.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {

    private Cliente cliente;

    private String password;

}
