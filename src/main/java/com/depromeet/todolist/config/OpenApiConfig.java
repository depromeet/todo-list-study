package com.depromeet.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    Info info = new Info()
        .title("Todo-list api")
        .description("투두 리스트 스터디")
        .version("v1");

    return new OpenAPI()
        .components(new Components())
        .info(info);
  }
}
