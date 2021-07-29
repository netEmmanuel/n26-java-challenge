package com.n26.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by oyelakin on 5/1/2021.
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Transaction time is in future")

/**
 * TransactionTimeInFutureException  Exception
 */
public class TransactionTimeInFutureException extends RuntimeException {

}

