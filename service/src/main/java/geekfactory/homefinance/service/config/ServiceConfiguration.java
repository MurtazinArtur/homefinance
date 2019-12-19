package geekfactory.homefinance.service.config;

import geekfactory.homefinance.dao.config.DaoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfiguration.class)
@ComponentScan("geekfactory.homefinance.service")
public class ServiceConfiguration {

}
