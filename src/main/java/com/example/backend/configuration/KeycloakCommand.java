package com.example.backend.configuration;

import com.example.backend.model.Utente;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;


public  class KeycloakCommand {

    private static String username_admin = "admin@gmail.com";
    private static String password_admin = "admin";
    private static String clientId = "psw-admin-client";
    private static String role = "user2";
    private static String serverUrl = "http://localhost:8080/auth";
    private static String realm = "psw-realm";
    private static String clientSecret = "bf2ecb27-7fc2-40fd-a848-058bb0161ad2";

    public static void AddUser(Utente utente, String password){

        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(username_admin)
                .password(password_admin)
                .build();

        // Define user
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(utente.getNome());
        user.setEmail(utente.getEmail());

        user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

        // Get realm
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersRessource = realmResource.users();

        // Create user (requires manage-users role)
        Response response = usersRessource.create(user);
        //System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
        //System.out.println(response.getLocation());
        String userId = CreatedResponseUtil.getCreatedId(response);
        //System.out.printf("User created with userId: %s%n", userId);

        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);

        UserResource userResource = usersRessource.get(userId);

        // Set password credential
        userResource.resetPassword(passwordCred);

        // Get client
        ClientRepresentation app1Client = realmResource.clients().findByClientId(clientId).get(0);

        // Get client level role (requires view-clients role)
        RoleRepresentation userClientRole = realmResource.clients().get(app1Client.getId()).roles().get(role).toRepresentation();

        // Assign client level role to user
        userResource.roles().clientLevel(app1Client.getId()).add(Arrays.asList(userClientRole));

        // Send password reset E-Mail
        // VERIFY_EMAIL, UPDATE_PROFILE, CONFIGURE_TOTP, UPDATE_PASSWORD, TERMS_AND_CONDITIONS
        //usersRessource.get(userId).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));

        // Delete User
        //userResource.remove();
    }



}


