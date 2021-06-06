package com.example.productexample.core

interface BaseCrud<T, ID> {
    fun findAll(): List<T>
    fun findById(id: ID): T
    fun save(t: T): Boolean
    fun update(t: T): Boolean
    fun deleteById(id: ID): Boolean
}