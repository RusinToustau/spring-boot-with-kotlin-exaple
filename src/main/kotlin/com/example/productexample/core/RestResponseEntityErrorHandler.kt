package com.example.productexample.core

import org.springframework.dao.DuplicateKeyException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.io.FileNotFoundException
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class RestResponseEntityErrorHandler: ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val result: Map<String, List<String?>> = ex.bindingResult.fieldErrors.groupBy({it.field},{it.defaultMessage})
        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(result)
    }

    @ExceptionHandler(DuplicateKeyException::class, EntityNotFoundException::class)
    fun handleJpa(e: Exception): ResponseEntity<ApiResponseDTO> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResponseDTO(
                title = e.javaClass.simpleName,
                message = e.localizedMessage
        ))
    }
}