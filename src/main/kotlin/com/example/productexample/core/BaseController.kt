package com.example.productexample.core

import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.*

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
    fun save(@RequestBody body: T): ResponseEntity<Boolean> =
            if (baseCrud.save(body))
                status(HttpStatus.OK).body(true)
            else
                status(HttpStatus.CONFLICT).body(false)

    @PutMapping
    fun update(@RequestBody body: T): ResponseEntity<Boolean> =
            if (baseCrud.update(body))
                status(HttpStatus.OK).body(true)
            else
                status(HttpStatus.NOT_MODIFIED).body(false)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: ID): ResponseEntity<Boolean> =
            if (baseCrud.deleteById(id))
                status(HttpStatus.OK).body(true)
            else
                status(HttpStatus.NOT_FOUND).body(false)

}