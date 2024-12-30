package info.ejava.examples.app.config.beanfactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import info.ejava.examples.app.config.beanfactory.properties.PersonProperties;
import info.ejava.examples.app.hello.Hello;
import info.ejava.examples.app.hello.stdout.StdOutHello;

@SpringBootApplication
//@PropertySource("classpath:application.properties")
//@EnableConfigurationProperties(CarProperties.class)
@ConfigurationPropertiesScan
public class SelfConfiguredApp {

    public static void main(String... args) {
        SpringApplication.run(SelfConfiguredApp.class, args);
    }

    @Bean
    @ConditionalOnProperty(prefix = "hello", name = "quiet", havingValue = "true")
    public Hello hello() {
        return new StdOutHello(" Application @Bean says Hey ");
    }

    // @Bean
    // @ConfigurationProperties("app.config.car")
    // public CarProperties carProperties() {
    //     return new CarProperties();
    // }
    @Bean
    @Primary
    @ConfigurationProperties("owner")
    public PersonProperties ownerProps() {
        return new PersonProperties();
    }

    @Bean
    @ConfigurationProperties("manager")
    @Qualifier("manager")
    public PersonProperties managerProps() {
        return new PersonProperties();
    }

}
