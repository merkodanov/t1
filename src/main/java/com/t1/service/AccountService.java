package com.t1.service;

import com.t1.dto.AccountRequestDto;
import com.t1.dto.AccountResponseDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<AccountResponseDto> getAll();

    Optional<AccountResponseDto> getById(long id);

    AccountResponseDto save(AccountRequestDto accountRequestDto);

    void delete(long id);
}
