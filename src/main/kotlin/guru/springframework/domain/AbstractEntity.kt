package guru.springframework.domain

import javax.persistence.*

@MappedSuperclass
abstract class AbstractEntity(

        @field:[Id GeneratedValue(strategy = GenerationType.AUTO)]
        var id: Int? = null,

        @field:Version
        var version: Int = 0

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != this.javaClass) return false

        val entity = other as AbstractEntity
        return entity.id == this.id
    }

    override fun hashCode(): Int = this.id ?: -1
}