package com.dofire.user.src;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "users")
class UserEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String email;
    private String passwordHash;
    private String fullName;
    private Instant createdAt;
    private Instant updatedAt;

}