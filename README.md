#ProductExample


###Api Documentation

Swagger2
http://localhost:8080/swagger-ui/

`````@ApiOperation("Get all Entities")`````


###Validations

[How to use Java Bean Validation in Spring Boot](https://nullbeans.com/how-to-use-java-bean-validation-in-spring-boot/)

[APi Documentation](https://javaee.github.io/javaee-spec/javadocs/javax/validation/constraints/package-summary.html)

````
 data class Product(
    @get:Size(min = 3, max = 20)
    val name: String,
    @get:Min(value = 0)
    val price: Double) {
    
    ...
 }
````

    @PostMapping
    fun save(@Valid @RequestBody body: T): ResponseEntity<Boolean> {
        ...
    }
````
