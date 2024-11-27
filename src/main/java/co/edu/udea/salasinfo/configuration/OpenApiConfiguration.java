package co.edu.udea.salasinfo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Generated
@Configuration
public class OpenApiConfiguration {
    private static final String APPLICATION_TITLE = "SalasInfo API";
    private static final String TERMS_OF_SERVICE = "https://swagger.io/terms/";

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${app.description}") String appDescription,
            @Value("${app.version}") String appVersion
                                 ) {
        return new OpenAPI()
                .info(new Info()
                        .title(APPLICATION_TITLE)
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService(TERMS_OF_SERVICE)
                );
    }
}
