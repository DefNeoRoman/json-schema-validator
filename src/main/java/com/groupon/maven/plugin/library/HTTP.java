package com.groupon.maven.plugin.library;/*
Copyright (c) 2002 JSON.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import java.util.Locale;


public class HTTP {

    /** Carriage return/line feed. */
    public static final String CRLF = "\r\n";


    public static JSONObject toJSONObject(String string) throws JSONException {
        JSONObject     jo = new JSONObject();
        HTTPTokener    x = new HTTPTokener(string);
        String         token;

        token = x.nextToken();
        if (token.toUpperCase(Locale.ROOT).startsWith("library.HTTP")) {

// Response

            jo.put("library.HTTP-Version", token);
            jo.put("Status-Code", x.nextToken());
            jo.put("Reason-Phrase", x.nextTo('\0'));
            x.next();

        } else {

// Request

            jo.put("Method", token);
            jo.put("Request-URI", x.nextToken());
            jo.put("library.HTTP-Version", x.nextToken());
        }

// Fields

        while (x.more()) {
            String name = x.nextTo(':');
            x.next(':');
            jo.put(name, x.nextTo('\0'));
            x.next();
        }
        return jo;
    }



    public static String toString(JSONObject jo) throws JSONException {
        StringBuilder       sb = new StringBuilder();
        if (jo.has("Status-Code") && jo.has("Reason-Phrase")) {
            sb.append(jo.getString("library.HTTP-Version"));
            sb.append(' ');
            sb.append(jo.getString("Status-Code"));
            sb.append(' ');
            sb.append(jo.getString("Reason-Phrase"));
        } else if (jo.has("Method") && jo.has("Request-URI")) {
            sb.append(jo.getString("Method"));
            sb.append(' ');
            sb.append('"');
            sb.append(jo.getString("Request-URI"));
            sb.append('"');
            sb.append(' ');
            sb.append(jo.getString("library.HTTP-Version"));
        } else {
            throw new JSONException("Not enough material for an library.HTTP header.");
        }
        sb.append(CRLF);
        // Don't use the new entrySet API to maintain Android support
        for (final String key : jo.keySet()) {
            String value = jo.optString(key);
            if (!"library.HTTP-Version".equals(key)      && !"Status-Code".equals(key) &&
                    !"Reason-Phrase".equals(key) && !"Method".equals(key) &&
                    !"Request-URI".equals(key)   && !JSONObject.NULL.equals(value)) {
                sb.append(key);
                sb.append(": ");
                sb.append(jo.optString(key));
                sb.append(CRLF);
            }
        }
        sb.append(CRLF);
        return sb.toString();
    }
}
