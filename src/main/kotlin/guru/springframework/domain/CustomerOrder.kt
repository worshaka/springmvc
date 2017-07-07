package guru.springframework.domain

import java.sql.Date
import javax.persistence.*

@Entity
class CustomerOrder(

        @field:ManyToOne
        val customer: Customer,

        @field:Embedded
        val shippingAddress: Address

) : AbstractTimeStampEntity() {

    init {
        customer.addOrder(this)
    }

    @OneToMany(cascade = arrayOf(CascadeType.ALL), mappedBy = "order", orphanRemoval = true)
    private val orderLines: MutableList<OrderLine> = ArrayList()

    var dateShipped: Date? = null

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.NEW

    fun orderLines(): List<OrderLine> = orderLines

    fun addOrderLine(orderLine: OrderLine) {
        orderLine.order = this
        orderLines.add(orderLine)
    }
}
