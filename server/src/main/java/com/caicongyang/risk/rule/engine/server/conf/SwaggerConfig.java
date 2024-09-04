package com.caicongyang.risk.rule.engine.server.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

/**
 * @author caicongyang1
 * @version id: SwaggerConfig, v 0.1 16/4/22 下午4:12 caicongyang1 Exp $$
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Value("${swagger.enable:true}")
    private boolean swaggerEnableFlag;

    @Bean
    public Docket createRestApi() throws IOException {
        return new Docket(DocumentationType.SWAGGER_2).enable(swaggerEnableFlag).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.caicongyang.risk.rule.engine.server")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() throws IOException {
        return new ApiInfoBuilder().title("risk-rule-engine").version("0.0.1").build();
    }
}
