package br.com.autoStock.api.autoStock_api.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.autoStock.api.autoStock_api.model.Usuario;
import br.com.autoStock.api.autoStock_api.service.AuthService;
import br.com.autoStock.api.autoStock_api.dto.RegisterRequestDTO;
import br.com.autoStock.api.autoStock_api.dto.LoginRequestDTO;
import br.com.autoStock.api.autoStock_api.dto.AuthResponseDTO;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

     @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}