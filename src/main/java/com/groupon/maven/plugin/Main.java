package com.groupon.maven.plugin;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {

        try {
            StringBuilder sb = new StringBuilder();
       Stream<String> stringStream = Files.lines(Paths.get("D:\\mySandbox2\\validator\\json-schema-validator\\src\\test\\resources\\input-json-files\\localization-ru.json"));
       stringStream.forEach(sb::append);
       String json = sb.toString();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader = recurseParsing(reader);

            JsonToken peek = reader.peek();
            if (peek.equals(JsonToken.BEGIN_OBJECT)){

                reader = recurseParsing(reader);
            }
            if(peek.equals(JsonToken.NAME)){
                
                reader = recurseParsing(reader);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    public static JsonReader recurseParsing(JsonReader reader) throws IOException {
        int count = 0;
        reader.beginObject();
        while(reader.hasNext()){
            JsonToken token = reader.peek();
            if(token.equals(JsonToken.BEGIN_OBJECT)) {
                reader.beginObject();
                count++;
            }
            if(token.equals(JsonToken.NAME)) System.out.println(reader.nextName());
            if(token.equals(JsonToken.STRING)) reader.nextString();
        }
        for (int i=1; i<count;i++){
            reader.endObject();
        }
        System.out.println(reader.peek().toString());

        return reader;
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
