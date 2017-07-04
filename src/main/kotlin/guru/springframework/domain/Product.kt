package guru.springframework.domain

import java.math.BigDecimal
import javax.persistence.Entity

@Entity
class Product(
        var description: String? = null,
        var price: BigDecimal? = null,
        var imageUrl: String? = null

) : AbstractEntity() {

    constructor(id: Int, description: String? = null, price: BigDecimal? = null, imageUrl: String? = null)
            : this(description, price, imageUrl) {
        this.id = id
    }
}

