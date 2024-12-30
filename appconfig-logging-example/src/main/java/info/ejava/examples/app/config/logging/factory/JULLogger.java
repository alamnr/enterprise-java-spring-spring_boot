package info.ejava.examples.app.config.logging.factory;

import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("factory")
public class JULLogger implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(JULLogger.class.getName());

    @Override
    public void run(String... args) throws Exception {
        logger.info("Java Util Logger message");
    }

}
