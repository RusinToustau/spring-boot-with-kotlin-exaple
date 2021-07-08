package com.example.productexample.product.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.Min
import javax.validation.constraints.Size

@Entity
data class Product(
        @Id
        @get:Size(min = 3, max = 20)
        var name: String,
        @get:Min(value = 0)
        var price: Double,
        @get:Min(value = 0)
        var stock:Int = 0
){

    override fun equals(other: Any?): Boolean {
        other?.let {
            return when(it) {
                it === this -> true
                this.javaClass != it.javaClass -> false
                else -> this.name == (it as Product).name
            }
        }?:run { return false }
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}
