package guru.springframework.controller

import org.junit.Before
import org.junit.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class IndexControllerTest {

    private val indexController = IndexController()

    private lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        mockMvc = standaloneSetup(indexController).build()
    }

    @Test
    fun `should navigate to the index page when a web request to the root context is received`() {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(view().name("index"))
    }
}
