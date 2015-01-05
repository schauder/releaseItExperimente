package de.schauderhaft.releaseit.main.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import de.schauderhaft.releaseit.main.service.SimpleService;

public class WebRequestCommand extends HystrixCommand<String> {
    private final SimpleService baneService;

    protected WebRequestCommand(SimpleService baneService) {

        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("test"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationThreadTimeoutInMilliseconds(500)));
        this.baneService = baneService;
    }

    @Override
    protected String run() {
        return baneService.connectToBane();
    }
}
