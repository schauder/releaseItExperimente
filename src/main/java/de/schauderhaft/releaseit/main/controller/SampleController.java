package de.schauderhaft.releaseit.main.controller;

import de.schauderhaft.releaseit.main.service.BaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    private BaneService baneService;

    @Autowired
    public SampleController(BaneService baneService) {
        this.baneService = baneService;
    }

    @RequestMapping("/ok")
    @ResponseBody
    String home() {
        return "Hello, World!";
    }

    @RequestMapping("/wait/{time}")
    @ResponseBody
    String wait(@PathVariable("time") Long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
        }
        return "Hello, World!";
    }

    @RequestMapping("/500")
    @ResponseBody
    String gimmefive() {
        throw new RuntimeException("Good bye, World!");
    }
}
