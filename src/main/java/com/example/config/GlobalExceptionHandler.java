// package com.example.config;  // Or com.example.exception

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.RestControllerAdvice;

// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.validation.FieldError;

// @RestControllerAdvice
// public class GlobalExceptionHandler {

//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<Map<String, String>> handleValidationExceptions(
//             MethodArgumentNotValidException ex) {
//         Map<String, String> errors = new HashMap<>();
//         ex.getBindingResult().getAllErrors().forEach(error -> {
//             String fieldName = ((FieldError) error).getField();
//             String errorMessage = error.getDefaultMessage();
//             errors.put(fieldName, errorMessage);
//         });
//         return ResponseEntity.badRequest().body(errors);
//     }

//     // Add other exception handlers as needed
//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<String> handleGeneralExceptions(Exception ex) {
//         return ResponseEntity.internalServerError().body("An error occurred");
//     }
// }