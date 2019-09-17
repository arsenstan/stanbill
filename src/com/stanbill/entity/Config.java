package com.stanbill.entity;
/*
配置信息Config类与配置信息表 config相对应
 */
public class Config {

    public int id;
    public String key;
    public String value;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}