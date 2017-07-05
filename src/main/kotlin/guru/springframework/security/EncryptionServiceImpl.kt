package guru.springframework.security

import org.jasypt.util.password.PasswordEncryptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EncryptionServiceImpl @Autowired constructor(private val passwordEncyptor: PasswordEncryptor) : EncryptionService {

    override fun encryptPassword(plainText: String): String = passwordEncyptor.encryptPassword(plainText)

    override fun checkPassword(plainText: String, encryptedPassword: String): Boolean =
            passwordEncyptor.checkPassword(plainText, encryptedPassword)
}
