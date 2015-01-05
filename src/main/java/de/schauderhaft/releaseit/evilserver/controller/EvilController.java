package de.schauderhaft.releaseit.evilserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EvilController {

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
