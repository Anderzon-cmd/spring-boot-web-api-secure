package bo.edu.devsecops.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final String adminPassword;

    public AuthController(@Value("${app.security.admin-password:}") String adminPassword) {
        this.adminPassword = adminPassword;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.getOrDefault("username", "");
        String password = credentials.getOrDefault("password", "");

        if ("admin".equals(username) && adminPassword.equals(password)) {
            return ResponseEntity.ok(Map.of(
                    "message", "Acceso autorizado"));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
    }
}
