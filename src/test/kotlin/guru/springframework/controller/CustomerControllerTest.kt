package guru.springframework.controller

import com.nhaarman.mockito_kotlin.*
import guru.springframework.domain.Address
import guru.springframework.domain.Customer
import guru.springframework.service.CustomerService
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class CustomerControllerTest {

    @Mock
    private lateinit var customerService: CustomerService

    @InjectMocks
    private lateinit var customerController: CustomerController

    private lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        initMocks(this)
        mockMvc = standaloneSetup(customerController).build()
    }

    @Test
    fun `should list all customers when a web request to list all customers is received`() {
        val customerList = listOf(Customer(1), Customer(2))
        whenever(customerService.listAll()).thenReturn(customerList)

        mockMvc.perform(get("/customer/list"))
                .andExpect(status().isOk)
                .andExpect(view().name("customer/list"))
                .andExpect(model().attribute("users", `is`(customerList)))
    }

    @Test
    fun `should view a customer when a web request for a specific customer is received`() {
        val customerId = 1
        val customer = Customer(customerId)
        whenever(customerService.getById(customerId)).thenReturn(customer)

        mockMvc.perform(get("/customer/view/$customerId"))
                .andExpect(status().isOk)
                .andExpect(view().name("customer/view"))
                .andExpect(model().attribute("customer", `is`(customer)))
    }

    @Test
    fun `should navigate to the customer form when adding a new customer`() {
        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk)
                .andExpect(view().name("customer/form"))
                .andExpect(model().attribute("customer", `is`(Customer())))

        verifyZeroInteractions(customerService)
    }

    @Test
    fun `should navigate to the customer form when editing an existing customer`() {
        val customerId = 1
        val customer = Customer(customerId)
        whenever(customerService.getById(customerId)).thenReturn(customer)

        mockMvc.perform(get("/customer/edit/$customerId"))
                .andExpect(status().isOk)
                .andExpect(view().name("customer/form"))
                .andExpect(model().attribute("customer", `is`(customer)))
    }

    @Test
    fun `should redirect to the customer list when deleting an existing customer`() {
        val customerId = 1

        mockMvc.perform(get("/customer/delete/$customerId"))
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/customer/list"))

        verify(customerService, times(1)).delete(customerId)
    }

    @Test
    fun `should redirect to the customer view when saving a product`() {
        val customerId = 1
        val customer = Customer(customerId, "John", "Smith", "email", "phone",
                Address("billingAddress", null, "city", "state", "zip"))
        whenever(customerService.saveOrUpdate(customer)).thenReturn(customer)

        mockMvc.perform(post("/customer")
                .param("id", customerId.toString())
                .param("firstName", customer.firstName)
                .param("lastName", customer.lastName)
                .param("email", customer.email)
                .param("phoneNumber", customer.phoneNumber))
//                .param("addressLine1", customer.addressLine1)
//                .param("addressLine2", customer.addressLine2)
//                .param("city", customer.city)
//                .param("state", customer.state)
//                .param("zipCode", customer.zipCode))
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/customer/view/$customerId"))
                .andExpect(model().attribute("customer", `is`(customer)))

        val boundCustomer = argumentCaptor<Customer>()
        verify(customerService, times(1)).saveOrUpdate(boundCustomer.capture())
        assertThat(boundCustomer.firstValue, `is`(customer))
    }
}