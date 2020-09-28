package com.jvm.demo.controller;

public class StackVo {
    private String variableName;
    private String type;
    private String value;
    private String quote;
    StackVo(String variableName,String type,String value,String quote){
        this.variableName = variableName;
        this.type = type;
        this.value = value;
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public String getVariableName() {
        return variableName;
    }
}
