package guru.springframework.service.jpa

import guru.springframework.AbstractIntegrationTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

@JpaIntegrationTest
class ProductServiceJpaDaoIT : AbstractIntegrationTest() {

    @Autowired
    private lateinit var productService: guru.springframework.service.ProductService

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

        assertThat(deletedProduct!!.id, `is`(productId))
        assertThat(productService.listAll(), hasSize(numOfProducts - 1))
    }

    @Test
    fun `should save an update for a product`() {
        val productId = 1
        val product = productService.getById(productId)
        val newDescription = "new description"

        product.description = newDescription
        productService.saveOrUpdate(product)

        val savedProduct = productService.getById(productId)
        assertThat(savedProduct.description, `is`(newDescription))
    }
}
