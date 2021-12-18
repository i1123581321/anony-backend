package anony.controller;

import anony.payload.response.MessageResponse;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(JwtException.class)
    ResponseEntity<MessageResponse> jwtExceptionHandler(Exception exception) {
        log.info("Jwt Exception Handler");
        var msg = new MessageResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<MessageResponse> usernameNotFoundHandler(Exception exception) {
        log.info("Username not found handler");
        var msg = new MessageResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<MessageResponse> authenticationHandler(Exception exception) {
        log.info("Authentication exception");
        var msg = new MessageResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(msg);
    }
}
