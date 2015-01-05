package de.schauderhaft.releaseit.main.controller;

import de.schauderhaft.releaseit.main.service.BaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    private BaneService baneService;

    @Autowired
    public SampleController(BaneService baneService) {
        this.baneService = baneService;
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        baneService.connectToBane();
        return "Hello World!";
    }
}
