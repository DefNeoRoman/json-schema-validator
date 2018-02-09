package com.groupon.maven.plugin.json;


import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;

public class Testing {
    public static void main(String[] args) throws  IOException
    {

        String json = "{\n" +
                "    \"supportForm\": {\n" +
                "\n" +
                "        \"title\":4,\n" +
                "        \"question\": \"Ваш вопрос:\",\n" +
                "        \"email\": \"Ответ придёт на ваш E-mail:\",\n" +
                "        \"service\": \"Вас обслуживает:\",\n" +
                "        \"attrTitle\": \"Отправить сообщение в техническую поддержку\",\n" +
                "        \"undefined\": \"Информация недоступна\",\n" +
                "        \"tel\": \"тел.\",\n" +
                "        \"subject\": \"Тема:\",\n" +
                "        \"success\": \"Ваш вопрос успешно отправлен в техническую поддержку\",\n" +
                "        \"fail\": \"При попытке отправить запрос произошла ошибка. Пожалуйста, попробуйте позже.\",\n" +
                "        \"errors\": {\n" +
                "            \"email\": \"Введён некорректный E-mail\",\n" +
                "            \"subject\": \"Тема обращения не должна быть пустой\",\n" +
                "            \"question\": \"Ваш вопрос не должен быть пустым\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        // use the reader to read the json to a stream of tokens
        JsonReader reader = new JsonReader(new StringReader(json));
        // we call the handle object method to handle the full json object. This
        // implies that the first token in JsonToken.BEGIN_OBJECT, which is
        // always true.

        handleObject(reader);
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
            if (token.equals(JsonToken.BEGIN_ARRAY))
                handleArray(reader);
            else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
                return;
            } else
                handleNonArrayToken(reader, token);
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

            if (token.equals(JsonToken.END_ARRAY)) {
                reader.endArray();
                break;
            } else if (token.equals(JsonToken.BEGIN_OBJECT)) {
                handleObject(reader);
            } else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
            } else
                handleNonArrayToken(reader, token);
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
        if (token.equals(JsonToken.NAME))
            System.out.println(reader.nextName());
        else if (token.equals(JsonToken.STRING))
            System.out.println(reader.nextString());
        else if (token.equals(JsonToken.NUMBER))
            System.out.println(reader.nextDouble());
        else
            reader.skipValue();
    }
}


