package com.t1.service;

import com.t1.dto.ClientRequestDto;
import com.t1.dto.ClientResponseDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientResponseDto> getAll();

    Optional<ClientResponseDto> getById(long id);

    ClientResponseDto save(ClientRequestDto clientRequestDto);

    void delete(long id);
}
