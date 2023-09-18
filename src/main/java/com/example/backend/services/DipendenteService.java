package com.example.backend.services;

import com.example.backend.model.Cliente;
import com.example.backend.model.Dipendente;
import com.example.backend.model.Utente;
import com.example.backend.repositories.ClienteRepository;
import com.example.backend.repositories.DipendenteRepository;
import com.example.backend.support.UtenteAlreadyExistingException;
import com.example.backend.support.UtenteNotExistingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DipendenteService {

    @Autowired
    DipendenteRepository dipendenteRepository;

    @Transactional
    public Utente creaDipendente(Dipendente dipendente) {
        if (dipendenteRepository.existsByEmail(dipendente.getEmail()))
            throw new UtenteAlreadyExistingException("L'utente con username " +dipendente.getUsername()+ " è già esistente!");
        return dipendenteRepository.save(dipendente);
    }//CREATE

    @Transactional
    public void aggiornaDipendente(Dipendente dipendente) {
        if (!dipendenteRepository.existsById(dipendente.getId())) {
            throw new UtenteNotExistingException("Utente non esistente!");
        }
        dipendenteRepository.save(dipendente);
    }//UPDATE

    @Transactional
    public Long rimuoviDipendente(long idDipendente) {
        if (!dipendenteRepository.existsById(idDipendente))
            throw new UtenteNotExistingException("L'utente con id " +idDipendente+ " non esiste!");
        Dipendente c = dipendenteRepository.findById(idDipendente);
        dipendenteRepository.delete(c);
        return c.getId();
    }//DELETE


    @Transactional(readOnly = true)
    public List<Dipendente> findAll() {
        return dipendenteRepository.findAll();
    }//READ

    @Transactional(readOnly = true)
    public Dipendente findById(long id) {
        if (!dipendenteRepository.existsById(id)) throw new UtenteNotExistingException("dipendente non trovato");
        return dipendenteRepository.findById(id);
    }//READ byID

    @Transactional(readOnly = true)
    public List<Dipendente> findByNome(String nome) {
        if (!dipendenteRepository.existsByNome(nome)) throw new UtenteNotExistingException("Cliente non trovato!");
        return dipendenteRepository.findByNome(nome);
    }//READ byNome

    @Transactional(readOnly = true)
    public List<Dipendente> findByCognome(String cognome) {
        if (!dipendenteRepository.existsByCognome(cognome)) throw new UtenteNotExistingException("Utente non trovato!");
        return dipendenteRepository.findByCognome(cognome);
    }//READ byCognome

    @Transactional(readOnly = true)
    public Dipendente findByUsername(String username) {
        if (!dipendenteRepository.existsByUsername(username)) throw new UtenteNotExistingException("Utente non trovato!");
        return dipendenteRepository.findByUsername(username);
    }//READ byUsername

    @Transactional(readOnly = true)
    public Dipendente findByEmail(String email) {
        if (!dipendenteRepository.existsByEmail(email)) throw new UtenteNotExistingException("Utente non trovato!");
        return dipendenteRepository.findByEmail(email);
    }//READ byEmail


    @Transactional(readOnly = true)
    public boolean existsById(Long id) { return dipendenteRepository.existsById(id); }

    @Transactional(readOnly = true)
    public boolean existsByNome(String nome) { return dipendenteRepository.existsByNome(nome); }

    @Transactional(readOnly = true)
    public boolean existsByCognome(String cognome) { return dipendenteRepository.existsByCognome(cognome); }

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) { return dipendenteRepository.existsByUsername(username); }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) { return dipendenteRepository.existsByEmail(email); }


}
