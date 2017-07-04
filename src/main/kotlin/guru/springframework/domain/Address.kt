package guru.springframework.domain

import javax.persistence.Embeddable

@Embeddable
data class Address(
        val addressLine1: String,
        val addressLine2: String? = null,
        val city: String,
        val state: String,
        val zipCode: String
)