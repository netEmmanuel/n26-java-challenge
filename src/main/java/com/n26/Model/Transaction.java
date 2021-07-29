package com.n26.Model;

import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * Created by oyelakin on 4/29/2021.
 * Transaction Model
 */
public class Transaction {

    @NotNull(message = "amount can not be null!")
    private double amount;

    @NotNull(message = "time can not be null!")
    private Instant  timestamp;

    public Transaction() {
    }

    /**
     *
     * @param amount
     * @param timestamp
     * Model Constructor
     */
    public Transaction(double amount, Instant  timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    /**
     *
     * @return the transaction amount
     */
    public double getAmount() {
        return amount;
    }


    /**
     *
     * @return the transaction timestamp
     */
    public Instant getTimestamp() {
        return timestamp;
    }


}
