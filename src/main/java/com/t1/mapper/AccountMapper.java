package com.t1.mapper;

import com.t1.dto.AccountRequestDto;
import com.t1.dto.AccountResponseDto;
import com.t1.model.Account;
import com.t1.model.Client;
import com.t1.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final ClientRepository clientRepository;

    public AccountResponseDto toResponseDto(Account account) {
        return new AccountResponseDto(account.getId(), account.getBalance(), account.getType(), account.getClient().getId());
    }

    public Account toEntity(AccountRequestDto accountRequestDto) {
        Client client = clientRepository.findById(accountRequestDto.clientId())
                .orElseThrow(() -> new RuntimeException("Клиента не существует"));

        return new Account(accountRequestDto.balance(), accountRequestDto.type(),
                client);
    }
}
