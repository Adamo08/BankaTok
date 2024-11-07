package com.adamo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class JsonConverter {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // Register the JavaTimeModule to handle Java 8 date/time types like LocalDate
        mapper.registerModule(new JavaTimeModule());
        // Configure mapper to write dates as ISO-8601 strings (not timestamps)
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    // Convert an object to JSON
    public static String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    // Convert JSON to Client object
    public static Client fromJsonToClient(String json) throws IOException {
        return mapper.readValue(json, Client.class);
    }

    // Convert JSON to Compte object
    public static Compte fromJsonToCompte(String json) throws IOException {
        return mapper.readValue(json, Compte.class);
    }

    // Convert JSON to Banque object
    public static Banque fromJsonToBanque(String json) throws IOException {
        return mapper.readValue(json, Banque.class);
    }

    // Convert JSON to Transaction object
    public static Transaction fromJsonToTransaction(String json) throws IOException {
        return mapper.readValue(json, Transaction.class);
    }
}
