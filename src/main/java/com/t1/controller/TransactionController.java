package com.t1.controller;

import com.t1.dto.TransactionRequestDto;
import com.t1.dto.TransactionResponseDto;
import com.t1.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> getById(@PathVariable long id) {
        Optional<TransactionResponseDto> transactionResponseDto = transactionService.getById(id);
        return transactionResponseDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> getAll() {
        List<TransactionResponseDto> transactionResponseDtos = transactionService.getAll();
        return transactionResponseDtos.isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(transactionResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        try {
            transactionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDto> create(@RequestBody TransactionRequestDto transactionRequestDto) {
        try {
            return ResponseEntity.ok(transactionService.save(transactionRequestDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
