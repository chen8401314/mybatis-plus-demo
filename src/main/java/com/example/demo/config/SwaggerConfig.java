package com.example.demo.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.List;

import static com.example.demo.context.ServiceContext.TOKEN_HEADER;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(new ApiKey(TOKEN_HEADER, TOKEN_HEADER, "header"));
    }

    @Bean
    public Docket documentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .build()
                .protocols(new HashSet<String>(Lists.newArrayList("http")))
                .pathMapping("/")
                .apiInfo(apiInfo())
                .securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("服务API")
                .description("服务端后台接口说明文档")
                .version("1.0")
                .build();
    }
}
