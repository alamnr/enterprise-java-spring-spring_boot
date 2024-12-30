package info.ejava.examples.app.config.beanfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import info.ejava.examples.app.config.beanfactory.properties.BoatProperties;
import info.ejava.examples.app.config.beanfactory.properties.BusinessProperties;
import info.ejava.examples.app.config.beanfactory.properties.CarProperties;
import info.ejava.examples.app.config.beanfactory.properties.CompanyProperties;
import info.ejava.examples.app.config.beanfactory.properties.CorporationProperties;
import info.ejava.examples.app.config.beanfactory.properties.PersonProperties;
import info.ejava.examples.app.config.beanfactory.properties.RouteProperties;
import info.ejava.examples.app.config.beanfactory.properties.UserProperties;
import info.ejava.examples.app.hello.Hello;
import jakarta.inject.Inject;
import jakarta.inject.Named;

//@Component
@Named
public class AppCommand implements CommandLineRunner {

    private final Hello greeter;

    @Value("${app.audience:Default World}")
    //@Value("${app.audience}")
    private String audience;

    @Value("${app.commonProperty:not supplied}")
    private String commonProperty;

    @Value("${app.appProperty:not supplied}")
    private String appProperty;

    @Value("${app.devProperty:not supplied}")
    private String devProperty;

    @Value("${app.qaProperty:not supplied}")
    private String qaProperty;

    @Value("${app.prodProperty:not supplied}")
    private String prodProperty;

    public AppCommand(Hello greeter) {
        this.greeter = greeter;
    }

    //@Autowired
    @Inject
    private CarProperties carProperties;

    @Autowired
    private BoatProperties boatProperties;
    @Autowired
    private CompanyProperties companyProperties;
    @Autowired
    private BusinessProperties businessProperties;
    @Autowired
    private CorporationProperties corporationProperties;
    @Autowired
    private RouteProperties routeProperties;
    @Autowired
    private UserProperties userProp;

    @Autowired
    private PersonProperties ownerProp;

    @Autowired
    @Qualifier("manager")
    private PersonProperties manager;

    @Override
    public void run(String... args) throws Exception {
        greeter.sayHello(audience);
        System.out.println("commonProperty = " + commonProperty);
        System.out.println("appProperty = " + appProperty);
        System.out.println("devProperty = " + devProperty);
        System.out.println("qaProperty = " + qaProperty);
        System.out.println("prodProperty = " + prodProperty);

        System.out.println("carProperties= " + carProperties);
        System.out.println("boatProperties= " + boatProperties);
        System.out.println("companyProperties= " + companyProperties);

        System.out.println("businessProperties= " + businessProperties);
        System.out.println("corporationProperties= " + corporationProperties);
        System.out.println("routeProperties= " + routeProperties);
        System.out.println("userProperties= " + userProp);

        System.out.println("ownerProperties= " + ownerProp);

        System.out.println("managerProperties= " + manager);
    }

}
