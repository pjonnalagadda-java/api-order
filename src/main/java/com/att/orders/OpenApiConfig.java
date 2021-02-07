package com.att.orders;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig  {
 	  
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Orders API by pavan jonnalagadda").description(
                        "This is a sample orders API. ITS ONLY A API DEFINITION AND THERE IS NO IMPLEMENTATION EXISTS."
                        + "Its expected that you mock these APIs for these implementation"));
    }
	
}
