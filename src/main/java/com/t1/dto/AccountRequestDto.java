package com.t1.dto;

import com.t1.model.CreditType;

import java.math.BigDecimal;

public record AccountRequestDto(long id, BigDecimal balance, CreditType type, long clientId) {
}
