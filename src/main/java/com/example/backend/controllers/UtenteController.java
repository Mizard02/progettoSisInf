package com.example.backend.controllers;

import com.example.backend.model.Utente;
import com.example.backend.services.UtenteService;
import com.example.backend.support.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente")
@Validated
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @PostMapping
    public ResponseEntity<?> creaUtente(@RequestBody Utente utente) {
        if(utenteService.existsByEmail(utente.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("L'utente con email: " +utente.getEmail() + " è già esistente!"), HttpStatus.OK);
        }
        Utente risultato = utenteService.creaUtente(utente);
        return new ResponseEntity<>(risultato.getId(), HttpStatus.OK);
    }//POST


    @PutMapping("/{id}")
    public ResponseEntity<?> aggiornaUtente(@PathVariable("id") String id, @RequestBody Utente utente) {
        if(!utenteService.existsByEmail(utente.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("L'utente non è esistente"), HttpStatus.OK);
        }
        Utente daAggiornare = utenteService.findById(Long.parseLong(id));
        daAggiornare.setNome(utente.getNome());
        daAggiornare.setCognome(utente.getCognome());
        daAggiornare.setEmail(utente.getEmail());
        daAggiornare.setUsername(utente.getUsername());
        daAggiornare.setPassword(utente.getPassword());
        daAggiornare.setIBAN(utente.getIBAN());
        daAggiornare.setDocumento_identita(utente.getDocumento_identita());
        daAggiornare.setResidenza(utente.getResidenza());
        utenteService.aggiornaUtente(daAggiornare);
        return new ResponseEntity<>(daAggiornare.getId(), HttpStatus.OK);
    }//PUT


    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancellaUtente(@PathVariable("id") String id) {
        if(!utenteService.existsById(Long.parseLong(id))) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(utenteService.rimuoviUtente(Long.parseLong(id)), HttpStatus.OK);
    }//DELETE


    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Utente> utenti = utenteService.findAll();
        if(utenti.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }//GET findAll

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        if(!utenteService.existsById(Long.parseLong(id))) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        Utente utente = utenteService.findById(Long.parseLong(id));
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }//GET findByID

    @GetMapping("/email")
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
        if(!utenteService.existsByEmail(email)) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        Utente utente = utenteService.findByEmail(email);
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }//GET findByEmail

    @GetMapping("/nome")
    public ResponseEntity<?> findByNome(@RequestParam("nome") String nome) {
        if(!utenteService.existsByNome(nome)) {
            return new ResponseEntity<>(new ResponseMessage("Non esiste alucn utente chiamato: " +nome), HttpStatus.OK);
        }
        List<Utente> utenti = utenteService.findByNome(nome);
        if(utenti.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }//GET findByNome

    @GetMapping("/cognome")
    public ResponseEntity<?> findByCognome(@RequestParam("cognome") String cognome) {
        if(!utenteService.existsByCognome(cognome)) {
            return new ResponseEntity<>(new ResponseMessage("Non esiste alcun utente con cognome: " +cognome), HttpStatus.OK);
        }
        List<Utente> utenti = utenteService.findByCognome(cognome);
        if(utenti.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }//GET findByCognome

    @GetMapping("/username")
    public ResponseEntity<?> findByUsername(@RequestParam("username") String username) {
        if(!utenteService.existsByUsername(username)) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        Utente utente = utenteService.findByUsername(username);
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }//GET findByUsername

}//UtenteController
