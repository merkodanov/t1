package com.t1.mapper;

import com.t1.dto.ClientRequestDto;
import com.t1.dto.ClientResponseDto;
import com.t1.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientResponseDto toResponseDto(Client client) {
        return new ClientResponseDto(client.getId(), client.getFirstName(), client.getMiddleName(),
                client.getLastName());
    }

    public Client toEntity(ClientRequestDto clientRequestDto) {
        return new Client(clientRequestDto.id(), clientRequestDto.firstName(), clientRequestDto.middleName(),
                clientRequestDto.lastName());
    }
}
