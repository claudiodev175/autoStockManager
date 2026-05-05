package br.com.autoStock.api.autoStock_api.exceptions;
import br.com.autoStock.api.autoStock_api.model.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ApiError> handleProdutoNaoEncontrado(
            ProdutoNaoEncontradoException ex,
            HttpServletRequest request
    ) {
        ApiError erro = ApiError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .erro(ex.getMessage())
                .caminho(request.getRequestURI())
                .data(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<ApiError> handleEstoqueInsuficiente(
            EstoqueInsuficienteException ex,
            HttpServletRequest request
    ) {
        ApiError erro = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .erro(ex.getMessage())
                .caminho(request.getRequestURI())
                .data(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(erro);
    }
}
