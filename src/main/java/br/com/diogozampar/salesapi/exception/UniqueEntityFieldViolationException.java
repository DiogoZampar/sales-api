package br.com.diogozampar.salesapi.exception;

public class UniqueEntityFieldViolationException extends RuntimeException{
    public UniqueEntityFieldViolationException(String message){
        super(message);
    }
}
