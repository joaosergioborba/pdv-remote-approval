package com.example.backend_java.Exception;

import com.example.backend_java.Erros.CadastroNaoEncontradoException;
import org.hibernate.validator.cfg.context.Cascadable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        // Percorre todos os erros dos campos validados no DTO
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();
            erros.put(campo, mensagem);
        });

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erros", erros);
        resposta.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExeptions(IllegalArgumentException ex){
        Map<String, String> erros = new HashMap<>();

        erros.put("matricula", ex.getMessage());

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.CONFLICT.value());
        resposta.put("erros", ex.getMessage());
        resposta.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
    }

    @ExceptionHandler(CadastroNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExeptions(CadastroNaoEncontradoException ex){

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.CONFLICT.value());
        resposta.put("erros", ex.getMessage());
        resposta.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

}