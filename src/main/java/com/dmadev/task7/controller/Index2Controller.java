package com.dmadev.task7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/2")
public class Index2Controller {

    @GetMapping("")
    public String indexView(){
        return "index2";
    }


}
