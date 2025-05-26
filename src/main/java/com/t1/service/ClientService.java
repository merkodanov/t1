package com.t1.service;

import com.t1.dto.ClientRequestDto;
import com.t1.dto.ClientResponseDto;

import java.util.List;

public interface ClientService {
    List<ClientResponseDto> getAll();

    ClientResponseDto getById(long id);

    ClientResponseDto save(ClientRequestDto clientRequestDto);

    void delete(long id);
}
