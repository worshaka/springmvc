package guru.springframework.domain

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class CartDetail(

        @field:OneToOne
        val product: Product,
        val quantity: Int

) : AbstractTimeStampEntity() {

        @field:ManyToOne
        var cart: Cart? = null
}
