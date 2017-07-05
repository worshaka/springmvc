package guru.springframework.domain

import javax.persistence.CascadeType
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.OneToOne

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

    constructor(
            id: Int,
            firstName: String? = null,
            lastName: String? = null,
            email: String? = null,
            phoneNumber: String? = null,
            billingAddress: Address? = null,
            shippingAddress: Address? = null,
            user: User? = null

    ) : this(firstName, lastName, email, phoneNumber, billingAddress, shippingAddress, user) {

        this.id = id
    }
}
