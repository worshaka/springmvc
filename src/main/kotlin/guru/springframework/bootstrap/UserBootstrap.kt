package guru.springframework.bootstrap

import guru.springframework.domain.Address
import guru.springframework.domain.Customer
import guru.springframework.domain.User
import guru.springframework.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class UserBootstrap @Autowired constructor(private val userService: UserService) : AbstractBootstrap() {

    val users = listOf(
            User(
                    "jones",
                    "password",
                    Customer(
                            "Travis",
                            "Jones",
                            "travis.jones@internode.on.net",
                            "0416007064",
                            Address("Unit 117", "155 Missenden Road", "Newtown", "NSW", "2042"),
                            Address("Unit 117", "155 Missenden Road", "Newtown", "NSW", "2042")
                    )
            ),
            User(
                    "cbutler",
                    "password",
                    Customer(
                            "Calli",
                            "Butler",
                            "callisarah13@gmail.com",
                            "0423700789",
                            Address("U117/155 Missenden Road", null, "Newtown", "NSW", "2042"),
                            null
                    )
            )
    )

    override fun loadData() = users.forEach { userService.saveOrUpdate(it) }
}