package com.t1.service;

import com.t1.dto.AccountRequestDto;
import com.t1.dto.AccountResponseDto;

import java.util.List;

public interface AccountService {
    List<AccountResponseDto> getAll();

    AccountResponseDto getById(long id);

    AccountResponseDto save(AccountRequestDto accountRequestDto);

    void delete(long id);
}
