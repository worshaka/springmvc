package guru.springframework.service.jpa

import guru.springframework.domain.Customer
import guru.springframework.service.CustomerService
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

@Service
@Profile("jpadao")
class CustomerServiceJpaDaoImpl constructor(@field:PersistenceUnit private val emf: EntityManagerFactory) : CustomerService {

    override fun listAll(): List<Customer> = emf.createEntityManager().createQuery("from Customer",
            Customer::class.java).resultList

    override fun getById(id: Int): Customer = emf.createEntityManager().find(Customer::class.java, id)

    override fun delete(id: Int): Customer? {
        val em = emf.createEntityManager()
        em.transaction.begin()
        val customer = em.find(Customer::class.java, id)
        em.remove(customer)
        em.transaction.commit()
        return customer
    }

    override fun saveOrUpdate(entity: Customer): Customer {
        val em = emf.createEntityManager()
        em.transaction.begin()
        val savedCustomer = em.merge(entity)
        em.transaction.commit()
        return savedCustomer
    }
}
