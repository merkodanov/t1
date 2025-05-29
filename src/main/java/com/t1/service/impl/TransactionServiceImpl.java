package com.t1.service.impl;

import com.t1.aop.annotation.Cacheable;
import com.t1.aop.annotation.LogDataSourceError;
import com.t1.aop.annotation.TrackTime;
import com.t1.dto.TransactionRequestDto;
import com.t1.dto.TransactionResponseDto;
import com.t1.mapper.TransactionMapper;
import com.t1.model.Transaction;
import com.t1.repository.TransactionRepository;
import com.t1.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    @LogDataSourceError
    @TrackTime
    public List<TransactionResponseDto> getAll() {
        return transactionRepository.findAll()
                .stream().map(transactionMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    @LogDataSourceError
    @TrackTime
    @Cacheable
    public TransactionResponseDto getById(long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент с id " + id + " не найден"));
        return transactionMapper.toResponseDto(transaction);
    }

    @Override
    @LogDataSourceError
    @TrackTime
    public TransactionResponseDto save(TransactionRequestDto transactionRequestDto) {
        return transactionMapper.toResponseDto(
                transactionRepository.save(transactionMapper.toEntity(transactionRequestDto)));
    }

    @Override
    @LogDataSourceError
    @TrackTime
    public void delete(long id) {
        boolean exists = transactionRepository.existsById(id);
        if (!exists) {
            throw new EntityNotFoundException("Аккаунта с id " + id + " не существует");
        }
        transactionRepository.deleteById(id);
    }
}
