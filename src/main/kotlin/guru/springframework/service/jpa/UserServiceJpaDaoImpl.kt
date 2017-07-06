package guru.springframework.service.jpa

import guru.springframework.domain.User
import guru.springframework.security.EncryptionService
import guru.springframework.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

@Service
@Profile("jpadao")
class UserServiceJpaDaoImpl constructor(

        private val encryptionService: EncryptionService,

        @field:PersistenceUnit
        private val emf: EntityManagerFactory

) : UserService {

    override fun listAll(): List<User> = emf.createEntityManager().createQuery("from User",
            User::class.java).resultList.filter { it.enabled }

    override fun getById(id: Int): User = emf.createEntityManager().find(User::class.java, id)

    override fun delete(id: Int): User? {
        val em = emf.createEntityManager()
        em.transaction.begin()
        val user = em.find(User::class.java, id)
        user.enabled = false
        em.merge(user)
        em.transaction.commit()
        return user
    }

    override fun saveOrUpdate(entity: User): User {
        entity.password?.let {
            entity.encryptedPassword = encryptionService.encryptPassword(it)
        }

        val em = emf.createEntityManager()
        em.transaction.begin()
        val savedUser = em.merge(entity)
        em.transaction.commit()
        return savedUser
    }
}
