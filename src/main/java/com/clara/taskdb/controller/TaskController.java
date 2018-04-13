package com.clara.taskdb.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by vq4988nx on 4/12/2018.
 */
public class TaskController {

    @GetMapping("/")
    public String homePage() {
        return "index.html";
    }
}
