package de.schauderhaft.releaseit.main.controller;

import de.schauderhaft.releaseit.main.service.BaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class SampleController {
    private final WebRequestCommand webService;

    @Autowired
    public SampleController(BaneService baneService) {
         webService = new WebRequestCommand(baneService);
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        System.out.println(webService.run());
        return "Alles ist Gut!";
    }

    @RequestMapping("/ok")
    @ResponseBody
    String ok() {
        return "ok";
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
