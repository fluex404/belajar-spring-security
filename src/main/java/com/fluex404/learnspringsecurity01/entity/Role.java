package com.fluex404.learnspringsecurity01.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role")
@NoArgsConstructor
public class Role extends BaseEntity {
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }
}
