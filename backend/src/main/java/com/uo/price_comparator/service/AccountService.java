package com.uo.price_comparator.service;

import com.uo.price_comparator.dto.AccountDto;
import com.uo.price_comparator.dto.UpdateAccountRequest;
import com.uo.price_comparator.repository.GroceryListRepository;
import com.uo.price_comparator.user.User;
import com.uo.price_comparator.user.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final UserRepository userRepository;
    private final GroceryListRepository groceryListRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(UserRepository userRepository,
                          GroceryListRepository groceryListRepository,
                          PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.groceryListRepository = groceryListRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private User getCurrentUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit"));
    }

    public AccountDto getAccount(String email) {
        User u = getCurrentUser(email);
        return new AccountDto(u.getEmail(), u.getName());
    }

    public AccountDto updateAccount(String email, UpdateAccountRequest req) {
        String name = req == null ? null : req.name();
        if (name == null || name.trim().length() < 2) {
            throw new IllegalArgumentException("Numele trebuie să aibă minim 2 caractere.");
        }

        User u = getCurrentUser(email);
        u.setName(name.trim());
        userRepository.save(u);

        return new AccountDto(u.getEmail(), u.getName());
    }

    @Transactional
    public void deleteAccount(String email, String password) {
        User u = getCurrentUser(email);
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Parola este obligatorie.");
        }

        if (!passwordEncoder.matches(password, u.getPasswordHash())) {
            throw new BadCredentialsException("Parola este incorectă.");
        }

        groceryListRepository.deleteByUser(u);
        userRepository.delete(u);
    }
}
