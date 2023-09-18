package com.example.backend.controllers;

import com.example.backend.DTO.ClienteDTO;
import com.example.backend.model.Cliente;
import com.example.backend.model.Utente;
import com.example.backend.services.ClienteService;
import com.example.backend.support.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utente/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;


    @PostMapping
    public ResponseEntity<?> creaCliente(@RequestBody ClienteDTO cliente) {
        if(clienteService.existsByEmail(cliente.getCliente().getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("L'utente con email: " +cliente.getCliente().getEmail() + " è già esistente!"), HttpStatus.OK);
        }
        Utente risultato = clienteService.creaCliente(cliente);
        return new ResponseEntity<>(risultato.getId(), HttpStatus.OK);
    }//POST


/*
    @PutMapping("/{id}")
    public ResponseEntity<?> aggiornaCliente(@PathVariable("id") String id, @RequestBody Cliente cliente) {
        if(!clienteService.existsById(Long.parseLong(id))) {
            return new ResponseEntity<>(new ResponseMessage("L'utente non è esistente"), HttpStatus.OK);
        }
        Cliente daAggiornare = clienteService.findById(Long.parseLong(id));
        if (cliente.getNome() != null) { daAggiornare.setNome(cliente.getNome()); }
        if (cliente.getCognome() != null) { daAggiornare.setCognome(cliente.getCognome()); }
        if (cliente.getEmail() != null) { daAggiornare.setEmail(cliente.getEmail()); }
        if (cliente.getUsername() != null) { daAggiornare.setUsername(cliente.getUsername()); }
        //if (cliente.getPassword() != null) { daAggiornare.setPassword(cliente.getPassword()); }
        if (cliente.getIBAN() != null) { daAggiornare.setIBAN(cliente.getIBAN()); }
        if (cliente.getDocumento_identita() != null) { daAggiornare.setDocumento_identita(cliente.getDocumento_identita()); }
        if (cliente.getResidenza() != null) { daAggiornare.setResidenza(cliente.getResidenza()); }
        if (cliente.getSaldoGioco() != null) {daAggiornare.setSaldoGioco(cliente.getSaldoGioco());}
        if (cliente.getSaldoPunti() != null) {daAggiornare.setSaldoPunti(cliente.getSaldoPunti());}
        if (cliente.getStatoFedelta() != null) {daAggiornare.setStatoFedelta(cliente.getStatoFedelta());}
        clienteService.aggiornaCliente(daAggiornare);
        return new ResponseEntity<>(daAggiornare.getId(), HttpStatus.OK);
    }//PUT
*/

    @PutMapping("/modify")
    public ResponseEntity aggiornaCliente (@RequestParam String email, String value, String type){
        try {
            Cliente cliente = clienteService.aggiornaCliente(email, value, type);
            return new ResponseEntity(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.toString()), HttpStatus.BAD_REQUEST);
        }
    }



    @DeleteMapping("/id")
    public ResponseEntity<?> cancellaCliente(@RequestParam("id") String id) {
        if(!clienteService.existsById(Long.parseLong(id))) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        //Long l = clienteService.rimuoviCliente(Long.parseLong(id));
        clienteService.rimuoviCliente(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }//DELETE


    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Cliente> clienti = clienteService.findAll();
        if(clienti.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(clienti, HttpStatus.OK);
    }//GET findAll

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        if(!clienteService.existsById(Long.parseLong(id))) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        Cliente cliente = clienteService.findById(Long.parseLong(id));
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }//GET findByID

    @GetMapping("/email")
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
        if(!clienteService.existsByEmail(email)) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        Cliente cliente = clienteService.findByEmail(email);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }//GET findByEmail

    @GetMapping("/nome")
    public ResponseEntity<?> findByNome(@RequestParam("nome") String nome) {
        if(!clienteService.existsByNome(nome)) {
            return new ResponseEntity<>(new ResponseMessage("Non esiste alucn utente chiamato: " +nome), HttpStatus.OK);
        }
        List<Cliente> clienti = clienteService.findByNome(nome);
        if(clienti.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(clienti, HttpStatus.OK);
    }//GET findByNome

    @GetMapping("/cognome")
    public ResponseEntity<?> findByCognome(@RequestParam("cognome") String cognome) {
        if(!clienteService.existsByCognome(cognome)) {
            return new ResponseEntity<>(new ResponseMessage("Non esiste alcun utente con cognome: " +cognome), HttpStatus.OK);
        }
        List<Cliente> clienti = clienteService.findByCognome(cognome);
        if(clienti.size() == 0) {
            return new ResponseEntity<>(new ResponseMessage("Nessun Risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(clienti, HttpStatus.OK);
    }//GET findByCognome

    @GetMapping("/username")
    public ResponseEntity<?> findByUsername(@RequestParam("username") String username) {
        if(!clienteService.existsByUsername(username)) {
            return new ResponseEntity<>(new ResponseMessage("Utente non esistente!"), HttpStatus.OK);
        }
        Cliente cliente = clienteService.findByUsername(username);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }//GET findByUsername


}
