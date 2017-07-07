package guru.springframework.domain

import java.math.BigDecimal
import javax.persistence.Entity

@Entity
class Product(
        var description: String? = null,
        var price: BigDecimal? = null,
        var imageUrl: String? = null

) : AbstractTimeStampEntity()
