package com.farhan.bioskopapi.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api(){
        String paths[] = {"/bioskop/api/**"};
        String packagesToScan[] = {"com.farhan.bioskopapi.controller"};
        return GroupedOpenApi.builder()
                .group("reservasi-ticket")
                .pathsToMatch(paths)
                .packagesToScan(packagesToScan)
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("Reservasi Ticket API") String appTitle,
                                 @Value("v1.0.0") String appVersion){
        return new OpenAPI()
                .info(new Info().title(appTitle)
                        .description("API yang berisi keperluan untuk reservasi ticket bioskop")
                        .version(appVersion)
                        .license(new License()
                                .name("Apcahe 2.0")
                                .url("http://springdoc.org"))
                        .contact(new Contact()
                                .name("Farhan Nuryadi")
                                .email("farhnanuryadi6@gmail.com")));
    }
}
