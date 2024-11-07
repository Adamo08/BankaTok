package com.adamo;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Transaction {
    private TypeTransaction typeTransaction;
    private final LocalDate timestamp;
    private final String reference;
    private final Compte sender;
    private final Compte receiver;
    private final double amount;

    // Constructor
    public Transaction(
            TypeTransaction typeTransaction,
            String reference,
            Compte sender,
            Compte receiver,
            double amount
    ) {
        this.typeTransaction = typeTransaction;
        this.timestamp = LocalDate.now();
        this.reference = reference;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    // Process transaction between two accounts
    public boolean process() {
        // Attempt to debit sender and credit receiver
        if (sender.updateBalance(-amount)) {
            receiver.updateBalance(amount);
            return true;
        } else {
            System.out.println("Transaction failed: Insufficient funds in sender's account.");
            return false;
        }
    }

    // Gson JSON conversion methods
    public String toJson() throws JsonProcessingException {
        return JsonConverter.toJson(this);
    }

    public static Transaction fromJson(String json) throws IOException {
        return JsonConverter.fromJsonToTransaction(json);
    }
}
