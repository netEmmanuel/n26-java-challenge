package com.n26.Service;

import com.n26.Exception.InvalidTransactionsException;
import com.n26.Exception.TransactionTimeInFutureException;
import com.n26.Model.Statistics;
import com.n26.Model.Transaction;
import org.springframework.stereotype.Service;
import com.n26.Constant.AppConstant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * Created by oyelakin on 4/29/2021.
 */
@Service
public class TransactionServiceImpl implements TransactionService{

    //Using ReentrantLock for threadSafe
    private final ReentrantLock lock = new ReentrantLock(true);

    private static final long serialVersionUID=1L;
    public static Logger logger=Logger.getLogger("global");

    //store transactions
    private static final List<Transaction> TRANSACTION = new ArrayList<>();

    private Statistics statistics;

    /**
     * Create new transaction
     * @param transaction
     */
    @Override
    public void createTransaction(Transaction transaction) {
        lock.lock();
        try {
            if (!validateTimeStamp(transaction)) {
                throw new InvalidTransactionsException();
            }
            TRANSACTION.add(transaction);


        }
        finally {
            lock.unlock();
        }
   }

    /**
     *
     * @return stats
     */
    @Override
    public Statistics getStatistics() {
        lock.lock();
        try {
            if (statistics != null) {
                clearOldData();
            }
            if (statistics == null) {
                statistics = new Statistics();
            }
            return statistics;
        }
        finally {
            lock.unlock();
        }
    }

    /**
     * deletes stats
     */
    @Override
    public void deleteAllTransactions(){

        TRANSACTION.clear();
        statistics = new Statistics();

    }

    /**
     * calculates values and round up to 2 decimal places
     */
    private void calculateStats() {
        if (TRANSACTION.isEmpty()) {
            statistics = new Statistics();
        } else {
            DoubleSummaryStatistics stat = TRANSACTION.stream().mapToDouble(Transaction::getAmount)
                    .summaryStatistics();

            statistics = new Statistics(BigDecimal.valueOf(stat.getSum()).setScale(AppConstant.SCALE, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(stat.getAverage()).setScale(AppConstant.SCALE, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(stat.getMax()).setScale(AppConstant.SCALE, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(stat.getMin()).setScale(AppConstant.SCALE, RoundingMode.HALF_UP),
                    stat.getCount());

        }
    }

    /**
     * ensure old transactions are not returned
     */
    private void clearOldData() {
        TRANSACTION.removeIf(
                transaction -> (!validateTimeStamp(transaction)));

        calculateStats();
    }

    /**
     * check to make sure transaction timestamps are valid
     * @param transaction
     * @return boolean
     */
    private boolean validateTimeStamp(Transaction transaction) {

        if (Instant.now().toEpochMilli() < transaction.getTimestamp().toEpochMilli()) {
            throw new TransactionTimeInFutureException();
        }
        if ((Instant.now().toEpochMilli() - transaction.getTimestamp().toEpochMilli()) > AppConstant.TRANSACTION_EXPIRED_MILI_SECONDS
                && Instant.now().toEpochMilli() >= transaction.getTimestamp().toEpochMilli()) {
            logger.info(String.valueOf(transaction.getTimestamp().toEpochMilli()));
            return true;
        }

        return false;

    }
}
