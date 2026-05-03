package com.mod.commerce.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "products")
data class Product(
    @Id
    val id: UUID,
    val name: String,
    val price: BigDecimal,
    val stockQuantity: Int,
    val description: String?,
    val category: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)