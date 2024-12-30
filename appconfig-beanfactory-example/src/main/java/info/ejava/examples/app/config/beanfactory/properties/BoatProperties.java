package info.ejava.examples.app.config.beanfactory.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@ConfigurationProperties("app.config.boat")
@Validated
public class BoatProperties {

    @NotBlank
    private String name;

    // public BoatProperties(String name) {
    //     this.name = name;
    // }
    public String getName() {
        return name;
    }

    public void setName(String nameX) {
        this.name = nameX;
    }

    @Override
    public String toString() {
        return "BoatProperties{name='" + name + "\'}";
    }

}
