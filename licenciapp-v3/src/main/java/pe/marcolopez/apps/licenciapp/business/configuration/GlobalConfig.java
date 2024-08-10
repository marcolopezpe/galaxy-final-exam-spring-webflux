package pe.marcolopez.apps.licenciapp.business.configuration;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {

  @Bean
  public WebProperties.Resources resources() {
    return new WebProperties.Resources();
  }
}
