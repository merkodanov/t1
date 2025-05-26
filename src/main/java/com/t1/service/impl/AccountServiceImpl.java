package com.t1.service.impl;

import com.t1.dto.AccountRequestDto;
import com.t1.dto.AccountResponseDto;
import com.t1.mapper.AccountMapper;
import com.t1.repository.AccountRepository;
import com.t1.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountResponseDto> getAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    public Optional<AccountResponseDto> getById(long id) {
        return accountRepository.findById(id).map(accountMapper::toResponseDto);
    }

    @Override
    public AccountResponseDto save(AccountRequestDto accountRequestDto) {
        return accountMapper.toResponseDto(accountRepository.save(accountMapper.toEntity(accountRequestDto)));
    }

    @Override
    public void delete(long id) {
        accountRepository.deleteById(id);
    }
}
