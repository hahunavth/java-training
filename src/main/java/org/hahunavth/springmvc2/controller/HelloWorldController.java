package org.hahunavth.springmvc2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HelloWorldController {
    @RequestMapping("/welcome")
    @ResponseBody

    // Testor Method
    public String helloGfg()
    {
        return "Welcome to GeeksforGeeks!";
    }
}
