package pl.zajavka.model.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pl.zajavka.ApplicationConfigMarker;

@Configuration
@ComponentScan(basePackageClasses = ApplicationConfigMarker.class)
@Import(PersistenceJPAConfiguration.class)
public class ApplicationConfig {
}
