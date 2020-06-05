package com.example.config.swaggerconfig;


import io.swagger.annotations.ApiOperation;
import io.swagger.models.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        final List<ResponseMessage> globalResponses = Arrays.asList(
                new ResponseMessageBuilder()
                        .code(200)
                        .message("OK")
                        .build(),
                new ResponseMessageBuilder()
                        .code(400)
                        .message("参数错误")
                        .build(),
                new ResponseMessageBuilder()
                        .code(401)
                        .message("没有登录")
                        .build(),
                new ResponseMessageBuilder()
                        .code(403)
                        .message("没有权限")
                        .build(),
                new ResponseMessageBuilder()
                        .code(500)
                        .message("系统错误")
                        .build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, globalResponses)
                .globalResponseMessage(RequestMethod.POST, globalResponses)
                .globalResponseMessage(RequestMethod.PUT, globalResponses)
                .globalResponseMessage(RequestMethod.DELETE, globalResponses)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
        //配置鉴权信息
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts())
//                .enable(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot整合Swagger")
                // .description("SpringBoot整合Swagger，详细信息......")
                // .version("9.0")
                // .license("The Apache License")
                // .licenseUrl("http://www.javaboy.org")
                .build();
    }
}
