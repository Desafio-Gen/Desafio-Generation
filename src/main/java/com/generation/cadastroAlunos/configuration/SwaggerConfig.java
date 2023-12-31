package com.generation.cadastroAlunos.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Desafio Generation Nataly Carvalho")
						.description("Desafio Generation")
						.version("V0.0.1")
				.license(new License()
						.name("Nataly Carvalho")
						.url("https://github.com/Nataly-Carvalho/Desafio-Generation"))
				.contact(new Contact()
						.name("Repositorio do blog")
						.url("https://github.com/Nataly-Carvalho/Desafio-Generation")
						.email("natalycarvalho.dev@gmail.com")))
				.externalDocs(new ExternalDocumentation()
						.description("GitHub")
						.url("https://github.com/Nataly-Carvalho/Desafio-Generation"));
	}
	
	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
		return openApi ->{
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation ->{
				
				ApiResponses apiResponses = operation.getResponses();
				
				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluido!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
				
			}));
		};
	}
	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	}

}
