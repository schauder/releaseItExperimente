package de.schauderhaft.releaseit.main.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import de.schauderhaft.releaseit.main.service.SimpleService;

public class WebRequestCommand extends HystrixCommand<String> {
    private final SimpleService baneService;

    protected WebRequestCommand(SimpleService baneService) {
        super(HystrixCommandGroupKey.Factory.asKey("NewGroup"));
        this.baneService = baneService;
    }

    @Override
    protected String run() {
        return  baneService.connectToBane();
    }
}
