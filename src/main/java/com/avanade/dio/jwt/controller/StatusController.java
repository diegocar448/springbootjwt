package com.avanade.dio.jwt.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*anotation para informar que é um controller Rest*/
@RestController
public class StatusController {

    @RequestMapping("/status")
    public String viewStatus(){
        return "online";
    }


}
