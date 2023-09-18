package com.example.backend.repositories;


import com.example.backend.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {

    List<Dipendente> findAll();
    Dipendente findById(long id);
    List<Dipendente> findByNome(String nome);
    List<Dipendente> findByCognome(String cognome);
    Dipendente findByUsername(String username);
    Dipendente findByEmail(String email);


    boolean existsById(Long id);
    boolean existsByNome(String nome);
    boolean existsByCognome(String cognome);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
