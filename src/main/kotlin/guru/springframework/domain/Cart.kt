package guru.springframework.domain

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Cart : AbstractTimeStampEntity() {

    @OneToMany(cascade = arrayOf(CascadeType.ALL), mappedBy = "cart", orphanRemoval = true)
    private val cartDetails: MutableList<CartDetail> = ArrayList()

    fun cartDetails(): List<CartDetail> = cartDetails

    fun addCartDetail(cartDetail: CartDetail) {
        cartDetail.cart = this
        cartDetails.add(cartDetail)
    }

    fun removeCartDetail(cartDetail: CartDetail) = cartDetails.remove(cartDetail)
}
