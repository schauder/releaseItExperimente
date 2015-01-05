package de.schauderhaft.releaseit.evilserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EvilController {
    private boolean defective = false;

    @RequestMapping("/ok")
    @ResponseBody
    String ok() {
        if (defective) {
            return wait(Long.valueOf(5));
        } else {
            return "Hello, World!";
        }
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

    @RequestMapping("/defective/{flag}")
    @ResponseBody
    String defective(@PathVariable("flag") Boolean flag) {
        defective = flag;
        return String.format("defective set to %s", flag);
    }
}
