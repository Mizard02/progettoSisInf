package com.example.backend.services;

import com.example.backend.DTO.ClienteDTO;
import com.example.backend.configuration.KeycloakCommand;
import com.example.backend.model.Cliente;
import com.example.backend.model.Utente;
import com.example.backend.repositories.ClienteRepository;
import com.example.backend.support.UtenteAlreadyExistingException;
import com.example.backend.support.UtenteNotExistingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;


    @Transactional
    public Utente creaCliente(ClienteDTO cliente) {
        if (clienteRepository.existsByUsername(cliente.getCliente().getUsername()))
            throw new UtenteAlreadyExistingException("L'utente con username " +cliente.getCliente().getUsername()+ " è già esistente!");
        KeycloakCommand.AddUser(cliente.getCliente(), cliente.getPassword());
        return clienteRepository.save(cliente.getCliente());
    }//CREATE



    @Transactional
    public void aggiornaCliente(ClienteDTO cliente) {
        if (!clienteRepository.existsById(cliente.getCliente().getId())) {
            throw new UtenteNotExistingException("Utente non esistente!");
        }
        clienteRepository.save(cliente.getCliente());
    }//UPDATE

    @Transactional
    public Long rimuoviCliente(long idCliente) {
        if (!clienteRepository.existsById(idCliente))
            throw new UtenteNotExistingException("L'utente con id " +idCliente+ " non esiste!");
        Cliente c = clienteRepository.findById(idCliente);
        clienteRepository.delete(c);
        return c.getId();
    }//DELETE


    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }//READ

    @Transactional(readOnly = true)
    public Cliente findById(long id) {
        if (!clienteRepository.existsById(id)) throw new UtenteNotExistingException("Cliente non trovato");
        return clienteRepository.findById(id);
    }//READ byID

    @Transactional(readOnly = true)
    public List<Cliente> findByNome(String nome) {
        if (!clienteRepository.existsByNome(nome)) throw new UtenteNotExistingException("Cliente non trovato!");
        return clienteRepository.findByNome(nome);
    }//READ byNome

    @Transactional(readOnly = true)
    public List<Cliente> findByCognome(String cognome) {
        if (!clienteRepository.existsByCognome(cognome)) throw new UtenteNotExistingException("Utente non trovato!");
        return clienteRepository.findByCognome(cognome);
    }//READ byCognome

    @Transactional(readOnly = true)
    public Cliente findByUsername(String username) {
        if (!clienteRepository.existsByUsername(username)) throw new UtenteNotExistingException("Utente non trovato!");
        return clienteRepository.findByUsername(username);
    }//READ byUsername

    @Transactional(readOnly = true)
    public Cliente findByEmail(String email) {
        if (!clienteRepository.existsByEmail(email)) throw new UtenteNotExistingException("Utente non trovato!");
        return clienteRepository.findByEmail(email);
    }//READ byEmail


    @Transactional(readOnly = true)
    public boolean existsById(Long id) { return clienteRepository.existsById(id); }

    @Transactional(readOnly = true)
    public boolean existsByNome(String nome) { return clienteRepository.existsByNome(nome); }

    @Transactional(readOnly = true)
    public boolean existsByCognome(String cognome) { return clienteRepository.existsByCognome(cognome); }

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) { return clienteRepository.existsByUsername(username); }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) { return clienteRepository.existsByEmail(email); }


}
