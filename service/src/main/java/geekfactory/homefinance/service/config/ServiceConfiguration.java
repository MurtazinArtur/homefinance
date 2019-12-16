package geekfactory.homefinance.service.config;

import geekfactory.homefinance.dao.config.DaoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Import(DaoConfiguration.class)
@ComponentScan("geekfactory.homefinance.service")
public class ServiceConfiguration {

}
