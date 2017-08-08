package guru.springframework.service.jpa

import guru.springframework.domain.Product
import guru.springframework.service.ProductService
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit
import javax.validation.Validator

@Service
@Profile("jpadao")
class ProductServiceJpaDaoImpl(

        @field:PersistenceUnit
        private val emf: EntityManagerFactory,
        private val validator: Validator

) : ProductService {

    override fun listAll(): List<Product> = emf.createEntityManager().createQuery("from Product",
            Product::class.java).resultList

    override fun getById(id: Int): Product = emf.createEntityManager().find(Product::class.java, id)

    override fun delete(id: Int): Product? {
        val em = emf.createEntityManager()
        em.transaction.begin()
        val product = em.find(Product::class.java, id)
        em.remove(product)
        em.transaction.commit()
        return product
    }

    override fun saveOrUpdate(entity: Product): Product {
        val validationErrors = validator.validate(entity)
        if (!validationErrors.isEmpty()) {
            val validationErrorMessages = validationErrors.joinToString { it.message }
            throw IllegalStateException("Product contains validation errors: $validationErrorMessages")
        }
        val em = emf.createEntityManager()
        em.transaction.begin()
        val savedProduct = em.merge(entity)
        em.transaction.commit()
        return savedProduct
    }
}
