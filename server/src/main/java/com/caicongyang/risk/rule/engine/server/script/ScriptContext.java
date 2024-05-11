package com.caicongyang.risk.rule.engine.server.script;

import com.caicongyang.risk.common.RiskResult;

import java.util.List;
import java.util.Map;

public class ScriptContext {

    /**
     * 场景code
     */
    private String sceneCode;


    /**
     * 是否
     */
    private Boolean fireOne = false;

    /**
     * 规则因子code
     */
    private List<String> factorCode;

    /**
     * 规则因子编码： grovvy 脚本
     */
    private Map<String, String> factorMap;


    /**
     * factorValueMap： 规则因子编码： 因子的的值
     * 执行完因子脚本后，放到次map 中
     */
    private Map<String, Object> factorValueMap;


    /**
     * 规则code
     */
    private List<String> ruleCode;


    /**
     * 规则code: grovvy 脚本
     */
    private Map<String, String> ruleMap;

    /**
     * 命中规则code ： action
     */
    private Map<String, RiskResult> fireRuleMap;


    /**
     * 执行code
     */
    private List<String> actionCode;


    /**
     * 执行code:  grovvy 脚本
     */
    private Map<String, String> actionMap;


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


    public Map<String, String> getFactorMap() {
        return factorMap;
    }

    public void setFactorMap(Map<String, String> factorMap) {
        this.factorMap = factorMap;
    }

    public Map<String, String> getRuleMap() {
        return ruleMap;
    }

    public void setRuleMap(Map<String, String> ruleMap) {
        this.ruleMap = ruleMap;
    }

    public Map<String, String> getActionMap() {
        return actionMap;
    }

    public void setActionMap(Map<String, String> actionMap) {
        this.actionMap = actionMap;
    }

    public Map<String, Object> getFactorValueMap() {
        return factorValueMap;
    }

    public void setFactorValueMap(Map<String, Object> factorValueMap) {
        this.factorValueMap = factorValueMap;
    }


    public Boolean getFireOne() {
        return fireOne;
    }

    public void setFireOne(Boolean fireOne) {
        this.fireOne = fireOne;
    }

    public Map<String, RiskResult> getFireRuleMap() {
        return fireRuleMap;
    }

    public void setFireRuleMap(Map<String, RiskResult> fireRuleMap) {
        this.fireRuleMap = fireRuleMap;
    }
}
