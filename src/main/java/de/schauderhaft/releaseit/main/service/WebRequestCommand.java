package de.schauderhaft.releaseit.main.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import de.schauderhaft.releaseit.main.repository.EvilDataRepository;

public class WebRequestCommand extends HystrixCommand<String> {
    private final EvilDataRepository baneService;

    public WebRequestCommand(EvilDataRepository baneService) {

        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("test"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationThreadTimeoutInMilliseconds(500)));
        this.baneService = baneService;
    }

    @Override
    protected String run() {
        return baneService.connectToEvilEndpoint();
    }

    @Override
    protected String getFallback() {
       return "SERVICE NOT AVAILABLE";
    }
}
