package com.listen.ip.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Java中发出的Http请求可以通过HttpURLConnection 来实现
 */
public class HttpUtil {
    public static void main(String[] args) {
        try {
            String destination = "http://lib.utsz.edu.cn/";
            //定义url
            URL url = new URL(destination);
            //打开链接,强制转换为HttpUrlConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            //获取输入流，并用BufferedReader进行封装
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuffer sBuffer = new StringBuffer();
            String line = "";

            //读取返回的内容
            while ((line = reader.readLine()) != null){
                sBuffer.append(line).append("\r\n");
            }

            //打印码状态
            System.out.println("http responseCode ==============> "+ conn.getResponseCode());

            //打印返回内容
            System.out.println("http response =======>" +sBuffer.toString());
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
