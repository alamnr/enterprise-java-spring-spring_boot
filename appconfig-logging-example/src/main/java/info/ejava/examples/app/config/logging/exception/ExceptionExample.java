package info.ejava.examples.app.config.logging.exception;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ExceptionExample implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        try {
            log.info("Calling iThrowException");
            iThrowException();
        } catch (Exception e) {
            log.warn("catch exception ", e);
            log.warn("catch exception {} {} ", "p1", "p2", e);
        }
    }

    private void iThrowException() throws Exception {
        throw new Exception("Example Exception.");
    }

}
