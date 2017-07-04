package guru.springframework

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class SpringmvcApplication {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(SpringmvcApplication::class.java, *args)
        }
    }
}
