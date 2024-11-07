package com.adamo;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Banque {
    private final String id;
    private final String pays;
    private ArrayList<Compte> comptes;

    // Constructor
    public Banque(
            String id, String pays
    ) {
        this.id = id;
        this.pays = pays;
        this.comptes = new ArrayList<>();
    }

    // Add account
    public void addCompte(Compte compte) {
        comptes.add(compte);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banque banque = (Banque) o;
        return id.equals(banque.id);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    // Gson JSON conversion methods
    public String toJson() throws JsonProcessingException {
        return JsonConverter.toJson(this);
    }

    public static Banque fromJson(String json) throws IOException {
        return JsonConverter.fromJsonToBanque(json);
    }
}

