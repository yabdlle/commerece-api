package com.mod.commerce.service

import com.mod.commerce.domain.model.Product
import com.mod.commerce.repository.ProductRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID
import java.time.Instant


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

    fun createProduct(
        name: String,
        price: BigDecimal,
        stockQuantity: Int,
        description: String?,
        category: String,
    ): Product {
        if(productRepository.existsByName(name)){
            throw RuntimeException("Product already exists")
        }
        val product = Product(
            id = UUID.randomUUID(),
            name = name,
            price = price,
            description = description,
            category = category,
            stockQuantity = stockQuantity,
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        )
        return productRepository.save(product)
    }

    fun updateProduct(
        id: UUID,
        name: String,
        price: BigDecimal,
        stockQuantity: Int,
        description: String?,
        category: String,
    ): Product {
        val product = getProductById(id)

        if(name != product.name && productRepository.existsByName(name)){
            throw RuntimeException("Product Not Found")
        }
        val updatedProduct = product.copy(
            name = name,
            price = price,
            stockQuantity = stockQuantity,
            description = description,
            category = category,
            updatedAt = Instant.now(),
        )

        return productRepository.save(updatedProduct)
    }

    fun deleteProductById(id: UUID) {
        val product = getProductById(id)
        productRepository.delete(product)
    }
}
