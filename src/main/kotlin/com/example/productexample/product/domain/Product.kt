package com.example.productexample.product.domain

data class Product(val name: String, val price: Double) {

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
