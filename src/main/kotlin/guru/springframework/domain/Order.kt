package guru.springframework.domain

import java.sql.Date
import javax.persistence.Embedded
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne

class Order(

        @field:ManyToOne
        val customer: Customer,

        @field:Embedded
        val shippingAddress: Address

) : AbstractTimeStampEntity() {

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.NEW

    var dateShipped: Date? = null
}

enum class OrderStatus {
    NEW, ALLOCATED, SHIPPED
}