package guru.springframework.config

import org.jasypt.util.password.PasswordEncryptor
import org.jasypt.util.password.StrongPasswordEncryptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class CommonBeanConfig {

    @Bean
    open fun strongPasswordEncryptor(): PasswordEncryptor {
        return StrongPasswordEncryptor()
    }
}
