package info.ejava.examples.app.config.beanfactory.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@ConfigurationProperties("user")
@Data
public class UserProperties {

    @NotNull
    private String name;
    @NotNull
    private String home;
    @NotNull
    private String timeZone;
}
