package com.example.productexample.product.extension

fun <T>MutableSet<T>.update(element: T): Boolean {
    return this.remove(element) && this.add(element)
}