package info.ejava.examples.app.hello;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import info.ejava.examples.app.hello.stdout.StdOutHello;

@Configuration(proxyBeanMethods = false)
// Auto-configured class satisfied only when file hello.properties present
@ConditionalOnResource(resources = "file:./hello.properties")
// This Auto-configuration class is processed prior to HelloAutoConfiguration
@AutoConfigureBefore(HelloAutoConfiguration.class)
public class HelloResourceAutoConfiguration {

    @Bean
    @Primary
    public Hello resourcHello() {
        return new StdOutHello("hello.properties exist says hello ");
    }

}
