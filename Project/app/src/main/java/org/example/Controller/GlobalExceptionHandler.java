package org.example.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Traduzione centralizzata delle eccezioni dei Gestori in risposte HTTP.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    record ErrorResponse(String errore) {
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
        HttpStatus status = isNotFoundMessage(e.getMessage()) ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<ErrorResponse> handleIllegalState(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
    }

    /*
     * GestoreSottomissioni.getSottomissione lancia NullPointerException (non IllegalArgumentException come
     * il resto dei Gestori) quando la sottomissione non esiste: è uno scarto rispetto allo schema di eccezioni
     * usato ovunque nel resto dei Gestori. Mappata comunque a 404 così l'endpoint resta utilizzabile da Postman
     * nel frattempo.
     */
    @ExceptionHandler(NullPointerException.class)
    ResponseEntity<ErrorResponse> handleNullPointer(NullPointerException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }

    private boolean isNotFoundMessage(String message) {
        return message != null && message.toLowerCase().contains("non trovat");
    }
}
