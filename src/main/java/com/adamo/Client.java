package com.adamo;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    private final String numClient;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String telephone;
    private ArrayList<Compte> comptes;

    // Constructor
    public Client(
            String numClient,
            String nom,
            String prenom,
            String email,
            String adresse,
            String telephone
    ) {
        this.numClient = numClient;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
        this.comptes = new ArrayList<>();
    }


    // Add account
    public void addCompte(Compte compte) {
        comptes.add(compte);
    }

    // Gson JSON conversion methods
    public String toJson() throws JsonProcessingException {
        return JsonConverter.toJson(this);
    }

    public static Client fromJson(String json) throws IOException {
        return JsonConverter.fromJsonToClient(json);
    }
}
