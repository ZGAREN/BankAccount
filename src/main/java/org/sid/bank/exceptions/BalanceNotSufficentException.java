package org.sid.bank.exceptions;

public class BalanceNotSufficentException extends Throwable {
    public BalanceNotSufficentException(String message) {
        super(message);
    }
}
