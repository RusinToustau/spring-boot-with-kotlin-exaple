package com.example.productexample.core

import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

abstract class BaseController<T, ID>(protected val baseCrud: BaseCrud<T, ID>) {

    @ApiOperation("Get all Entities")
    @GetMapping
    fun findAll() = baseCrud.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: ID): ResponseEntity<T> = baseCrud.findById(id)?.let {
        status(HttpStatus.OK).body(it)
    }?: run {
        status(HttpStatus.NO_CONTENT).body(null)
    }

    @PostMapping
    fun save(@Valid @RequestBody body: T) = baseCrud.save(body)

    @PutMapping
    fun update(@RequestBody body: T) = baseCrud.update(body)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: ID) = baseCrud.deleteById(id)

}