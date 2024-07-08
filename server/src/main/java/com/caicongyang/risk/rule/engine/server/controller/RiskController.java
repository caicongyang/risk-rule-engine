package com.caicongyang.risk.rule.engine.server.controller;

import com.caicongyang.risk.common.RiskFact;
import com.caicongyang.risk.common.RiskResult;
import com.caicongyang.risk.rule.engine.server.script.IScriptExecutor;
import com.caicongyang.risk.rule.engine.server.script.ScriptContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1")
public class RiskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RiskController.class);


    @Autowired
    IScriptExecutor grovvyExecutor;


    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "hello，Spring Boot ！";
    }


    @PostMapping(value = "/risk")
    @ResponseBody
    public RiskResult risk(@RequestBody RiskFact fact) {
        ScriptContext ctx = new ScriptContext();
        //todo 初始化 ctx

        try {
            return grovvyExecutor.execute(fact, ctx);
        } catch (Exception e) {
            LOGGER.error("风控执行异常:{}", fact.getRequestCode(), e);
            return RiskResult.pass("风控执行异常,默认通过！");
        }
    }

}
