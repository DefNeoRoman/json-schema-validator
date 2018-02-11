package com.groupon.maven.plugin;

import com.groupon.maven.plugin.library.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Stream<String> stringStream = null;
        try {
            stringStream = Files.lines(Paths.get("src\\test\\resources\\input-json-files\\myJson.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stringStream.forEach(sb::append);
        String json = sb.toString();
        JSONObject jsonObject = new JSONObject(json);
        System.out.println(jsonObject.toString());
    }

}
