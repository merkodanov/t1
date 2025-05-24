package com.t1.infrastucture;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DataSourceErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "TEXT")
    private String stackTrace;
    private String message;
    private String signatureOfMethod;
}
