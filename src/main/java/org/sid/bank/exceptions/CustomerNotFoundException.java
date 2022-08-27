package org.sid.bank.exceptions;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String message){
        super(message);
    }
}
