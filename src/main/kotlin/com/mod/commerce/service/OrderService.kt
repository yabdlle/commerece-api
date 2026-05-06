package com.mod.commerce.service
import com.mod.commerce.domain.model.Order
import com.mod.commerce.domain.model.OrderStatus
import com.mod.commerce.repository.OrderRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID
import java.time.Instant

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {

    fun getOrderById(id: UUID): Order {
        return orderRepository.findById(id).orElseThrow { RuntimeException("Order not found for id $id") }
    }
    fun getOrdersByUserId(userId: UUID): List<Order> {
        return orderRepository.findByUserId(userId)
    }
    fun getOrdersByStatus(status: OrderStatus): List<Order> {
        return orderRepository.findByStatus(status)
    }

    fun createOrder(
        userId: UUID,
        totalPrice: BigDecimal,
    ): Order {
        val order = Order(
            id = UUID.randomUUID(),
            userId = userId,
            status = OrderStatus.PENDING,
            totalPrice = totalPrice,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
        return orderRepository.save(order)
    }

    fun updateOrder(id: UUID, status: OrderStatus): Order {
        val order = getOrderById(id)

        val updatedOrder = order.copy(
            status = status,
            updatedAt = Instant.now(),
        )
        return orderRepository.save(updatedOrder)
    }

    fun cancelOrder(id: UUID): Order {
        val order =  getOrderById(id)

        if(order.status == OrderStatus.DELIVERED) {
            throw RuntimeException("Can't cancel order")
        }

        val cancelledOrder = order.copy(
            status = OrderStatus.CANCELLED,
            updatedAt = Instant.now(),
        )
        return orderRepository.save(cancelledOrder)
    }

    fun deleteOrder(id: UUID){
        val order = getOrderById(id)
        orderRepository.delete(order)
    }

}