package com.depromeet.todolist.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Component
@Configuration
public class OpenApiConfig {

  public OpenAPI openAPI() {
    Info info = new Info();

    return new OpenAPI()
        .components(new Components())
        .info(info);
  }
}
