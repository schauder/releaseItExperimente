package de.schauderhaft.releaseit.main.controller;

import com.netflix.hystrix.HystrixCommandMetrics;
import de.schauderhaft.releaseit.main.service.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HystrixStatusController {

    private final TestDataService testDataService;

    @Autowired
    public HystrixStatusController(TestDataService testDataService) {
        this.testDataService = testDataService;
    }

    @RequestMapping("/")
    @ResponseBody
    public String printStats() {
        HystrixCommandMetrics metrics = testDataService.currentWebRequest().getMetrics();
        StringBuilder result = new StringBuilder();
        result.append("Total requests: ").append(metrics.getHealthCounts().getTotalRequests()).append("<br/>");
        result.append("Error count:    ").append(metrics.getHealthCounts().getErrorCount()).append("<br/>");
        result.append("Error percentage: ").append(metrics.getHealthCounts().getErrorPercentage()).append("<br/>");

        return result.toString();
    }
}
