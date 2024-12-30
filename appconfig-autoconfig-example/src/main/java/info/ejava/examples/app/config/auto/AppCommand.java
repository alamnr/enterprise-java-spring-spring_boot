package info.ejava.examples.app.config.auto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import info.ejava.examples.app.hello.Hello;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppCommand implements CommandLineRunner {

    private final Hello greeter;

    @Override
    public void run(String... args) throws Exception {
        greeter.sayHello(" World");
    }

}
