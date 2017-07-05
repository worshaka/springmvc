package guru.springframework.service.jpa

import guru.springframework.config.JpaIntegrationConfig
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD
import org.springframework.test.context.ActiveProfiles
import java.lang.annotation.Inherited

@ActiveProfiles("jpadao")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@Inherited
@Retention(AnnotationRetention.RUNTIME)
@SpringBootTest(classes = arrayOf(JpaIntegrationConfig::class))
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class JpaIntegrationTest
