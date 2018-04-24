package com.listen.ip.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpMethodDemo {
    /**
     * GET method
     * @param path
     * @return
     * @throws IOException
     */
    public static String httpGet(String path) throws IOException {
        URL url = new URL(path + "?param1=hjc1&param2=hjc2");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null){
            buffer.append(line).append("\r\n");
        }

        reader.close();
        return buffer.toString();
    }

    public static String httpPost(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");

        conn.setDoOutput(true);
        conn.connect();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        writer.write("param1=hjc1&para2=hjc2");
        writer.close();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuffer buffer = new StringBuffer();

        String line = "";
        while ((line = reader.readLine()) != null){
            buffer.append(line);
        }

        reader.close();
        return buffer.toString();
    }
}
