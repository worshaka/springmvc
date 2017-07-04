package guru.springframework.service.security

import com.nhaarman.mockito_kotlin.whenever
import guru.springframework.security.EncryptionServiceImpl
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.jasypt.util.password.PasswordEncryptor
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class EncryptionServiceImplTest {

    @InjectMocks
    lateinit var encryptionService: EncryptionServiceImpl

    @Mock
    lateinit var passwordEncryptor: PasswordEncryptor

    @Before
    fun setup() {
        initMocks(this)
    }

    @Test
    fun `should encrypt plain text password`() {
        val plainTextPassword = "plainText"
        val expectedEncryptedPassword = "encryptedPassword"
        whenever(passwordEncryptor.encryptPassword(plainTextPassword)).thenReturn(expectedEncryptedPassword)

        val actualEncryptedPassword = encryptionService.encryptPassword(plainTextPassword)

        assertThat(actualEncryptedPassword, `is`(expectedEncryptedPassword))
    }

    @Test
    fun `should check plain text password with encrypted password`() {
        val plainText = "plainText"
        val encrypted = "encrypted"
        val expectedResult = true
        whenever(passwordEncryptor.checkPassword(plainText, encrypted)).thenReturn(expectedResult)

        val actualResult = encryptionService.checkPassword(plainText, encrypted)
        assertThat(actualResult, `is`(expectedResult))
    }
}