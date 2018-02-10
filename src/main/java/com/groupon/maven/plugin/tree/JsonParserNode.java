package com.groupon.maven.plugin.tree;

import java.util.LinkedList;
import java.util.List;

public class JsonParserNode {
    private JsonParserNode parent;
    private String key;
    private String value;
    private List<JsonParserNode> childrens;

    public JsonParserNode() {
        this.childrens = new LinkedList<>();
    }

    public JsonParserNode(JsonParserNode parent) {
        this.parent = parent;
        this.childrens = new LinkedList<>();
    }

    public JsonParserNode getParent() {
        return parent;
    }

    public void setParent(JsonParserNode parent) {
        this.parent = parent;
    }

    public List<JsonParserNode> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<JsonParserNode> childrens) {
        this.childrens = childrens;
    }
    public void addChildren(JsonParserNode node){
        childrens.add(node);
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "JsonParserNode{" +
                "parent=" + parent +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", childrens=" + childrens +
                '}';
    }
}
