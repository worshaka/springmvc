package guru.springframework.service.stub

import guru.springframework.domain.User
import guru.springframework.security.EncryptionService
import guru.springframework.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("stub")
class UserServiceStubImpl @Autowired constructor(private val encryptionService: EncryptionService)
    : AbstractServiceStub<User>(), UserService {

    override fun saveOrUpdate(entity: User): User {
        entity.password?.let {
            entity.encryptedPassword = encryptionService.encryptPassword(it)
        }
        return super.saveOrUpdate(entity)
    }
}
