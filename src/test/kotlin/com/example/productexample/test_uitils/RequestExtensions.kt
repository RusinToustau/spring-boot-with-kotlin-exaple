package com.example.productexample.test_uitils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder

fun MockHttpServletRequestBuilder.bodyData(data: Any, mapper: ObjectMapper = jacksonObjectMapper()): MockHttpServletRequestBuilder {
    return this.content(mapper.writeValueAsBytes(data)).contentType(MediaType.APPLICATION_JSON)
}