package br.com.link_box_core_back_springboot.exceptions;

import br.com.link_box_core_back_springboot.dtos.ErrorFieldDTO;
import br.com.link_box_core_back_springboot.dtos.ResponseErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}

