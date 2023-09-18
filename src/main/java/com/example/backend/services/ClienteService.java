package com.example.backend.services;

import com.example.backend.DTO.ClienteDTO;
import com.example.backend.configuration.KeycloakCommand;
import com.example.backend.model.Cliente;
import com.example.backend.model.Utente;
import com.example.backend.repositories.ClienteRepository;
import com.example.backend.support.UtenteAlreadyExistingException;
import com.example.backend.support.UtenteNotExistingException;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.awt.event.KeyAdapter;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EntityManager entityManager;

    private static String username_admin = "admin@gmail.com";
    private static String password_admin = "admin";
    private static String clientId = "psw-admin-client";
    private static String role = "user2";
    private static String serverUrl = "http://localhost:8080/auth";
    private static String realm = "psw-realm";
    private static String clientSecret = "";


    @Transactional
    public Utente creaCliente(ClienteDTO cliente) {
        if (clienteRepository.existsByUsername(cliente.getCliente().getUsername()))
            throw new UtenteAlreadyExistingException("L'utente con username " +cliente.getCliente().getUsername()+ " è già esistente!");
        KeycloakCommand.AddUser(cliente.getCliente(), cliente.getPassword());
        return clienteRepository.save(cliente.getCliente());
    }//CREATE

    @Transactional
    public Cliente aggiornaCliente(String email, String value, String type){
        try {
            Cliente c = clienteRepository.findByEmail(email);
            switch (type) {
                case "nome":
                    c.setNome(value);
                    break;
                case "cognome":
                    c.setCognome(value);
                    break;
                case "residenza":
                    c.setResidenza(value);
                    break;
                case "IBAN":
                    c.setIBAN(value);
                    break;
                case "saldo punti":
                    c.setSaldoPunti(Integer.parseInt(value));
                    break;
                case "stato":
                    c.setStatoFedelta(value);
                    break;
                case "documento":
                    c.setDocumento_identita(value);
                    break;
                //case "numero":
                //    c.set(value);
                //    break;
                //case "email":
                //    c.setEmail(value);
                //    break;
            }
            entityManager.flush();
            return c;
        } catch (UtenteNotExistingException e) {
            throw new RuntimeException(e);
        }
    }

/*
    @Transactional
    public Long rimuoviCliente(String idCliente) {
        if (!clienteRepository.existsById(idCliente))
            throw new UtenteNotExistingException("L'utente con id " +idCliente+ " non esiste!");
        Cliente c = clienteRepository.findById(idCliente);
        KeycloakCommand.RemoveUtente(c);
        clienteRepository.delete(c);
        return c.getId();
    }//DELETE
 */

    @Transactional
    public void rimuoviCliente(String id){
        Cliente cliente = clienteRepository.findById(Long.parseLong(id));
        System.out.println("CLIENTE: " + cliente);
        if (!clienteRepository.existsById(Long.parseLong(id))) {
            throw new UtenteNotExistingException("Utente non esistente!");
        }

        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(username_admin)
                .password(password_admin)
                .build();

        String username = cliente.getEmail();
        List<UserRepresentation> userList = keycloak.realm(realm).users().search(username);
        for (UserRepresentation user : userList) {
            if (user.getUsername().equals(username)) {
                keycloak.realm(realm).users().delete(user.getId());
            }
        }
        clienteRepository.delete(cliente);
    }//cancellaUtente




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
