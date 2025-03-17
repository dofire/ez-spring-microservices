package com.dofire.api_gateway.src;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class TestController {

    @GetMapping("/ok")
    public String test() {
        return "ok";
    }

}
