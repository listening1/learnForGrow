package com.listen.ip.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketServer {
    public static void main(String[] args) {
        run();
    }

    public static void run(){
        int serverPort = 4700;
        ServerSocket serverSocket = null;
        int receiveMsgSize = 0;
        //接收字节缓冲区数组
        byte[] bytes = new byte[32];

        try {
            serverSocket = new ServerSocket(serverPort);
            while (true){
                System.out.println("服务端已经启动 "+serverPort);
                Socket client = serverSocket.accept();
                SocketAddress socketAddress = client.getRemoteSocketAddress();
                System.out.println("收到客户端连接：IP "+socketAddress);

                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                //接收客户端发来的数据，并原样发给客户端
                while ((receiveMsgSize = in.read(bytes)) != -1){
                    String receiveData = new String(bytes.toString());
                    System.out.println(receiveData);
                    out.write(bytes, 0 , receiveMsgSize);

                }
                //释放资源
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
