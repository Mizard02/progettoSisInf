package com.example.backend.repositories;


import com.example.backend.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {

    List<Dipendente> findAll();
    Dipendente findById(long id);
    Dipendente findByEmail(String email);


    boolean existsById(Long id);
    boolean existsByEmail(String email);
}
