package guru.springframework.service.jpa

import guru.springframework.AbstractIntegrationTest
import guru.springframework.service.ProductService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import javax.annotation.Resource

@JpaIntegrationTest
class ProductServiceJpaDaoIT : AbstractIntegrationTest() {

    @Resource
    private lateinit var productService: ProductService

    @Test
    fun `should list all products`() {
        val products = productService.listAll()

        assertThat(products, hasSize(5))
    }

    @Test
    fun `should return a product by id`() {
        val productId = 1
        val product = productService.getById(productId)

        assertThat(product.id, `is`(1))
    }

    @Test
    fun `should delete a product`() {
        val productId = 1
        val numOfProducts = productService.listAll().size

        val deletedProduct = productService.delete(productId)

        assertAll(
                Executable { assertThat(deletedProduct!!.id, `is`(productId)) },
                Executable { assertThat(productService.listAll(), hasSize(numOfProducts - 1)) }
        )
    }

    @DisplayName("when saving or updating a product")
    @Nested
    inner class whenSavingOrUpdatingProduct {

        @Test
        fun `should save a valid update for a product`() {
            val productId = 1
            val product = productService.getById(productId)
            val newDescription = "new description"

            product.description = newDescription
            productService.saveOrUpdate(product)

            val savedProduct = productService.getById(productId)
            assertThat(savedProduct.description, `is`(newDescription))
        }

        @Test
        fun `should not save if the product fails validation`() {
            val productId = 1
            val product = productService.getById(productId)

            product.description = null
            val exception = assertThrows(IllegalStateException::class.java, { productService.saveOrUpdate(product) })
            println(exception.message)
            assertThat(exception.message, containsString("Description cannot be null"))
        }
    }
}
