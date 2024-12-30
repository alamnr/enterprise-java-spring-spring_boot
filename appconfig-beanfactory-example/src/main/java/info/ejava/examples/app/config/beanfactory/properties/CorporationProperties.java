package info.ejava.examples.app.config.beanfactory.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@ConfigurationProperties("app.config.corp")
@Data
@Validated
public class CorporationProperties {

    @NotNull
    private String name;

    @NestedConfigurationProperty
    @NotNull
    private AddressProperties address;

}
