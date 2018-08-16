package com.ingamedeo.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController implements ErrorController {

    private static final String EMPTY = "";
    private static final String PATH = "/error";

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return EMPTY;
    }

    @RequestMapping(value = PATH)
    public String error() {
        return EMPTY;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
