package de.schauderhaft.releaseit.main.service;

import com.netflix.hystrix.HystrixCommandMetrics;
import de.schauderhaft.releaseit.main.repository.EvilDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Service
public class TestDataService {
    private final EvilDataRepository evilDataRepository;
    private WebRequestCommand webService;

    @Autowired
    public TestDataService(EvilDataRepository evilDataRepository) {
        this.evilDataRepository = evilDataRepository;
    }


    @PostConstruct
    public void run4ever() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(home());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }.start();
    }

    String home() {
        long start = System.currentTimeMillis();
        String result = null;
        try {
            webService = new WebRequestCommand(evilDataRepository);
            result = webService.queue().get(60000, MILLISECONDS);
            HystrixCommandMetrics metrics = webService.getMetrics();
            System.out.println("error percentage " + metrics.getHealthCounts().getErrorPercentage());
            if (webService.isCircuitBreakerOpen()) {
                return "circuit breaker open";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            result = "geht gerade nicht.";
        }
        return "Alles ist Gut: " + (System.currentTimeMillis() - start) + " " + result;
    }

    public WebRequestCommand currentWebRequest() {
        return webService;
    }
}
