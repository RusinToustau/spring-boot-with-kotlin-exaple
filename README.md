# ProductExample

### Init project

Para inicializar un proyecto con spring boot la recomendación es entrar a este [link](https://start.spring.io/)


### Api Documentation : Swagger2

Swagger2
http://localhost:8080/swagger-ui/

`````@ApiOperation("Get all Entities")`````

Esta herramienta permite documentar la api de manera sencilla. Con tan solo importar su dependencia podremos obtener y agregar una configuración automáticamente tendremos ducuemtados los endepoints y autogenerados curls de ejemplo.

````
	// -------------------- Documentation -------------------- //
	implementation ("io.springfox:springfox-boot-starter:3.0.0")
 
````

````
@Configuration
class SpringFoxConfig {

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()
            .paths(PathSelectors.any())
            .build()

}
````

Finalmente en app deberemos agregar la notaction `@EnableSwagger2`, en nuestro caso : 

````
@SpringBootApplication
@EnableSwagger2
class ProductExampleApplication
  ...

````


### Validations

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

````
    @PostMapping
    fun save(@Valid @RequestBody body: T): ResponseEntity<Boolean> {
        ...
    }
````

### Manejo de Errores

````
@ControllerAdvice
class RestResponseEntityErrorHandler: ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val result: Map<String, List<String?>> = ex.bindingResult.fieldErrors.groupBy({it.field},{it.defaultMessage})
        return ResponseEntity
                .status(status)
                .headers(headers)
                .body(result)
    }
    
    @ExceptionHandler(FileNotFoundException::class)
    fun customExceptionExample(fileNotFoundException: FileNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UPPS!")
    }
}
````

### Databa base integration
Spring Data JPA
[Documentation](https://plugins.gradle.org/plugin/org.jetbrains.kotlin.plugin.jpa)
```
       // Add jpa plugin
	kotlin("plugin.jpa") version "1.3.61"


	// -------------------- DataBase - Spring Data JPA - H2 -------------------- //
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly ("com.h2database:h2")
	
```



