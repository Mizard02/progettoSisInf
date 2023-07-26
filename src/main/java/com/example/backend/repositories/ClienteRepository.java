package com.example.backend.repositories;

import com.example.backend.model.Cliente;
import com.example.backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findAll();
    Cliente findById(long id);
    List<Cliente> findByNome(String nome);
    List<Cliente> findByCognome(String cognome);
    Cliente findByUsername(String username);
    Cliente findByEmail(String email);


    boolean existsById(Long id);
    boolean existsByNome(String nome);
    boolean existsByCognome(String cognome);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
