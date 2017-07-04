package guru.springframework.security


interface EncryptionService {

    fun encryptPassword(plainText: String): String

    fun checkPassword(plainText: String, encryptedPassword: String): Boolean
}