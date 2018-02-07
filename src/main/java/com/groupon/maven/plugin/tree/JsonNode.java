package com.groupon.maven.plugin.tree;

import java.util.LinkedList;

public class JsonNode {

    private String key;

    private String value;
    private LinkedList<Integer> path;

    public JsonNode(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public void setPath(LinkedList<Integer> path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "JsonNode{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", path=" + path.toString() +
                '}';
    }
}
