package geekfactory.homefinance.service.config;

import geekfactory.homefinance.dao.config.DaoConfiguration;
import org.springframework.context.annotation.*;

@Configuration
@Import(DaoConfiguration.class)
@ComponentScan("geekfactory.homefinance.service")
public class ServiceConfiguration {
}
