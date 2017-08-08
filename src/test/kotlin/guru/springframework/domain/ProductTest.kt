package guru.springframework.domain

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal

private fun createProduct(id: Int) = Product().apply { this.id = id }

class ProductTest {


    @DisplayName("when compared for equality")
    @Nested
    class WhenComparedForEquality {
        @Test fun `should return true when compared to an object of the same type and same id`() {
            val product1 = createProduct(1)
            val product2 = Product("description", BigDecimal.ONE, "url").apply { id = 1 }

            assertTrue(product1 == product2)
        }

        @Test fun `should return false when compared to an object of the same type and different id`() {
            val product1 = createProduct(1)
            val product2 = createProduct(2)

            assertFalse(product1 == product2)
        }

        @Test fun `should return false when compared to an object of a different type and same id`() {
            val product = createProduct(1)
            val customer = Customer().apply { id = 1 }

            assertFalse(product == customer as AbstractEntity)
        }
    }
}
