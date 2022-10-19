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
                        .description("Ini adalah simple API Reservasi Ticket\n\n" +
                                "API yang berisi keperluan untuk reservasi ticket bioskop\n" +
                                "didalam API ini tedapat beberapa controller yang dapat digunakan, hasil akhir dari API ini adalah\n" +
                                "untuk dapat megenerate invoice dari user yang memesan ticket, file akan berbentuk media file pdf \n\n" +
                                "_Berikut contoh file yang akan di hasilkan click [here](https://drive.google.com/file/d/1VZiEgVidY1_RgSyayLKy5AAh6ZN8-MZX/view?usp=sharing)_." +
                                " (contoh tersebut digenerate melalu endPoint /bioskop/api/invoice/generate/farhanryd6/3)")
                        .version(appVersion)
                        .license(new License()
                                .name("Apcahe 2.0")
                                .url("http://springdoc.org"))
                        .contact(new Contact()
                                .name("Farhan Nuryadi")
                                .email("farhnanuryadi6@gmail.com")));
    }
}
