package guru.springframework.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@ComponentScan("guru.springframework")
@Configuration
@EnableAutoConfiguration
open class JpaIntegrationConfig
