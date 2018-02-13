package com.groupon.maven.plugin;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.groupon.maven.plugin.library.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
//C:\Omnicomm\json-schema-validator\json-schema-validator\src\test\resources\input-json-files\localization-en.json
public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Stream<String> stringStream = null;
        try {
            stringStream = Files.lines(Paths.get("src\\test\\resources\\input-json-files\\localization-en.json"));
            final JsonNode node = JsonLoader.fromPath(Paths.get("src\\test\\resources\\input-json-files\\localization-en.json").toString()) ;
            System.out.println(new JSONObject(node.toString()).toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
//        stringStream.forEach(sb::append);
//        String json = sb.toString();
//        JSONObject jsonObject = new JSONObject(json);
//        String test = jsonObject.toString();
//        System.out.println(jsonObject.toString());
    }

}
