package br.com.diogozampar.salesapi.exception;

public class MissingEntityException extends RuntimeException {
    public MissingEntityException(String message) {
        super(message);
    }
    
}
