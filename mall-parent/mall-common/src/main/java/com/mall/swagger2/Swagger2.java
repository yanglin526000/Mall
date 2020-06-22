package com.mall.swagger2;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * <p>
 * Swagger2 Configuration
 * </p>
 *
 * @author yanglin
 * @date 2020-06-22 17:00:07
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${swagger.enable:true}")
    private Boolean enable;

    @Value("${swagger.title:Swagger2}")
    private String title;

    @Value("${swagger.description:Swagger builds the API documentation}")
    private String description;

    @Value("${swagger.termsOfServiceUrl:}")
    private String termsOfServiceUrl;

    @Value("${swagger.version:1.0}")
    private String version;

    @Value("${swagger.contact.name:Linus Yang}")
    private String name;

    @Value("${swagger.contact.url:}")
    private String url;

    @Value("${swagger.contact.email:893713339@qq.com}")
    private String email;

    /**
     * <p>
     * Create Rest Api
     * </p>
     *
     * @return springfox.documentation.spring.web.plugins.Docket
     * @author yanglin
     * @date 2020-06-22 18:52:11
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    /**
     * <p>
     * Swagger2 base info
     * </p>
     *
     * @return springfox.documentation.service.ApiInfo
     * @author yanglin
     * @date 2020-06-22 18:53:04
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact(name, url, email);
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .version(version)
                .contact(contact)
                .build();
    }

    /**
     * <p>
     * Security Authentication Schemes
     * </p>
     *
     * @return java.util.List<springfox.documentation.service.ApiKey>
     * @author yanglin
     * @date 2020-06-22 18:53:26
     */
    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(new ApiKey("token", "token", "header"));
    }

    /**
     * <p>
     * Security Contexts
     * </p>
     *
     * @return java.util.List<springfox.documentation.spi.service.contexts.SecurityContext>
     * @author yanglin
     * @date 2020-06-22 18:54:33
     */
    private List<SecurityContext> securityContexts() {
        return Lists.newArrayList(
                SecurityContext
                        .builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }

    /**
     * <p>
     * Default Auth
     * </p>
     *
     * @return java.util.List<springfox.documentation.service.SecurityReference>
     * @author yanglin
     * @date 2020-06-22 18:54:58
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("token", authorizationScopes));
    }
}
