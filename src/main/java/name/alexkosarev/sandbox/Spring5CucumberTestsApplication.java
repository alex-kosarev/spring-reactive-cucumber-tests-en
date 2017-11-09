package name.alexkosarev.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class Spring5CucumberTestsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring5CucumberTestsApplication.class, args);
    }
}
