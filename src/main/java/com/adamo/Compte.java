package com.adamo;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Compte {
    private final String numCompte;
    private final LocalDate dateCreation;
    private LocalDate dateUpdate;
    private Devise devise;
    private double balance;

    @JsonBackReference
    private final Client client;

    @JsonBackReference
    private final Banque banque;

    // Constructor with null checks for client and banque
    public Compte(
            String numCompte,
            Devise devise,
            Client client,
            Banque banque,
            double initialBalance
    ) {
        if (client == null || banque == null) {
            throw new IllegalArgumentException("Client and Banque must not be null");
        }
        this.numCompte = numCompte;
        this.dateCreation = LocalDate.now();
        this.dateUpdate = LocalDate.now();
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
        this.dateUpdate = LocalDate.now();
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

    // JSON conversion methods using JsonConverter
    public String toJson() throws JsonProcessingException {
        return JsonConverter.toJson(this);
    }

    public static Compte fromJson(String json) throws IOException {
        return JsonConverter.fromJsonToCompte(json);
    }
}
