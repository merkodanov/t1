package com.t1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private CreditType type;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Account(BigDecimal balance, CreditType type, Client client) {
        this.balance = balance;
        this.type = type;
        this.client = client;
    }
}
