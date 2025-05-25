package com.t1.mapper;

import com.t1.dto.TransactionRequestDto;
import com.t1.dto.TransactionResponseDto;
import com.t1.model.Account;
import com.t1.model.Transaction;
import com.t1.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionMapper {
    private final AccountRepository accountRepository;

    public TransactionResponseDto toResponseDto(Transaction transaction) {
        return new TransactionResponseDto(transaction.getId(), transaction.getTotalAmount(), transaction.getTime(),
                transaction.getAccount().getId());
    }

    public Transaction toEntity(TransactionRequestDto transactionRequestDto) {
        Account account = accountRepository.findById(transactionRequestDto.accountId())
                .orElseThrow(() -> new RuntimeException("Аккаунта не существует"));

        return new Transaction(transactionRequestDto.id(), transactionRequestDto.totalAmount(),
                transactionRequestDto.time(), account);
    }
}
