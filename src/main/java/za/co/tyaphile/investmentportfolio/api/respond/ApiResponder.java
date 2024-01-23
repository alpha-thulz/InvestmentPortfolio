package za.co.tyaphile.investmentportfolio.api.respond;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Centralised response entity, extended by classes that response on API endpoints *
 */
public class ApiResponder {
    protected ResponseEntity<Object> getResponse(HttpStatus status, Object response) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
    protected ResponseEntity<Object> getResponseError(HttpStatus status, Object response) {
        Map<String, Object> error = Map.of("Error", response);
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }
}