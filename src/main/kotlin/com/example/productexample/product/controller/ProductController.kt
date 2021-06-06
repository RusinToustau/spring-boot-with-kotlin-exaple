package com.example.productexample.product.controller

import com.example.productexample.core.BaseController
import com.example.productexample.product.domain.Product
import com.example.productexample.product.service.ProductService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/product")
class ProductController(productService: ProductService) : BaseController<Product,String>(productService)