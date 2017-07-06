package guru.springframework.service.jpa

import guru.springframework.AbstractIntegrationTest
import guru.springframework.domain.Cart
import guru.springframework.domain.CartDetail
import guru.springframework.domain.Customer
import guru.springframework.domain.User
import guru.springframework.service.ProductService
import guru.springframework.service.UserService
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import javax.annotation.Resource

@JpaIntegrationTest
class UserServiceJpaDaoIT : AbstractIntegrationTest() {

    @Resource
    private lateinit var userService: UserService

    @Resource
    private lateinit var productService: ProductService

    @Test
    fun `should list all users`() {
        val users = userService.listAll()

        assertThat(users, hasSize(2))
    }

    @Test
    fun `should find user by id`() {
        val userId = 1

        val user = userService.getById(userId)

        assertThat(user.id, `is`(userId))
    }

    @Test
    fun `should delete user`() {
        val numOfUsers = userService.listAll().size

        val deletedUser = userService.delete(1)

        assertThat(deletedUser!!.enabled, `is`(false))
        assertThat(userService.listAll(), hasSize(numOfUsers - 1))
    }

    @Test
    fun `should save user update`() {
        val userId = 1
        val user = userService.getById(userId)
        val newUsername = "username"

        user.username = newUsername
        userService.saveOrUpdate(user)

        val updatedUser = userService.getById(userId)
        assertThat(updatedUser.username, `is`(newUsername))
    }

    @Test
    fun `should save encrypted password`() {
        val plainTextPassword = "password"

        val user = userService.saveOrUpdate(User("username", plainTextPassword))

        assertThat(user.encryptedPassword, `is`(not(nullValue())))
    }

    @Test
    fun `should persist user with customer`() {
        val expectedCustomer = Customer("Travis", "Jones")
        val user = User("tjones", "password", customer = expectedCustomer)

        val savedUser = userService.saveOrUpdate(user)
        val savedCustomer = savedUser.customer

        assertThat(savedCustomer!!.user, `is`(savedUser))
        assertThat(savedCustomer.id, `is`(not(nullValue())))
    }


    @Test
    fun `should save user with cart`() {
        val user = userService.saveOrUpdate(User("username", "password", cart = Cart()))

        assertThat(user.cart, `is`(not(nullValue())))
    }

    @Test fun `should save user with cart details`() {
        val expectedProduct = productService.getById(1)
        val expectedQuantity = 2
        val cart = Cart()
        cart.addCartDetail(CartDetail(cart, expectedProduct, 2))

        val user = userService.saveOrUpdate(User("username", "password", cart = cart))

        val savedCartDetails = user.cart!!.cartDetails()[0]
        assertThat(savedCartDetails.product, `is`(expectedProduct))
        assertThat(savedCartDetails.quantity, `is`(expectedQuantity))
    }
}
