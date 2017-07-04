package guru.springframework.domain

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.math.BigDecimal

/**
 * Created by travis on 6/13/17.
 */
class ProductTest {

    @Test
    fun `should be equal when compared to an object of the same type and same id`() {
        val product1 = Product(1)
        val product2 = Product(1, "description", BigDecimal.ONE, "url")

        assertThat(product1 == product2, `is`(true))
    }

    @Test
    fun `should not be equal when compared to an object of the same type and different id`() {
        val product1 = Product(1)
        val product2 = Product(2)

        assertThat(product1 == product2, `is`(false))
    }

    @Test
    fun `should not be equal when compared to an object of a different type and same id`() {
        val product = Product(1)
        val customer = Customer(1)

        assertThat(product == customer as AbstractEntity, `is`(false))
    }
}