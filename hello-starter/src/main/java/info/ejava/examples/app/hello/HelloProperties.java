package info.ejava.examples.app.hello;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@ConfigurationProperties("hello")
@Data
@Validated
public class HelloProperties {

    @NotBlank
    private String greeting = "Hello Properties default greeting say Hola! ";

}
