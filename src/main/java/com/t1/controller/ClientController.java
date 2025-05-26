package com.t1.controller;

import com.t1.dto.ClientRequestDto;
import com.t1.dto.ClientResponseDto;
import com.t1.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getById(@PathVariable long id) {
        try {
            ClientResponseDto clientResponseDto = clientService.getById(id);
            return ResponseEntity.ok(clientResponseDto);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> getAll() {
        List<ClientResponseDto> clientResponseDtos = clientService.getAll();
        return clientResponseDtos.isEmpty() ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(clientResponseDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        try {
            clientService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@RequestBody ClientRequestDto clientRequestDto) {
        try {
            return ResponseEntity.ok(clientService.save(clientRequestDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
