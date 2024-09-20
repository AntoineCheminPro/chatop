package com.openclassrooms.api_chatop.handlers;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import com.openclassrooms.api_chatop.Exception.EntityNotFoundException;
import com.openclassrooms.api_chatop.Exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<ErrorDTO> handleException(EntityNotFoundException exception, WebRequest request) {
            
            final HttpStatus notFound = HttpStatus.NOT_FOUND;
            final ErrorDTO errorDTO = ErrorDTO.builder()
            .code(exception.getErrorCodes())
            .httpCode(notFound.value())
            .message(exception.getMessage())
            .build();
            return new ResponseEntity<>(errorDTO, notFound);
        }

        @ExceptionHandler(InvalidEntityException.class)
        public ResponseEntity<ErrorDTO> handleException(InvalidEntityException exception, WebRequest request) {
            
            final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
            final ErrorDTO errorDTO = ErrorDTO.builder()
            .code(exception.getErrorCodes())
            .httpCode(badRequest.value())
            .message(exception.getMessage())
            .errors(exception.getErrors())
            .build();
            return new ResponseEntity<>(errorDTO, badRequest);
        }
}
