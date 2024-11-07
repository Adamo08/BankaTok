package com.adamo;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private TypeTransaction typeTransaction;

    @JsonFormat(pattern = "yyyy-MM-dd")  // Serialize LocalDate in the specified format
    private final LocalDate timestamp;

    private final String reference;

    @JsonBackReference  // Prevents infinite recursion during serialization
    private final Compte sender;

    @JsonBackReference
    private final Compte receiver;

    private final double amount;

    // Constructor with validation
    public Transaction(
            TypeTransaction typeTransaction,
            String reference,
            Compte sender,
            Compte receiver,
            double amount
    ) {
        if (sender.equals(receiver)) {
            throw new IllegalArgumentException("Sender and receiver cannot be the same.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

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

    @Override
    public String toString() {
        return "Transaction{reference='" + reference + "', type=" + typeTransaction +
                ", sender=" + sender.getNumCompte() + ", receiver=" + receiver.getNumCompte() +
                ", amount=" + amount + ", timestamp=" + timestamp + "}";
    }

    // Gson JSON conversion methods
    public String toJson() throws JsonProcessingException {
        return JsonConverter.toJson(this);
    }

    public static Transaction fromJson(String json) throws IOException {
        return JsonConverter.fromJsonToTransaction(json);
    }
}
