package org.zerock.boot08.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log
public class SampleController {

    @GetMapping("/")
    public String index() {
        log.info("IN SAMPLE CONTROLLER: index() running...");
        return "index";
    }

    @RequestMapping("/guest")
    public void forGuest() {
        log.info("IN SAMPLE CONTROLLER: forGuest() running...");
    }

    @RequestMapping("/manager")
    public void forManager() {
        log.info("IN SAMPLE CONTROLLER: forManager() running...");
    }

    @RequestMapping("/admin")
    public void forAdmin() {
        log.info("IN SAMPLE CONTROLLER: forAdmin() running...");
    }
}
