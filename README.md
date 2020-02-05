# ErrorHandling Maven/Kotlin/Exposed


This is a demo to handle errors on Spring with Kotlin, and configure the error body response for spring

## Important points

### Classes with @ResponseStatus  
```
@ResponseStatus(code = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(message: String) : RuntimeException(message)
```
If you throw this exception, the response will have the httpCode will that is indicated on @ResponseStatus(code=....)


### Handle the error attributes 
Check the file: ErrorApplication.kt
```
@Component
class MyCustomErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(
            webRequest: WebRequest, includeStackTrace: Boolean): Map<String, Any> {
        val errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace)

        ....
        
    errorAttributes.remove("trace")
        errorAttributes.remove("path")
        return errorAttributes
    }
}
```
If this component is created and injected, will allow to add logic or remove parameters, sometimes
the client don't need all the trace, so is a good idea to handle this information and show only the 
necessary information

### Usage
Requirements
* Jdk 8
* IDE (Eclipse, IntelliJ Community Edition)

Commands

```mvn clean package```

```java -jar target/error-develop.jar```

#### Notes
this example use H2 a database memory

