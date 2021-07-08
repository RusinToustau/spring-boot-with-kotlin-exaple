package com.example.productexample.product.dao

import com.example.productexample.product.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductDAO: JpaRepository<Product, String>