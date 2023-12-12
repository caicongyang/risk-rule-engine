package com.caicongyang.risk.rule.engine.server.script;

import java.util.List;

public class ScriptContext {

    private String sceneCode;

    private List<String> ruleCode;

    private List<String> factorCode;

    private List<String> actionCode;


    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public List<String> getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(List<String> ruleCode) {
        this.ruleCode = ruleCode;
    }

    public List<String> getFactorCode() {
        return factorCode;
    }

    public void setFactorCode(List<String> factorCode) {
        this.factorCode = factorCode;
    }

    public List<String> getActionCode() {
        return actionCode;
    }

    public void setActionCode(List<String> actionCode) {
        this.actionCode = actionCode;
    }
}
