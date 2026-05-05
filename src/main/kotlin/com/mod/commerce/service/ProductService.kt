package com.mod.commerce.service

import com.mod.commerce.domain.model.Product
import com.mod.commerce.repository.ProductRepository
import jakarta.persistence.Id
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID


@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun getProductById(id: UUID): Product {
        return productRepository.findById(id).orElseThrow{ RuntimeException("Product Not Found") }
    }

    fun getProductsByCategory(category: String): List<Product> {
        return productRepository.findByCategory(category)
    }

    fun getInStockProducts(): List<Product>{
        return productRepository.findByStockQuantityGreaterThan(0)
    }

    fun deleteProductById(id: UUID) {
        val product = getProductById(id)
        productRepository.delete(product)
    }
}
