package com.adamo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;


import java.util.HashSet;

// Classe pour les méthodes de conversion JSON
public class JsonConverter {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // Configurer le mapper pour la conversion des dates
        mapper.findAndRegisterModules(); // Nécessaire pour les LocalDate
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    // Convertir un objet en JSON
    public static String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    // Convertir JSON en objet Client
    public static Client fromJsonToClient(String json) throws IOException {
        return mapper.readValue(json, Client.class);
    }

    // Convertir JSON en objet Compte
    public static Compte fromJsonToCompte(String json) throws IOException {
        return mapper.readValue(json, Compte.class);
    }

    // Convertir JSON en objet Banque
    public static Banque fromJsonToBanque(String json) throws IOException {
        return mapper.readValue(json, Banque.class);
    }

    // Convertir JSON en objet Banque
    public static Transaction fromJsonToTransaction(String json) throws IOException {
        return mapper.readValue(json, Transaction.class);
    }

}
