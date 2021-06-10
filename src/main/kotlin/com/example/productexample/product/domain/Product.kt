package com.example.productexample.product.domain

import javax.validation.constraints.Min
import javax.validation.constraints.Size

data class Product(
        @get:Size(min = 3, max = 20)
        val name: String,
        @get:Min(value = 0)
        val price: Double) {

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
