package info.ejava.examples.app.config.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import info.ejava.examples.app.hello.Hello;
import info.ejava.examples.app.hello.stdout.StdOutHello;

//@SpringBootApplication(exclude = {})
@SpringBootApplication
public class StarterConfiguredApp {

    public static void main(String[] args) {
        SpringApplication.run(StarterConfiguredApp.class, args);
    }

    @Bean
    @ConditionalOnProperty(prefix = "hello", name = "quiet", havingValue = "true")
    public Hello quietHello() {
        return new StdOutHello("hello.quiet property condition set, Application @Bean says hi ");
    }

}
