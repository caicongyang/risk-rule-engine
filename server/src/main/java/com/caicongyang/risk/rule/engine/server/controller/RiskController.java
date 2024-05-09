package com.caicongyang.risk.rule.engine.server.controller;

import com.caicongyang.risk.common.RiskFact;
import com.caicongyang.risk.common.RiskResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1")
public class RiskController {

    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "hello，Spring Boot ！";
    }


    @PostMapping(value = "/risk")
    @ResponseBody
    public RiskResult risk(@RequestBody RiskFact fact) {


        return null;

    }

}
