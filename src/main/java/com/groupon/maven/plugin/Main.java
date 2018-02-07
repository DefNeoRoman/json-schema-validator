package com.groupon.maven.plugin;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.groupon.maven.plugin.tree.JsonNode;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {

        try {
            StringBuilder sb = new StringBuilder();
       Stream<String> stringStream = Files.lines(Paths.get("D:\\forJava\\examples\\validator\\json-schema-validator\\src\\test\\resources\\input-json-files\\myJson.json"));
       stringStream.forEach(sb::append);
       String json = sb.toString();
       JsonReader reader = new JsonReader(new StringReader(json));

        reader.beginObject();
        while(reader.hasNext()){
            JsonToken jsonToken = reader.peek();
            System.out.println(jsonToken.toString());
            System.out.println(reader.nextName());
            if(jsonToken.equals(JsonToken.NAME)){

                System.out.println(jsonToken = reader.peek());
                if(jsonToken.equals(JsonToken.STRING)){

                    System.out.println(reader.nextString());
                    System.out.println(reader.nextName());
                    reader.endObject();
                }
                reader.beginObject();
                continue;
            }
            if(jsonToken.equals(JsonToken.STRING)){
                System.out.println(reader.nextString());
                reader.endObject();
                continue;
            }
            reader.endObject();

        }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    /**
     * Handle an Object. Consume the first token which is BEGIN_OBJECT. Within
     * the Object there could be array or non array tokens. We write handler
     * methods for both. Noe the peek() method. It is used to find out the type
     * of the next token without actually consuming it.
     *
     * @param reader
     * @throws IOException
     */
    private static void handleObject(JsonReader reader) throws IOException
    {
        reader.beginObject();
        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            System.out.println(token.toString());
            if (token.equals(JsonToken.BEGIN_ARRAY))
                handleArray(reader);
            else if (token.equals(JsonToken.END_OBJECT)) {
                System.out.println("in JsonToken.END_OBJECT");
                reader.endObject();
                return;
            } else{

                handleNonArrayToken(reader, token);
            }

        }

    }

    /**
     * Handle a json array. The first token would be JsonToken.BEGIN_ARRAY.
     * Arrays may contain objects or primitives.
     *
     * @param reader
     * @throws IOException
     */
    public static void handleArray(JsonReader reader) throws IOException
    {
        reader.beginArray();
        while (true) {
            JsonToken token = reader.peek();
            System.out.println(token.toString());
            if (token.equals(JsonToken.END_ARRAY)) {
                reader.endArray();
                break;
            } else if (token.equals(JsonToken.BEGIN_OBJECT)) {
                handleObject(reader);
            } else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
            } else{

                handleNonArrayToken(reader, token);
            }


        }
    }

    /**
     * Handle non array non object tokens
     *
     * @param reader
     * @param token
     * @throws IOException
     */
    public static void handleNonArrayToken(JsonReader reader, JsonToken token) throws IOException
    {
        if (token.equals(JsonToken.NAME)){

            System.out.println(reader.nextName());
        }
        else if (token.equals(JsonToken.STRING))
            System.out.println(reader.nextString());
        else if (token.equals(JsonToken.NUMBER))
            System.out.println(reader.nextDouble());
        else
            reader.skipValue();
    }

}
