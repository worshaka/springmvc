package guru.springframework.domain

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class CartDetail(

        @field:ManyToOne
        private val cart: Cart,

        @field:OneToOne val product: Product

) : AbstractEntity()
