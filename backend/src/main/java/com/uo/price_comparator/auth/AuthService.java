package com.uo.price_comparator.auth;

import com.uo.price_comparator.auth.dto.*;
import com.uo.price_comparator.security.JwtService;
import com.uo.price_comparator.user.User;
import com.uo.price_comparator.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(UserRepository userRepo, PasswordEncoder encoder, JwtService jwt) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public AuthResponse register(RegisterRequest req) {
        String email = req.email.toLowerCase().trim();

        if (userRepo.existsByEmail(email)) {
            throw new IllegalArgumentException("Există deja un cont asociat cu această adresă de email.");
        }

        User u = new User();
        u.setEmail(email);
        u.setName(req.name.trim());
        u.setPasswordHash(encoder.encode(req.password));
        userRepo.save(u);

        return new AuthResponse(jwt.generateToken(u.getEmail()), u.getEmail(), u.getName());
    }

    public AuthResponse login(LoginRequest req) {
        String email = req.email.toLowerCase().trim();

        var user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Autentificare eșuată. Verifică datele introduse."));

        if (!encoder.matches(req.password, user.getPasswordHash())) {
            throw new IllegalArgumentException("Autentificare eșuată. Verifică datele introduse.");
        }

        return new AuthResponse(jwt.generateToken(user.getEmail()), user.getEmail(), user.getName());
    }

    public User getCurrentUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Utilizatorul nu a fost găsit."));
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
