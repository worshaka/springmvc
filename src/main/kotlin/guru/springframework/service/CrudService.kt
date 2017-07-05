package guru.springframework.service

interface CrudService<T> {

    fun listAll(): List<T>

    fun getById(id: Int): T

    fun delete(id: Int): T?

    fun saveOrUpdate(entity: T): T
}
