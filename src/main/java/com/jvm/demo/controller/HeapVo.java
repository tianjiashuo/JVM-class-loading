package com.jvm.demo.controller;

public class HeapVo {
    private String name;
    private String value;
    private String quote;
    HeapVo(String name,String value,String quote){
        this.name = name;
        this.value = value;
        this.quote = quote;
    }

    public String getName() {
        return name;
    }

    public String getQuote() {
        return quote;
    }

    public String getValue() {
        return value;
    }
}
