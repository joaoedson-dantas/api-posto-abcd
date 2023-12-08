package posto.abcd.api.infra.exceptions;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleException404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleException400(MethodArgumentNotValidException exception) {

        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(dataValidationErrors::new).toList());
    }


    // dto - para devolver o erro
    private record dataValidationErrors(String field, String message) {
        public dataValidationErrors(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }


}



// Classe responsável por isolar o tratamento de erros na api; @RestControllerAdvice
// em cima do metodo informar para qual ecessao o metoo handleExcepetion404 vai ser chama?