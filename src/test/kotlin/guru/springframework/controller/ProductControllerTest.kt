package guru.springframework.controller

import com.nhaarman.mockito_kotlin.*
import guru.springframework.domain.Product
import guru.springframework.service.ProductService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
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
import java.math.BigDecimal.TEN

class ProductControllerTest {

    @Mock
    private lateinit var productService: ProductService

    @InjectMocks
    private lateinit var productController: ProductController

    private lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        initMocks(this)
        mockMvc = standaloneSetup(productController).build()
    }

    @Test
    fun `should list all products when a web request to list all products is received`() {
        val expectedProducts = listOf(Product(1), Product(2))
        whenever(productService.listAll()).thenReturn(expectedProducts)

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk)
                .andExpect(view().name("product/list"))
                .andExpect(model().attribute("products", `is`(expectedProducts)))
    }

    @Test
    fun `should view a product when a web request for a specific product is received`() {
        val productId = 1
        val expectedProduct = Product(productId, "description", TEN, "imageURL")
        whenever(productService.getById(productId)).thenReturn(expectedProduct)

        mockMvc.perform(get("/product/view/$productId"))
                .andExpect(status().isOk)
                .andExpect(view().name("product/view"))
                .andExpect(model().attribute("product", `is`(expectedProduct)))
    }

    @Test
    fun `should navigate to the product form when adding a new product`() {
        verifyZeroInteractions(productService)

        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk)
                .andExpect(view().name("product/form"))
                .andExpect(model().attribute("product", `is`(Product())))
    }

    @Test
    fun `should navigate to the product form when editing an existing product`() {
        val productId = 1
        val product = Product(productId)
        whenever(productService.getById(productId)).thenReturn(product)

        mockMvc.perform(get("/product/edit/$productId"))
                .andExpect(status().isOk)
                .andExpect(view().name("product/form"))
                .andExpect(model().attribute("product", `is`(product)))
    }

    @Test
    fun `should redirect to the product list page when deleting an existing product`() {
        val productId = 1

        mockMvc.perform(get("/product/delete/$productId"))
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/product/list"))

        verify(productService, times(1)).delete(productId)
    }

    @Test
    fun `should redirect to the product view when saving a product`() {
        val productId = 1
        val product = Product(productId, "description", TEN, "imageURL")
        whenever(productService.saveOrUpdate(product)).thenReturn(product)

        mockMvc.perform(post("/product")
                .param("id", productId.toString())
                .param("description", product.description)
                .param("price", product.price.toString())
                .param("imageUrl", product.imageUrl))
                .andExpect(status().is3xxRedirection)
                .andExpect(view().name("redirect:/product/view/$productId"))
                .andExpect(model().attribute("product", `is`(product)))

        val boundProduct = argumentCaptor<Product>()
        verify(productService, times(1)).saveOrUpdate(boundProduct.capture())
        assertThat(boundProduct.firstValue, `is`(product))
    }
}