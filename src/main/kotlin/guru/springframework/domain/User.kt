package guru.springframework.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class User(
        var username: String,

        @field:Transient
        var password: String? = null,

        customer: Customer? = null,

        var enabled: Boolean = true,

        @field:OneToOne(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        var cart: Cart? = null

) : AbstractEntity() {

    var encryptedPassword: String? = null

    @OneToOne(cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE))
    var customer: Customer? = null
        set(value) {
            value?.user = this
            field = value
        }

    init {
        this.customer = customer
    }
}