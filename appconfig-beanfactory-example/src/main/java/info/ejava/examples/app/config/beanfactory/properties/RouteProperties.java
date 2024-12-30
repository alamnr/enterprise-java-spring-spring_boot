package info.ejava.examples.app.config.beanfactory.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@ConfigurationProperties("app.config.route")
@Data
@Validated
public class RouteProperties {

    @NotNull
    private String name;
    @NestedConfigurationProperty
    @NotNull
    @Size(min = 1)
    private List<AddressProperties> stops;
}
