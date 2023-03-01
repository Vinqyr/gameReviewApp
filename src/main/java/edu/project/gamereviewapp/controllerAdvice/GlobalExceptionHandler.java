package edu.project.gamereviewapp.controllerAdvice;

import edu.project.gamereviewapp.DTO.ErrorDTO;
import edu.project.gamereviewapp.exception.GameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(RuntimeException exception) {
        final ErrorDTO errorDTO = new ErrorDTO(
                LocalDateTime.now(),
                exception.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
