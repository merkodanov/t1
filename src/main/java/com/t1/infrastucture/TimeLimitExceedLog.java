package com.t1.infrastucture;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TimeLimitExceedLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 1000)
    private String signature;

    private long executionTime;

    public TimeLimitExceedLog(String signature, long executionTime) {
        this.signature = signature;
        this.executionTime = executionTime;
    }
}
