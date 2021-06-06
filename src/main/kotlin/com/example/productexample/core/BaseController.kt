package com.example.productexample.core

import org.springframework.web.bind.annotation.*

abstract class BaseController<T, ID>(protected val baseCrud: BaseCrud<T, ID>) {

    @GetMapping
    fun findAll() = baseCrud.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: ID) = baseCrud.findById(id)

    @PostMapping
    fun save(@RequestBody body: T) = baseCrud.save(body)

    @PutMapping
    fun update(@RequestBody body: T) = baseCrud.update(body)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: ID) = baseCrud.deleteById(id)

}