package geekfactory.homefinance.config;

import geekfactory.homefinance.dao.config.DaoConfiguration;
import geekfactory.homefinance.dao.model.*;
import geekfactory.homefinance.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfiguration.class)
@ComponentScan("geekfactory.homefinance.service")
public class ServiceConfiguration {
}
