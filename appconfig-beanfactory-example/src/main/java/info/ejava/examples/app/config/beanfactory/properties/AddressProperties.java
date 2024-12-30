package info.ejava.examples.app.config.beanfactory.properties;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressProperties {

    private String street;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String zip;

}
