package com.example.productexample.product.service

import com.example.productexample.core.BaseCrud
import com.example.productexample.product.dao.ProductDAO
import com.example.productexample.product.domain.Product
import org.springframework.dao.DuplicateKeyException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ProductService(private val dao: ProductDAO): BaseCrud<Product, String> {

    override fun findAll(): List<Product> = dao.findAll()

    override fun findById(id: String): Product? = dao.findByIdOrNull(id)

    override fun save(t: Product): Product =
            if(!dao.existsById(t.name)) dao.save(t) else throw DuplicateKeyException("${t.name} does exist")

    override fun update(t: Product): Product =
            if(dao.existsById(t.name)) dao.save(t) else throw EntityNotFoundException("${t.name} does not exist")

    override fun deleteById(id: String): Product {
        return findById(id)?.apply {
            dao.delete(this)
        } ?: throw EntityNotFoundException("$id does not exist")
    }

}