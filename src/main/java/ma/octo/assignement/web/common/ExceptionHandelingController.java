package ma.octo.assignement.web.common;

import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import ma.octo.assignement.exceptions.TransactionException;


@ControllerAdvice
public class ExceptionHandelingController {

    @ExceptionHandler(SoldeDisponibleInsuffisantException.class)
    public ResponseEntity<String> handleSoldeDisponibleInsuffisantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CompteNonExistantException.class)
    public ResponseEntity<String> handleCompteNonExistantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<String> handleTransactionException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
    }
}
