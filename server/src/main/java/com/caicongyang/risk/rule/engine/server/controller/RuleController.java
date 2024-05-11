package com.caicongyang.risk.rule.engine.server.controller;

import com.caicongyang.risk.common.RiskFact;
import com.caicongyang.risk.common.RiskResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1")
public class RuleController {


    @PostMapping(value = "/rule")
    @ResponseBody
    public RiskResult risk(@RequestBody RiskFact fact) {


        return null;

    }

}
