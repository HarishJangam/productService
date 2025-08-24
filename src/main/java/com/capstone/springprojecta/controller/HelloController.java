package com.capstone.springprojecta.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/hello")
public class HelloController {

    //localhost:8080/hello/say
    @GetMapping("/say/{name}")
    public String sayHello(@PathVariable("name") String name){
           return "hello "+name;
    }
}
