package com.example.productexample.product.service

import com.example.productexample.core.BaseCrud
import com.example.productexample.product.dao.ProductDAO
import com.example.productexample.product.domain.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService(private val dao: ProductDAO): BaseCrud<Product, String> {

    override fun findAll(): List<Product> = dao.findAll()

    override fun findById(id: String): Product? = dao.findByIdOrNull(id)

    override fun save(t: Product): Boolean = dao.save(t).let { return true }

    override fun update(t: Product): Boolean = dao.save(t).let { return true }

    override fun deleteById(id: String): Boolean = dao.deleteById(id).let { true }

}