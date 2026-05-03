package com.mod.commerce.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "order_items")
data class OrderItem(
    @Id
    val id: UUID,
    val orderId: UUID,
    val productId: UUID,
    val quantity: Int,
    val price: BigDecimal,
    val createdAt: Instant,
    val updatedAt: Instant,
)