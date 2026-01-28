package com.uo.price_comparator.auth;

import com.uo.price_comparator.auth.dto.*;
import com.uo.price_comparator.security.JwtService;
import com.uo.price_comparator.user.User;
import com.uo.price_comparator.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Există deja un cont asociat cu această adresă de email.");
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nu s-a găsit un cont asociat acestei adrese de email."));

        if (!encoder.matches(req.password, user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Autentificare eșuată. Verifică datele introduse.");
        }

        return new AuthResponse(jwt.generateToken(user.getEmail()), user.getEmail(), user.getName());
    }

    public User getCurrentUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utilizatorul nu a fost găsit."));
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
