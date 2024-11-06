package com.adamo;

import java.util.Date;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Compte {
    private final String numCompte;
    private final Date dateCreation;
    private Date dateUpdate;
    private Devise devise;
    private double balance;
    private final Client client;
    private final Banque banque;

    // Constructor
    public Compte(
            String numCompte,
            Devise devise,
            Client client,
            Banque banque,
            double initialBalance
    ) {
        this.numCompte = numCompte;
        this.dateCreation = new Date();
        this.dateUpdate = new Date();
        this.devise = devise;
        this.client = client;
        this.banque = banque;
        this.balance = initialBalance;
    }

    // Update balance method
    public boolean updateBalance(double amount) {
        if (this.balance + amount < 0) {
            return false; // Insufficient funds
        }
        this.balance += amount;
        this.dateUpdate = new Date();
        return true;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Compte compte) {
            return this.numCompte.equals(compte.numCompte);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.numCompte.hashCode();
    }

    // Gson JSON conversion methods
    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Compte fromJson(String json) {
        return new Gson().fromJson(json, Compte.class);
    }
}