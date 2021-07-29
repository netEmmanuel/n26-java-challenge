package com.n26;

import com.n26.Model.Statistics;
import com.n26.Model.Transaction;
import com.n26.Service.TransactionService;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.n26.Constant.AppConstant;
import java.time.Instant;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by oyelakin on 5/2/2021.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class StatsTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TransactionService transactionService;


    @Test
    public void testIfCurrentTimeReturnCreated() {

        assertEquals(HttpStatus.CREATED, restTemplate.postForEntity("/transactions", new Transaction(200.00, Instant.now()), Object.class)
                .getStatusCode());

    }

    @Test
    public void testIfPastTimeReturnNoContent() {

        assertEquals(HttpStatus.NO_CONTENT, restTemplate.postForEntity("/transactions", new Transaction(100.00, Instant.now().minusMillis(AppConstant.TRANSACTION_EXPIRED_MILI_SECONDS)), Object.class)
                .getStatusCode());

    }

    @Test
    public void testIfFutureTimeReturnUnprocessable() {

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, restTemplate.postForEntity("/transactions", new Transaction(100.00, Instant.now().plusMillis(AppConstant.TRANSACTION_EXPIRED_MILI_SECONDS)), Object.class)
                .getStatusCode());

    }


    @Test
    public void testIfTimestampIsNullReturnBadRequest() {
        assertEquals(HttpStatus.BAD_REQUEST, restTemplate.postForEntity("/transactions", new Transaction(34.44, null), Object.class)
                .getStatusCode());
    }

    @Test
    public void testCountValueOutput(){
        transactionService.deleteAllTransactions();
        transactionService.createTransaction(new Transaction(25.00,Instant.now()));
        transactionService.createTransaction(new Transaction(25.00,Instant.now()));
        transactionService.createTransaction(new Transaction(25.00,Instant.now()));
        transactionService.createTransaction(new Transaction(25.00,Instant.now()));
        transactionService.createTransaction(new Transaction(25.00,Instant.now()));
        Statistics stats = transactionService.getStatistics();
        assertTrue(Objects.equals(stats.getCount(), 5L));
    }
    @Test
    public void testSumValueOutput(){
        transactionService.deleteAllTransactions();
        transactionService.createTransaction(new Transaction(20.00,Instant.now()));
        transactionService.createTransaction(new Transaction(20.00,Instant.now()));
        transactionService.createTransaction(new Transaction(20.00,Instant.now()));
        transactionService.createTransaction(new Transaction(20.00,Instant.now()));
        transactionService.createTransaction(new Transaction(20.00,Instant.now()));
        Statistics stats = transactionService.getStatistics();
        assertTrue(stats.getSum().toString().equals("100.00"));
    }
    @Test
    public void testAvgValueOutput(){
        transactionService.deleteAllTransactions();
        transactionService.createTransaction(new Transaction(20.00,Instant.now()));
        transactionService.createTransaction(new Transaction(30.00,Instant.now()));
        transactionService.createTransaction(new Transaction(40.00,Instant.now()));
        transactionService.createTransaction(new Transaction(10.00,Instant.now()));
        transactionService.createTransaction(new Transaction(60.00,Instant.now()));
        Statistics stats = transactionService.getStatistics();
        assertTrue(stats.getAvg().toString().equals("32.00"));
    }
    @Test
    public void testMaxValueOutput(){
        transactionService.deleteAllTransactions();
        transactionService.createTransaction(new Transaction(20.00,Instant.now()));
        transactionService.createTransaction(new Transaction(30.00,Instant.now()));
        transactionService.createTransaction(new Transaction(40.00,Instant.now()));
        transactionService.createTransaction(new Transaction(10.00,Instant.now()));
        transactionService.createTransaction(new Transaction(60.00,Instant.now()));
        Statistics stats = transactionService.getStatistics();
        assertTrue(stats.getMax().toString().equals("60.00"));
    }
    @Test
    public void testMinValueOutput(){
        transactionService.deleteAllTransactions();
        transactionService.createTransaction(new Transaction(20.00,Instant.now()));
        transactionService.createTransaction(new Transaction(30.00,Instant.now()));
        transactionService.createTransaction(new Transaction(40.00,Instant.now()));
        transactionService.createTransaction(new Transaction(10.00,Instant.now()));
        transactionService.createTransaction(new Transaction(60.00,Instant.now()));
        Statistics stats = transactionService.getStatistics();
        assertTrue(stats.getMin().toString().equals("10.00"));
    }
    @Test
    public void deleteTest(){
        transactionService.createTransaction(new Transaction(20.00,Instant.now()));
        transactionService.createTransaction(new Transaction(30.00,Instant.now()));
        transactionService.createTransaction(new Transaction(40.00,Instant.now()));
        transactionService.deleteAllTransactions();
        Statistics stats = transactionService.getStatistics();
        assertTrue(Objects.equals(stats.getCount(), 0L));
    }

}
