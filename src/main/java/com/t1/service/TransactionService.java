package com.t1.service;

import com.t1.dto.TransactionRequestDto;
import com.t1.dto.TransactionResponseDto;

import java.util.List;

public interface TransactionService {
    List<TransactionResponseDto> getAll();

    TransactionResponseDto getById(long id);

    TransactionResponseDto save(TransactionRequestDto transactionRequestDto);

    void delete(long id);
}
