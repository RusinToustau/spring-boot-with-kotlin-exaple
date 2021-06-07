package com.example.productexample

import com.example.productexample.product.domain.Product
import com.example.productexample.product.service.ProductService
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
class ProductExampleApplicationTests {

	@Autowired
	private lateinit var mockMvc: MockMvc
	@Autowired
	private lateinit var mapper: ObjectMapper
	@Autowired
	private lateinit var productService: ProductService

	@Test
	fun findAll() {
		val jsonResponse = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product"))
				.andExpect(status().isOk)
				.andReturn().response.contentAsString

		val products: List<Product> = mapper.readValue(
				jsonResponse,
				mapper.typeFactory.constructCollectionType(List::class.java, Product::class.java)
		)

		assertThat(products, Matchers.`is`(equalTo(productService.findAll())))
	}


}
