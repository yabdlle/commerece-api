package com.mod.commerce.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "users")
data class User(
    @Id
    val id: UUID,
    val name: String,
    val email: String,
    val password: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)