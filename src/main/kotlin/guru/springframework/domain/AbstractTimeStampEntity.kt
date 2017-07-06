package guru.springframework.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractTimeStampEntity(

        @field:CreationTimestamp
        var created: Timestamp = Timestamp(System.currentTimeMillis()),

        @field:UpdateTimestamp
        val modified: Timestamp = Timestamp(System.currentTimeMillis())

) : AbstractEntity()
