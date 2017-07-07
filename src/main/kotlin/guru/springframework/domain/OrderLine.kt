package guru.springframework.domain

import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class OrderLine(

        @field:OneToOne
        val product: Product,

        val quantity: Int

) : AbstractEntity() {

    @ManyToOne
    var order: CustomerOrder? = null
}
