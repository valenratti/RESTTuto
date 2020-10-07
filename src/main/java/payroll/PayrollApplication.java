package payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



//@EnableSwagger2
@SpringBootApplication
public class PayrollApplication {
    public static void main(String... args) {
        SpringApplication.run(PayrollApplication.class, args);
    }

   /* @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("1.0").title("Server API").description("Documentation Server API v1.0").build());
    }*/

}
