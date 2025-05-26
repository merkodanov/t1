package com.t1.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionRequestDto(BigDecimal totalAmount, Instant time, long accountId) {
}
