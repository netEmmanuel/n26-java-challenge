package com.n26.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by oyelakin on 4/29/2021.
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Transaction Time is older than 60 seconds")

/**
 * InvalidTransactionsException Exception
 */
public class InvalidTransactionsException extends RuntimeException {

}
