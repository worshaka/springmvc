package guru.springframework.service.stub

import guru.springframework.domain.AbstractEntity
import guru.springframework.service.CrudService
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

abstract class AbstractServiceStub<T : AbstractEntity> : CrudService<T> {

    private val idPool = AtomicInteger(1)

    protected val domainObjects = HashMap<Int, T>()

    private fun nextEntityId() = idPool.getAndIncrement()

    override fun saveOrUpdate(entity: T): T {
        if (entity.id == null) {
            entity.id = nextEntityId()
        }

        val id = entity.id!!
        domainObjects.put(id, entity)
        return entity
    }

    override fun listAll() = domainObjects.values.toList()

    override fun getById(id: Int) = domainObjects[id] ?: throw IllegalArgumentException()

    override fun delete(id: Int) = domainObjects.remove(id)
}