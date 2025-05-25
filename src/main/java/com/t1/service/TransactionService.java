package com.t1.service;

import com.t1.dto.TransactionRequestDto;
import com.t1.dto.TransactionResponseDto;
import com.t1.mapper.TransactionMapper;
import com.t1.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public List<TransactionResponseDto> getAll() {
        return transactionRepository.findAll()
                .stream().map(transactionMapper::toResponseDto).collect(Collectors.toList());
    }

    public Optional<TransactionResponseDto> getById(long id) {
        return transactionRepository.findById(id).map(transactionMapper::toResponseDto);
    }

    public TransactionResponseDto save(TransactionRequestDto transactionRequestDto) {
        return transactionMapper.toResponseDto(
                transactionRepository.save(transactionMapper.toEntity(transactionRequestDto)));
    }

    public void delete(long id) {
        transactionRepository.deleteById(id);
    }
}
