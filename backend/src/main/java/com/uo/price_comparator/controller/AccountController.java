package com.uo.price_comparator.controller;

import com.uo.price_comparator.dto.AccountDto;
import com.uo.price_comparator.dto.DeleteAccountRequest;
import com.uo.price_comparator.dto.UpdateAccountRequest;
import com.uo.price_comparator.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "*")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public AccountDto getAccount(Authentication auth) {
        return accountService.getAccount(auth.getName());
    }

    @PatchMapping
    public AccountDto updateAccount(
            @RequestBody UpdateAccountRequest req,
            Authentication auth
    ) {
        return accountService.updateAccount(auth.getName(), req);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(@RequestBody DeleteAccountRequest req,
                                              Authentication auth) {
        accountService.deleteAccount(auth.getName(), req.getPassword());
        return ResponseEntity.noContent().build();
    }
}
