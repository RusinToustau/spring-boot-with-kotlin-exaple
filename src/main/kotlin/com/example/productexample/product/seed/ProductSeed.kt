package com.example.productexample.product.seed

import com.example.productexample.product.domain.Product
import com.example.productexample.product.service.ProductService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ProductSeed(private val productService: ProductService): ApplicationRunner {

    private val list = listOf<Product>(
            Product("Manzana", 11.1,10),
            Product("Naranja", 15.1,20),
            Product("Pomelo", 30.1,30)
    )

    override fun run(args: ApplicationArguments?) {
        list.forEach {
            println("Saving -> ${it.name}")
            productService.save(it)
        }
    }

}