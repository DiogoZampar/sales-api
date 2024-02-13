package br.com.diogozampar.salesapi.exception;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler({MissingEntityException.class, MissingEntityException.class})
    protected ResponseEntity<Object> handleEntityNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    
    @ExceptionHandler({UniqueEntityFieldViolationException.class, DateInputException.class})
    protected ResponseEntity<Object> handleInvalidEntityFields(RuntimeException ex, WebRequest request){
      String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders httpHeaders, HttpStatusCode httpStatusCode, WebRequest request){
      String bodyOfResponse = ex.getAllErrors().get(0).getDefaultMessage();
      return handleExceptionInternal(ex, bodyOfResponse, httpHeaders, httpStatusCode, request);
    }
 

}
