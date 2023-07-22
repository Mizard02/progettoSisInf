package com.example.backend.repositories;

import com.example.backend.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    List<Utente> findAll();
    Utente findById(long id);
    List<Utente> findByNome(String nome);
    List<Utente> findByCognome(String cognome);
    Utente findByUsername(String username);
    Utente findByEmail(String email);

    @Override
    boolean existsById(Long id);
    boolean existsByNome(String nome);
    boolean existsByCognome(String cognome);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
