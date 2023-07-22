package com.example.backend.services;

import com.example.backend.model.Utente;
import com.example.backend.repositories.UtenteRepository;
import com.example.backend.support.UtenteAlreadyExistingException;
import com.example.backend.support.UtenteNotExistingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;


    @Transactional
    public Utente creaUtente(Utente utente) {
        if (utenteRepository.existsByUsername(utente.getUsername()))
            throw new UtenteAlreadyExistingException("L'utente con username " +utente.getUsername()+ " è già esistente!");
        return utenteRepository.save(utente);
    }//CREATE

    @Transactional
    public void aggiornaUtente(Utente utente) {
        if (!utenteRepository.existsById(utente.getId())) {
            throw new UtenteNotExistingException("Utente non esistente!");
        }
        utenteRepository.save(utente);
    }//UPDATE

    @Transactional
    public Long rimuoviUtente(long idUtente) {
        if (!utenteRepository.existsById(idUtente))
            throw new UtenteNotExistingException("L'utente con id " +idUtente+ " non esiste!");
        Utente u = utenteRepository.findById(idUtente);
        utenteRepository.delete(u);
        return u.getId();
    }//DELETE


    @Transactional(readOnly = true)
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }//READ

    @Transactional(readOnly = true)
    public Utente findById(long id) {
        if (!utenteRepository.existsById(id)) throw new UtenteNotExistingException("Utente non trovato");
        return utenteRepository.findById(id);
    }//READ byID

    @Transactional(readOnly = true)
    public List<Utente> findByNome(String nome) {
        if (!utenteRepository.existsByNome(nome)) throw new UtenteNotExistingException("Utente non trovato!");
        return utenteRepository.findByNome(nome);
    }//READ byNome

    @Transactional(readOnly = true)
    public List<Utente> findByCognome(String cognome) {
        if (!utenteRepository.existsByCognome(cognome)) throw new UtenteNotExistingException("Utente non trovato!");
        return utenteRepository.findByCognome(cognome);
    }//READ byCognome

    @Transactional(readOnly = true)
    public Utente findByUsername(String username) {
        if (!utenteRepository.existsByUsername(username)) throw new UtenteNotExistingException("Utente non trovato!");
        return utenteRepository.findByUsername(username);
    }//READ byUsername

    @Transactional(readOnly = true)
    public Utente findByEmail(String email) {
        if (!utenteRepository.existsByEmail(email)) throw new UtenteNotExistingException("Utente non trovato!");
        return utenteRepository.findByEmail(email);
    }//READ byEmail


    @Transactional(readOnly = true)
    public boolean existsById(Long id) { return utenteRepository.existsById(id); }

    @Transactional(readOnly = true)
    public boolean existsByNome(String nome) { return utenteRepository.existsByNome(nome); }

    @Transactional(readOnly = true)
    public boolean existsByCognome(String cognome) { return utenteRepository.existsByCognome(cognome); }

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) { return utenteRepository.existsByUsername(username); }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) { return utenteRepository.existsByEmail(email); }




}
