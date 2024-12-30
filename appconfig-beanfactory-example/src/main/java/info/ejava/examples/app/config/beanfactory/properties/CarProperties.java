package info.ejava.examples.app.config.beanfactory.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.config.car")
public class CarProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarProperties{name='" + name + "\'}";
    }

}
