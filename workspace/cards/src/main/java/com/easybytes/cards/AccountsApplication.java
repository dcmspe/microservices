package com.easybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Accounts Microservice REST API",
        version = "v1",
        description = "Accounts Microservice REST API",
        contact = @Contact(name = "Danilo Caetano",
                email = "sample@gmail.com"),
        license = @License(name = "Apache 2.0",
                url = "www.something.com")),
        externalDocs = @ExternalDocumentation(
                description = "Eazybank Accounts microservice REST API Documentation",
                url="http://localhost:8080/swagger-ui/index.htm"
        ))

public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
