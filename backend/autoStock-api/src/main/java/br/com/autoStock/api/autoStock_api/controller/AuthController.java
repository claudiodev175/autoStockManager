package br.com.autoStock.api.autoStock_api.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.autoStock.api.autoStock_api.model.Usuario;
import br.com.autoStock.api.autoStock_api.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Usuario usuario) {
        return authService.register(usuario.getEmail(), usuario.getPassword(), usuario.getNome());
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        return authService.login(usuario.getEmail(), usuario.getPassword());
    }
}