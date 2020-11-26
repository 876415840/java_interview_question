package com.stephen.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author stephen
 * @date 2020/11/25 2:22 下午
 */
public class NetServer {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(100);

    public static void startUp(int port) throws IOException {
        System.out.println("启动服务端----------------");
        ServerSocket serverSocket = new ServerSocket(port);

        // 阻塞等待客户端连接
//        Socket socket = serverSocket.accept();
        // BIO方式流处理
//        socket.getInputStream().read();
//        socket.getOutputStream().write();

        // 多线程方式
        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.submit(new RPCThreadProcessor(socket));
        }
    }

    public static void main(String[] args) throws IOException {
        startUp(9999);
    }

}
