package com.mod.commerce.domain.repository

import com.mod.commerce.domain.model.Order
import com.mod.commerce.domain.model.OrderStatus
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

@Repository
interface OrderRepository : JpaRepository<Order, UUID> {

    fun findByUserId(userId: UUID): List<Order>

    fun findByStatus(status: OrderStatus): List<Order>

    fun existsByUserId(userId: UUID): Boolean
}