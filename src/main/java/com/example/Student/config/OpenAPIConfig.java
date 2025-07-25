package com.example.Student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hệ Thống Quản Lý Sinh Viên")
                        .description("API REST phục vụ cho việc quản lý sinh viên, giáo viên, lớp học, điểm số và xuất dữ liệu Excel.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Anh Nguyễn")
                                .email("support@example.com")
                                .url("https://example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Dev Server"),
                        new Server().url("https://api.example.com").description("Production Server")
                ));
    }
}
