package info.ejava.examples.app.config.beanfactory.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@ConfigurationProperties("app.config.company")
@Data
@Validated
public class CompanyProperties {

    @NotBlank
    private String name;

}
