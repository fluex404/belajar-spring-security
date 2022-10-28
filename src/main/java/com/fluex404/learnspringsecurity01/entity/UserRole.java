package com.fluex404.learnspringsecurity01.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role", indexes = {
        @Index(name = "unique_user_role", columnList = "user_id, role_id", unique = true)
})
@NoArgsConstructor
public class UserRole extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserData user;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    public UserRole(UserData user, Role role) {
        this.user = user;
        this.role = role;
    }
}
