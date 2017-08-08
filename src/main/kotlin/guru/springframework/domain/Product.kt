package guru.springframework.domain

import java.math.BigDecimal
import javax.persistence.Entity
import javax.validation.constraints.NotNull

@Entity
class Product(

        @field:NotNull(message = "Description cannot be null")
        var description: String? = null,
        var price: BigDecimal? = null,
        var imageUrl: String? = null

) : AbstractTimeStampEntity()
