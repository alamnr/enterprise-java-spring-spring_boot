package info.ejava.examples.app.config.logging;

import java.security.SecureRandom;

import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MdcLogger implements CommandLineRunner {

    private static final String[] USERS = new String[]{"Jim", "Jane", "Kim"};
    private static final SecureRandom r = new SecureRandom();

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            String user = USERS[r.nextInt(USERS.length)];
            MDC.put("user", user);
            MDC.put("requestId", Integer.toString(r.nextInt(9999)));
            doWork();
            MDC.clear();
            doWork();
        }

        // One way to do that is using a finally block and manually calling MDC.clear()
        /*
            try {
            MDC.put("user", user);
            MDC.put("requestId", requestId);
            doWork();
            } finally {
            MDC.clear();
            } */
        // Another is by using a try-with-closable and have the properties automatically cleared when the try block finishes.
        /*
            try (MDC.MDCCloseable mdc = MDC.putCloseable("user", user);
            MDC.MDCCloseable mdc = MDC.putCloseable("requestId", requestId) {
            doWork();
            } */
    }

    private void doWork() {
        log.info("Starting Work");
        log.info("Finishing work");
    }

}
