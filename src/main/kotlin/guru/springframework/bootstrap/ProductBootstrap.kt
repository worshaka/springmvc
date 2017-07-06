package guru.springframework.bootstrap

import guru.springframework.domain.Product
import guru.springframework.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
open class ProductBootstrap constructor(private val productService: ProductService) : AbstractBootstrap() {

    val products = listOf(
            Product("Product 1", BigDecimal("12.99"), "http://example.com/product1"),
            Product("Product 2", BigDecimal("14.99"), "http://example.com/product2"),
            Product("Product 3", BigDecimal("34.99"), "http://example.com/product3"),
            Product("Product 4", BigDecimal("44.99"), "http://example.com/product4"),
            Product("Product 5", BigDecimal("25.99"), "http://example.com/product5")
    )

    override fun loadData() = products.forEach { productService.saveOrUpdate(it) }
}
