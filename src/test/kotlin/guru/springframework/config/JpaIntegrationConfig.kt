package guru.springframework.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import javax.validation.Validation
import javax.validation.Validator

@ComponentScan("guru.springframework")
@Configuration
@EnableAutoConfiguration
open class JpaIntegrationConfig {

    @Bean
    open fun validator(): Validator = Validation.buildDefaultValidatorFactory().validator
}

