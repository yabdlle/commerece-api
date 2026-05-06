package com.mod.commerce.service

import com.mod.commerce.domain.model.OrderItem
import com.mod.commerce.repository.OrderItemRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID


@Service
class OrderItemService (
    private val orderItemRepository: OrderItemRepository
) {
    fun getOrderItemById(id: UUID): OrderItem {
        return orderItemRepository.findById(id).orElseThrow { RuntimeException("Order item not found with id $id") }
    }

    fun getOrderItemsByOrderId(id: UUID): List<OrderItem> {
        return orderItemRepository.findByOrderId(id)
    }

    fun getOrderItemByProductId(productId: UUID): List<OrderItem> {
        return orderItemRepository.findByProductId(productId)
    }

    fun addOrderItem(
        orderId: UUID,
        productId: UUID,
        quantity: Int,
        price: BigDecimal
    ): OrderItem {
        val existing = orderItemRepository.findByOrderIdAndProductId(orderId, productId)

        if (existing != null) {
            val updated = existing.copy(
                quantity = existing.quantity + quantity,
                updatedAt = Instant.now(),
            )
            return orderItemRepository.save(updated)
        }

        val orderItem = OrderItem(
            id = UUID.randomUUID(),
            orderId = orderId,
            productId = productId,
            quantity = quantity,
            price = price,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )

        return orderItemRepository.save(orderItem)
    }

    fun updateOrderItemQuantity(id: UUID, quantity: Int): OrderItem {
        val orderItem = getOrderItemById(id)

        val updated = orderItem.copy(
            quantity = quantity,
            updatedAt = Instant.now(),
        )
        return orderItemRepository.save(updated)
    }

    fun removeOrderItem(id: UUID) {
        val orderItem = getOrderItemById(id)
        orderItemRepository.delete(orderItem)
    }

    fun removeAllOrderItems(orderId: UUID) {
        val items = orderItemRepository.findByOrderId(orderId)
        orderItemRepository.deleteAll(items)
    }
}


