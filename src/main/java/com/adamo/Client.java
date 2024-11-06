package com.adamo;

import java.util.ArrayList;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {
    private final int numClient;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String telephone;
    private ArrayList<Compte> comptes;

    // Constructor
    public Client(
            int numClient,
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
    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Client fromJson(String json) {
        return new Gson().fromJson(json, Client.class);
    }
}
