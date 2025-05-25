package com.t1.service;

import com.t1.dto.AccountRequestDto;
import com.t1.dto.AccountResponseDto;
import com.t1.mapper.AccountMapper;
import com.t1.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public List<AccountResponseDto> getAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toResponseDto).collect(Collectors.toList());
    }

    public Optional<AccountResponseDto> getById(long id) {
        return accountRepository.findById(id).map(accountMapper::toResponseDto);
    }

    public AccountResponseDto save(AccountRequestDto accountRequestDto) {
        return accountMapper.toResponseDto(accountRepository.save(accountMapper.toEntity(accountRequestDto)));
    }

    public void delete(long id) {
        accountRepository.deleteById(id);
    }
}
