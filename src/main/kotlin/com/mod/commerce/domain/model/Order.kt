package com.mod.commerce.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "orders")
data class Order(
    @Id
    val id: UUID,
    val userId: UUID,
    @Enumerated(EnumType.STRING)
    val status: OrderStatus,
    val totalPrice: BigDecimal,
    val createdAt: Instant,
    val updatedAt: Instant,
)