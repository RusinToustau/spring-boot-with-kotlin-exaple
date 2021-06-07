package com.example.productexample

import com.example.productexample.product.domain.Product
import com.example.productexample.product.service.ProductService
import com.example.productexample.test_uitils.bodyTo
import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
class ProductExampleApplicationTests {

	@Autowired
	private lateinit var webApplicationContext: WebApplicationContext

	private val mockMvc: MockMvc by lazy {
		MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
				.build()
	}

	@Autowired
	private lateinit var mapper: ObjectMapper
	@Autowired
	private lateinit var productService: ProductService

	@Test
	fun findAll() {
		val products : List<Product> = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product"))
				.andExpect(status().isOk)
				.bodyTo(mapper)
		assertThat(products, Matchers.`is`(equalTo(productService.findAll())))
	}

	@Test
	fun findById() {
		val productsFromService = productService.findAll()
		assert(productsFromService.isNotEmpty()){ "Should not be empty" }
		val expectedProduct = productsFromService.first()

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/${expectedProduct.name}"))
				.andExpect(status().isOk)
				.andExpect(jsonPath("$.name",Matchers.`is`(equalTo(expectedProduct.name))))
	}

	@Test
	fun findByIdEmpty() {

	}
}
