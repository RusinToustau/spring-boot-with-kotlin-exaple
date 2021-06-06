package com.example.productexample.product.service

import com.example.productexample.core.BaseCrud
import com.example.productexample.product.domain.Product
import com.example.productexample.product.extension.update
import org.springframework.stereotype.Service

@Service
class ProductService: BaseCrud<Product, String> {
    private val products = mutableSetOf(
            Product("Manzana", 14.2),
            Product("Banana", 15.5)
    )

    override fun findAll(): List<Product> = products.toList()

    override fun findById(id: String): Product? = products.find { it.name == id }

    override fun save(t: Product): Boolean = products.add(t)

    override fun update(t: Product): Boolean = products.update(t)

    override fun deleteById(id: String): Boolean = products.remove(findById(id))

}