package guru.springframework.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractAuditableEntity(

        @field:CreationTimestamp
        val dateCreated: LocalDateTime,

        @field:UpdateTimestamp
        val dateUpdated: LocalDateTime

) : AbstractEntity()
