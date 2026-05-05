package com.mod.commerce.repository

import com.mod.commerce.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.UUID

@Repository
interface ProductRepository : JpaRepository<Product, UUID> {

    fun findByName(name: String): Product?

    fun existsByName(name: String): Boolean

    fun findByCategory(category: String): List<Product>

    fun findByStockQuantityGreaterThan(quantity: Int): List<Product>

    fun findByPriceBetween(min: BigDecimal, max: BigDecimal): List<Product>
}