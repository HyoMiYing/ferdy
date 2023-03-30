package rokklancar.ferdydurkeaudiobookplayer.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class MainConfig {

    @Bean
    public DataSource getDataSource() {
                return DataSourceBuilder.create()
                                .driverClassName("org.postgresql.Driver")
                                .url("jdbc:postgresql://localhost:5432/springdb")
                        .password("springpassword")
                        .username("springbootone")
                                .build();
            }
}

