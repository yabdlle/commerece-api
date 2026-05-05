package com.mod.commerce.repository

import com.mod.commerce.domain.model.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface OrderItemRepository : JpaRepository<OrderItem, UUID> {

    fun findByOrderId(orderId: UUID): List<OrderItem>

    fun findByProductId(productId: UUID): List<OrderItem>

    fun existsByOrderIdAndProductId(orderId: UUID, productId: UUID): Boolean

    fun findByOrderIdAndProductId(orderId: UUID, productId: UUID): OrderItem?
}