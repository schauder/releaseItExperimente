package de.schauderhaft.releaseit.evilserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = {"de.schauderhaft.releaseit.evilserver"})
public class EvilServerStarter {

    public static void main(String[] args) throws Exception {
        System.setProperty("server.port", "8000");
        SpringApplication.run(EvilServerStarter.class, args);
    }
}
