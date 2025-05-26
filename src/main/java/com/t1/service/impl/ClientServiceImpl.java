package com.t1.service.impl;

import com.t1.dto.ClientRequestDto;
import com.t1.dto.ClientResponseDto;
import com.t1.mapper.ClientMapper;
import com.t1.repository.ClientRepository;
import com.t1.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    @Override
    public List<ClientResponseDto> getAll() {
        return clientRepository.findAll().stream().map(clientMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ClientResponseDto> getById(long id) {
        return clientRepository.findById(id).map(clientMapper::toResponseDto);
    }

    @Override
    public ClientResponseDto save(ClientRequestDto clientRequestDto) {
        return clientMapper.toResponseDto(clientRepository.save(clientMapper.toEntity(clientRequestDto)));
    }

    @Override
    public void delete(long id) {
        clientRepository.deleteById(id);
    }
}
