package guru.springframework.service.jpa

import guru.springframework.domain.Product
import guru.springframework.service.ProductService
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

@Service
@Profile("jpadao")
class ProductServiceJpaDaoImpl constructor(
        @field:PersistenceUnit
        private val emf: EntityManagerFactory) : ProductService {

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
        val em = emf.createEntityManager()
        em.transaction.begin()
        val savedProduct = em.merge(entity)
        em.transaction.commit()
        return savedProduct
    }
}