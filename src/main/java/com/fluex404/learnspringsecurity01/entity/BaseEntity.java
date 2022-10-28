package com.fluex404.learnspringsecurity01.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "secure_id")
    private String secureId = UUID.randomUUID().toString();
}
