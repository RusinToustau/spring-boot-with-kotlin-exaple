package com.example.productexample

import com.example.productexample.product.domain.Product
import com.example.productexample.product.service.ProductService
import com.example.productexample.test_uitils.bodyData
import com.example.productexample.test_uitils.bodyTo
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

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
		val products : List<Product> = mockMvc.perform(MockMvcRequestBuilders.get(URL))
				.andExpect(status().isOk)
				.bodyTo(mapper)

		assertThat(products, Matchers.`is`(equalTo(productService.findAll())))
	}

	@Test
	fun findById() = mockMvc
			.perform(MockMvcRequestBuilders.get("$URL/${firstProduct.name}"))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.name",Matchers.`is`(equalTo(firstProduct.name))))


	@Test
	fun findByIdEmpty() = mockMvc
			.perform(MockMvcRequestBuilders.get("$URL/${UUID.randomUUID()}"))
			.andExpect(status().isNoContent)
			.andExpect(jsonPath("$").doesNotExist())

	@Test
	fun saveSuccessfully() {
		val product = Product(name = "Frutilla", price = 20.2)

		val result: Boolean = mockMvc
				.perform(MockMvcRequestBuilders
						.post(URL)
						.bodyData(product)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk)
				.bodyTo(mapper)

		assert(result)
	}

	@Test
	fun saveFail() {
		val result: Boolean = mockMvc
				.perform(MockMvcRequestBuilders
						.post(URL)
						.bodyData(firstProduct)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict)
				.bodyTo(mapper)

		assert(!result) { "Should be false" }
	}

	@Test
	fun updateSuccessfully() {
		val product = firstProduct.copy(price = 11.5)

		val result: Boolean = mockMvc
				.perform(MockMvcRequestBuilders
						.put(URL)
						.bodyData(product)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk)
				.bodyTo(mapper)

		assert(result)
	}

	@Test
	fun updateFail() {
		val product = Product(name = "Frutilla", price = 20.2)

		val result: Boolean = mockMvc
				.perform(MockMvcRequestBuilders
						.put(URL)
						.bodyData(product)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotModified)
				.bodyTo(mapper)

		assert(!result) { "Should be false" }
	}

	@Test
	fun deleteByIdSuccessfully() {
		val product = firstProduct

		val result: Boolean = mockMvc
				.perform(MockMvcRequestBuilders
						.delete("$URL/${product.name}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk)
				.bodyTo(mapper)

		assert(result)
	}

	@Test
	fun deleteByIdFail() {
		val product = Product(name = "Frutilla", price = 20.2)

		val result: Boolean = mockMvc
				.perform(MockMvcRequestBuilders
						.delete("$URL/${product.name}")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound)
				.bodyTo(mapper)

		assert(!result) { "Should be false" }
	}

	private val firstProduct : Product by lazy {
		val productsFromService = productService.findAll()
		assert(productsFromService.isNotEmpty()){ "Should not be empty" }
		productsFromService.first()
	}

	companion object {
		private const val URL = "/api/v1/product"
	}
}
