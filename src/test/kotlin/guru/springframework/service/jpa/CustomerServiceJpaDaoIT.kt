package guru.springframework.service.jpa

import guru.springframework.AbstractIntegrationTest
import guru.springframework.domain.Address
import guru.springframework.domain.Customer
import guru.springframework.service.CustomerService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import javax.annotation.Resource

@JpaIntegrationTest
class CustomerServiceJpaDaoIT : AbstractIntegrationTest() {

    @Resource
    private lateinit var customerService: CustomerService

    @Test
    fun `should list all customers`() {
        val customers = customerService.listAll()

        assertThat(customers, hasSize(2))
    }

    @Test
    fun `should find customer by id`() {
        val customerId = 1

        val customer = customerService.getById(customerId)

        assertThat(customer.id, `is`(customerId))
    }

    @Test
    fun `should delete customer`() {
        val customerId = 1
        val numOfCustomers = customerService.listAll().size

        val deletedCustomer = customerService.delete(customerId)

        assertThat(deletedCustomer!!.id, `is`(customerId))
        assertThat(customerService.listAll(), hasSize(numOfCustomers - 1))
    }

    @Test
    fun `should save customer update`() {
        val customerId = 1
        val customer = customerService.getById(customerId)
        val newFirstName = "Name"

        customer.firstName = newFirstName
        customerService.saveOrUpdate(customer)

        val updatedCustomer = customerService.getById(customerId)
        assertThat(updatedCustomer.firstName, `is`(newFirstName))
    }

    @Test
    fun `should persist customer with billing address`() {
        val expectedAddress = Address("billingAddress line 1", null, "city", "state", "zip")

        val savedCustomer = customerService.saveOrUpdate(Customer(billingAddress = expectedAddress))

        assertThat(savedCustomer.billingAddress, `is`(expectedAddress))
    }

    @Test
    fun `should persist customer with shipping address`() {
        val expectedAddress = Address("billingAddress line 1", null, "city", "state", "zip")

        val savedCustomer = customerService.saveOrUpdate(Customer(shippingAddress = expectedAddress))

        assertThat(savedCustomer.shippingAddress, `is`(expectedAddress))
    }
}
