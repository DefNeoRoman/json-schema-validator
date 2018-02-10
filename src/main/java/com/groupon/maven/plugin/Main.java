package com.groupon.maven.plugin;

import com.groupon.maven.plugin.tree.JsonParserNode;
import jregex.Matcher;
import jregex.Pattern;
import jregex.Replacer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class Main {

    public static void main(String[] args) {
        try {
       StringBuilder sb = new StringBuilder();
       Stream<String> stringStream = Files.lines(Paths.get("src\\test\\resources\\input-json-files\\myJson.json"));
       stringStream.forEach(sb::append);
       String json = sb.toString();
            Pattern p=new Pattern("(?(?=\\W)[^\\:^,^/^\\{^\\}]|)");
            Replacer r=p.replacer("");
            String result=r.replace(json.trim());
            String[] array = result.split(",");
            List<JsonParserNode> nodeList = new ArrayList<>();
            JsonParserNode parent = new JsonParserNode();
            for (int i=0;i<array.length;i++){
                String[] inner = array[i].split(":");
                 for(int l=0; l<inner.length;l++){
                           JsonParserNode current = new JsonParserNode();
                           int openNode = checkOpenNode(inner[l]);
                           if(openNode != 0){
                              current.setKey(inner[i]);

                           }
                           int closeNode = checkCloseNode(inner[l]);
                           if(closeNode != 0){

                           }else{

                           }

                           System.out.println(closeNode);
                           System.out.println(inner[l]);
                       }
               }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int checkOpenNode(String string){
            Pattern open = new Pattern("\\{");
            Matcher op = open.matcher(string);

            return op.findAll().count();
    }
    public static int checkCloseNode(String string){
           Pattern close = new Pattern("}");
           Matcher cl = close.matcher(string);

           return cl.findAll().count();
    }
}
