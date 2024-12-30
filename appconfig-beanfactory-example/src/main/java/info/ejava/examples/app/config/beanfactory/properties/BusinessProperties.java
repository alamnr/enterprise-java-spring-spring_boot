package info.ejava.examples.app.config.beanfactory.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@ConfigurationProperties("app.config.business")
@Data
@Validated
public class BusinessProperties {

    @NotNull
    private String name;
    @NotNull
    private String streetAddress;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String zipCode;

    private String notes;
}
