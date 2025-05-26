package com.t1.service.impl;

import com.t1.aop.LogDataSourceError;
import com.t1.dto.ClientRequestDto;
import com.t1.dto.ClientResponseDto;
import com.t1.mapper.ClientMapper;
import com.t1.model.Client;
import com.t1.repository.ClientRepository;
import com.t1.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Override
    @LogDataSourceError
    public List<ClientResponseDto> getAll() {
        return clientRepository.findAll().stream().map(clientMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    @LogDataSourceError
    public ClientResponseDto getById(long id) {
        Client client =
                clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Клиент с id " + id + " не " +
                        "найден"));
        return clientMapper.toResponseDto(client);
    }

    @Override
    @LogDataSourceError
    public ClientResponseDto save(ClientRequestDto clientRequestDto) {
        return clientMapper.toResponseDto(clientRepository.save(clientMapper.toEntity(clientRequestDto)));
    }

    @Override
    @LogDataSourceError
    public void delete(long id) {
        boolean exists = clientRepository.existsById(id);
        if (!exists) {
            throw new EntityNotFoundException("Аккаунта с id " + id + " не существует");
        }
        clientRepository.deleteById(id);
    }
}
