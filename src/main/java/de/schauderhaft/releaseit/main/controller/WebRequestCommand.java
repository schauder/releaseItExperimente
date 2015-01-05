package de.schauderhaft.releaseit.main.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import de.schauderhaft.releaseit.main.service.BaneService;

public class WebRequestCommand extends HystrixCommand<String> {
    private final BaneService baneService;

    protected WebRequestCommand(BaneService baneService) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.baneService = baneService;
    }

    @Override
    protected String run() {
        return  baneService.connectToBane();
    }
}
