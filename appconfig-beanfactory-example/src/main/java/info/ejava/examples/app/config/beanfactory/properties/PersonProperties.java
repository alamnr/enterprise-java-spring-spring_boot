package info.ejava.examples.app.config.beanfactory.properties;

import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Validated
public class PersonProperties {

    @NotNull
    private String name;

    @NestedConfigurationProperty
    @NotNull
    private AddressProperties address;
}
