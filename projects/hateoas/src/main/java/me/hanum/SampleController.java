package me.hanum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/hello")
    public Hello hello() {
        Hello hello = new Hello();
        hello.setPrefix("Hey,");
        hello.setName("hanum");

        Resource<Hello>
    }
}
