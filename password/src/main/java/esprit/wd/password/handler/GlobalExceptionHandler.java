package esprit.wd.password.handler;

import esprit.wd.password.exception.DataMismatchException;
import esprit.wd.password.exception.EmailSendFailedException;
import esprit.wd.password.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException exp) {
        Map<String, String> errors = new HashMap<>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }


    @ExceptionHandler(EmailSendFailedException.class)
    public ResponseEntity<?> handleException(EmailSendFailedException exp) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Password Email: ", exp.getMessage());
        return ResponseEntity
                .status(HttpStatus.IM_USED)
                .body(errors);
    }

    @ExceptionHandler(DataMismatchException.class)
    public ResponseEntity<?> handleException(DataMismatchException exp) {
        Map<String, String> errors = new HashMap<>();
        errors.put("user", exp.getMessage());
        return ResponseEntity
                .status(HttpStatus.IM_USED)
                .body(errors);
    }



    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleException(UserNotFoundException exp) {
        Map<String, String> errors = new HashMap<>();
        errors.put("user", exp.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errors);
    }

}
