package de.schauderhaft.releaseit.evilserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @RequestMapping("/slowresponse")
    void slowResponse(HttpServletResponse response) throws IOException, InterruptedException {
        byte[] bytes = "Hello waiting world".getBytes();
        ServletOutputStream outputStream = response.getOutputStream();
        for (byte aByte : bytes) {
            outputStream.write(aByte);
            outputStream.flush();
            Thread.sleep(1000);
        }
    }

    @RequestMapping("/slowresponsenoflush")
    void slowResponseNoFlush(HttpServletResponse response) throws IOException, InterruptedException {
        byte[] bytes = "Hello waiting world".getBytes();
        ServletOutputStream outputStream = response.getOutputStream();
        for (byte aByte : bytes) {
            outputStream.write(aByte);
            Thread.sleep(1000);
        }
    }
}
