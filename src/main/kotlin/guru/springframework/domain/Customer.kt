package guru.springframework.domain

import javax.persistence.*

@Entity
class Customer(
        var firstName: String? = null,
        var lastName: String? = null,
        var email: String? = null,
        var phoneNumber: String? = null,

        @field:Embedded
        var billingAddress: Address? = null,

        @field:Embedded
        var shippingAddress: Address? = null,

        @field:OneToOne(cascade = arrayOf(CascadeType.REMOVE))
        var user: User? = null

) : AbstractEntity() {

    @OneToMany
    private val orders: MutableList<CustomerOrder> = ArrayList()

    fun orders(): List<CustomerOrder> = orders

    fun addOrder(order: CustomerOrder) = orders.add(order)
}
