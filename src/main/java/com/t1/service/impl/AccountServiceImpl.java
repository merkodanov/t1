package com.t1.service.impl;

import com.t1.aop.LogDataSourceError;
import com.t1.dto.AccountRequestDto;
import com.t1.dto.AccountResponseDto;
import com.t1.mapper.AccountMapper;
import com.t1.model.Account;
import com.t1.repository.AccountRepository;
import com.t1.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    @LogDataSourceError
    public List<AccountResponseDto> getAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    @LogDataSourceError
    public AccountResponseDto getById(long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Клиент с id " + id + " не найден"));

        return accountMapper.toResponseDto(account);
    }

    @Override
    @LogDataSourceError
    public AccountResponseDto save(AccountRequestDto accountRequestDto) {
        return accountMapper.toResponseDto(accountRepository.save(accountMapper.toEntity(accountRequestDto)));
    }

    @Override
    @LogDataSourceError
    public void delete(long id) {
        boolean exists = accountRepository.existsById(id);
        if (!exists) {
            throw new EntityNotFoundException("Аккаунта с id " + id + " не существует");
        }
        accountRepository.deleteById(id);
    }
}
