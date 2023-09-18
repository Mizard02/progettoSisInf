package com.example.backend.controllers;

import com.example.backend.DTO.DipendenteDTO;
import com.example.backend.model.Dipendente;
import com.example.backend.model.Utente;
import com.example.backend.services.DipendenteService;
import com.example.backend.support.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente/dipendente")
public class DipendenteController {

    @Autowired
    DipendenteService dipendenteService;


    @PostMapping
    public ResponseEntity<?> creaCliente(@RequestBody DipendenteDTO dipendente) {
        if(dipendenteService.existsByEmail(dipendente.getDipendente().getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("L'utente con email: " +dipendente.getDipendente().getEmail() + " è già esistente!"), HttpStatus.OK);
        }
        Utente risultato = dipendenteService.creaDipendente(dipendente);
        return new ResponseEntity<>(risultato.getId(), HttpStatus.OK);
    }//POST





    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancellaCliente(@PathVariable("id") String id) {
        if(!dipendenteService.existsById(Long.parseLong(id))) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(dipendenteService.rimuoviDipendente(Long.parseLong(id)), HttpStatus.OK);
    }//DELETE


    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Dipendente> dipendenti = dipendenteService.findAll();
        if(dipendenti.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(dipendenti, HttpStatus.OK);
    }//GET findAll

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        if(!dipendenteService.existsById(Long.parseLong(id))) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        Dipendente dipendente = dipendenteService.findById(Long.parseLong(id));
        return new ResponseEntity<>(dipendente, HttpStatus.OK);
    }//GET findByID

    @GetMapping("/email")
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
        if(!dipendenteService.existsByEmail(email)) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        Dipendente dipendente = dipendenteService.findByEmail(email);
        return new ResponseEntity<>(dipendente, HttpStatus.OK);
    }//GET findByEmail

    @GetMapping("/nome")
    public ResponseEntity<?> findByNome(@RequestParam("nome") String nome) {
        if(!dipendenteService.existsByNome(nome)) {
            return new ResponseEntity<>(new ResponseMessage("Non esiste alucn utente chiamato: " +nome), HttpStatus.OK);
        }
        List<Dipendente> dipendente = dipendenteService.findByNome(nome);
        if(dipendente.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(dipendente, HttpStatus.OK);
    }//GET findByNome

    @GetMapping("/cognome")
    public ResponseEntity<?> findByCognome(@RequestParam("cognome") String cognome) {
        if(!dipendenteService.existsByCognome(cognome)) {
            return new ResponseEntity<>(new ResponseMessage("Non esiste alcun utente con cognome: " +cognome), HttpStatus.OK);
        }
        List<Dipendente> dipendente = dipendenteService.findByCognome(cognome);
        if(dipendente.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(dipendente, HttpStatus.OK);
    }//GET findByCognome

    @GetMapping("/username")
    public ResponseEntity<?> findByUsername(@RequestParam("username") String username) {
        if(!dipendenteService.existsByUsername(username)) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        Dipendente dipendente = dipendenteService.findByUsername(username);
        return new ResponseEntity<>(dipendente, HttpStatus.OK);
    }//GET findByUsername


}
