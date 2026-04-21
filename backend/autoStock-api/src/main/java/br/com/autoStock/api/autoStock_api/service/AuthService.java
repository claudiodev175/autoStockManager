package br.com.autoStock.api.autoStock_api.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.autoStock.api.autoStock_api.model.Usuario;
import br.com.autoStock.api.autoStock_api.repository.UsuarioRepository;
import br.com.autoStock.api.autoStock_api.enums.Role;
import br.com.autoStock.api.autoStock_api.dto.RegisterRequestDTO;
import br.com.autoStock.api.autoStock_api.dto.LoginRequestDTO;
import br.com.autoStock.api.autoStock_api.dto.AuthResponseDTO;
import br.com.autoStock.api.autoStock_api.service.JwtService;




@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository,
                       JwtService jwtService,
                       PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        Usuario user = Usuario.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        usuarioRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        Usuario user = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponseDTO(token);
    }
}