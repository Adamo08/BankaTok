package com.adamo;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Banque {
    private final String id;
    private final String pays;

    @JsonManagedReference
    private ArrayList<Compte> comptes;

    // Constructor with null checks for id and pays
    public Banque(String id, String pays) {
        if (id == null || id.isEmpty() || pays == null || pays.isEmpty()) {
            throw new IllegalArgumentException("id and pays must not be null or empty");
        }
        this.id = id;
        this.pays = pays;
        this.comptes = new ArrayList<>();
    }

    // Add account to the banque
    public void addCompte(Compte compte) {
        if (compte != null) {
            comptes.add(compte);
        }
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

    @Override
    public String toString() {
        return "Banque{id='" + id + "', pays='" + pays + "', comptes=" + comptes.size() + "}";
    }

    // Gson JSON conversion methods
    public String toJson() throws JsonProcessingException {
        return JsonConverter.toJson(this);
    }

    public static Banque fromJson(String json) throws IOException {
        return JsonConverter.fromJsonToBanque(json);
    }
}
