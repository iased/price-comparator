package com.uo.price_comparator.service;

import com.uo.price_comparator.dto.AccountDto;
import com.uo.price_comparator.dto.UpdateAccountRequest;
import com.uo.price_comparator.repository.GroceryListRepository;
import com.uo.price_comparator.repository.PriceAlertRepository;
import com.uo.price_comparator.user.User;
import com.uo.price_comparator.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AccountService {

    private final UserRepository userRepository;
    private final GroceryListRepository groceryListRepository;
    private final PasswordEncoder passwordEncoder;
    private final PriceAlertRepository priceAlertRepository;

    public AccountService(UserRepository userRepository,
                          GroceryListRepository groceryListRepository,
                          PasswordEncoder passwordEncoder,
                          PriceAlertRepository priceAlertRepository
    ) {
        this.userRepository = userRepository;
        this.groceryListRepository = groceryListRepository;
        this.passwordEncoder = passwordEncoder;
        this.priceAlertRepository = priceAlertRepository;
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Parola este obligatorie.");
        }

        if (!passwordEncoder.matches(password, u.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Parola este incorectă.");
        }

        groceryListRepository.deleteByUser(u);
        priceAlertRepository.deleteByUserId(u.getId());
        userRepository.delete(u);
    }
}
