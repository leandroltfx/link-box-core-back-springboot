package br.com.link_box_core_back_springboot.exceptions;

import br.com.link_box_core_back_springboot.dtos.ErrorFieldDTO;
import br.com.link_box_core_back_springboot.dtos.ResponseErrorDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.net.ConnectException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorDTO> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {

        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO("Dados inválidos.");

        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(err -> {
            ErrorFieldDTO errorFieldDto = new ErrorFieldDTO(err.getField(), err.getDefaultMessage());
            responseErrorDTO.getDetails().add(errorFieldDto);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseErrorDTO);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<ResponseErrorDTO> userFoundExceptionHandler(
            UserFoundException userFoundException
    ) {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(userFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseErrorDTO);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ResponseErrorDTO> connectExceptionHandler() {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO("Ocorreu um erro interno, tente novamente.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseErrorDTO);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseErrorDTO> authenticationExceptionHandler(
            AuthenticationException authenticationException
    ) {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(authenticationException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseErrorDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseErrorDTO> userNotFoundException(
            UserNotFoundException userNotFoundException
    ) {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(userNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseErrorDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseErrorDTO> entityNotFoundException(
            EntityNotFoundException entityNotFoundException
    ) {
        ResponseErrorDTO responseErrorDTO = new ResponseErrorDTO(entityNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseErrorDTO);
    }

}

