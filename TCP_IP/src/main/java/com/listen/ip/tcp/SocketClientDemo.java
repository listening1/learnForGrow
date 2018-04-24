package com.listen.ip.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

public class SocketClientDemo {
    public static void main(String[] args) {
        int port = 4700;
        String host = "localhost";
        String sendMsg  = "send data form client";
        connect(host,port,sendMsg.getBytes());
    }

    public static void connect(String server,int serverPort,byte[] data){
        Socket socket =null;

        try {
            socket = new Socket(server,serverPort);
            System.out.println("连接服务器并发送数据。。。");
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write(data);
            int totalBytesRcvd = 0;
            int bytesRcvd ;
            while (totalBytesRcvd < data.length){
                bytesRcvd = in.read(data,totalBytesRcvd,data.length-totalBytesRcvd);
                if (bytesRcvd == -1){
                    throw new SocketException("连接中断");
                }
                totalBytesRcvd += bytesRcvd;

            }

            System.out.println("接收的数据 "+ new String(data));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
