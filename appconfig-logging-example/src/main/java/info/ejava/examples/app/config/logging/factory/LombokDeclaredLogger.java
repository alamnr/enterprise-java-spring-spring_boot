package info.ejava.examples.app.config.logging.factory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Profile("factory")
public class LombokDeclaredLogger implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("lombok declared SLF4J logger");
    }

}
