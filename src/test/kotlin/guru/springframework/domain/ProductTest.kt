package guru.springframework.domain

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.math.BigDecimal

class ProductTest {

    private fun createProduct(id: Int) = Product().apply { this.id = id }

    @Test
    fun `should be equal when compared to an object of the same type and same id`() {
        val product1 = createProduct(1)
        val product2 = Product("description", BigDecimal.ONE, "url").apply { id = 1 }

        assertThat(product1 == product2, `is`(true))
    }

    @Test
    fun `should not be equal when compared to an object of the same type and different id`() {
        val product1 = createProduct(1)
        val product2 = createProduct(2)

        assertThat(product1 == product2, `is`(false))
    }

    @Test
    fun `should not be equal when compared to an object of a different type and same id`() {
        val product = createProduct(1)
        val customer = Customer().apply { id = 1 }

        assertThat(product == customer as AbstractEntity, `is`(false))
    }
}
