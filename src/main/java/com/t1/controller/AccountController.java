package com.t1.controller;

import com.t1.dto.AccountRequestDto;
import com.t1.dto.AccountResponseDto;
import com.t1.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> getById(@PathVariable long id) {
        Optional<AccountResponseDto> accountResponseDto = accountService.getById(id);
        return accountResponseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> getAll() {
        List<AccountResponseDto> accountResponseDtos = accountService.getAll();
        return accountResponseDtos.isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(accountResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        try {
            accountService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> create(@RequestBody AccountRequestDto accountRequestDto) {
        try {
            return ResponseEntity.ok(accountService.save(accountRequestDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
