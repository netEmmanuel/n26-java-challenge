package com.n26.Service;

import com.n26.Model.Transaction;
import com.n26.Model.Statistics;

/**
 * Created by oyelakin on 4/29/2021.
 */
public interface TransactionService {
    void  createTransaction(Transaction transaction);

    Statistics getStatistics();

    void deleteAllTransactions();
}
