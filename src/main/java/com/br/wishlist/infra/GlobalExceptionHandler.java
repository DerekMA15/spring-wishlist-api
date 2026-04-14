package com.br.wishlist.infra;

import com.br.wishlist.dto.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException; // Import necessário
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.swagger.v3.oas.annotations.Hidden;

import java.util.List;

@RestControllerAdvice
@Hidden // <- faz o swagger ignorar está classe pq tav dando erro com o @RestControllerAdvice
public class GlobalExceptionHandler {

    // Adicionamos <Object> para resolver o erro de Raw Type
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handle404() {
        return ResponseEntity.notFound().build();
    }

    // Usamos <ExceptionDTO> para tipar a resposta
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDTO> handleRuntimeException(RuntimeException ex) {
        ExceptionDTO erro = new ExceptionDTO(ex.getMessage(), "400");
        return ResponseEntity.badRequest().body(erro);
    }

    // Captura erros de validação (ex: campos vazios)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationError(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();
        
        // Retorna uma lista de strings com os erros de validação
        return ResponseEntity.badRequest().body(erros);
    }
}