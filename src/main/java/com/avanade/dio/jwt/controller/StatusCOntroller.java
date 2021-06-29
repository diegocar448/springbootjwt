package com.avanade.dio.jwt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*anotation para informar que é uma api*/
@RestController
public class StatusCOntroller {

    @RequestMapping("/status")
    public String viewStatus(){
        return "online";
    }


}
