package com.nioserver;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * packageName    : com.nioserver
 * fileName       : JavaIOSingleServer
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Slf4j
public class JavaIOSingleServer {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start JavaIOSingleServer");
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress("localhost", 8080));

            Socket clientSocket = serverSocket.accept();

            byte[] requestBytes = new byte[1024];
            InputStream in = clientSocket.getInputStream();
            in.read(requestBytes);
            log.info("request: {}", new String(requestBytes).trim());

            OutputStream out = clientSocket.getOutputStream();
            String response = "This is server";
            out.write(response.getBytes());
            out.flush();
        }
        log.info("end JavaIOSingleServer");
    }
}
