package com.adamo;

import java.util.Date;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private TypeTransaction typeTransaction;
    private final Date timestamp;
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
        this.timestamp = new Date();
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
    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Transaction fromJson(String json) {
        return new Gson().fromJson(json, Transaction.class);
    }
}
