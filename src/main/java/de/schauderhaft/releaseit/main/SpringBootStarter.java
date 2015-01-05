package de.schauderhaft.releaseit.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"de.schauderhaft.releaseit"})
public class SpringBootStarter {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootStarter.class, args);
    }
}
