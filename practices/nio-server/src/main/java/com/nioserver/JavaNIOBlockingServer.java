package com.nioserver;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : com.nioserver
 * fileName       : JavaNIOBlockingServer
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Slf4j
public class JavaNIOBlockingServer {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("start JavaNIONonBlockingServer");
        try (ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress("localhost", 8080));

            while (true) {
                SocketChannel clientSocket = serverSocket.accept();

                ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
                clientSocket.read(requestByteBuffer);
                requestByteBuffer.flip();
                String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
                log.info("request: {}", requestBody);

                ByteBuffer responseByteBuffer = ByteBuffer.wrap("This is server".getBytes());
                clientSocket.write(responseByteBuffer);
                clientSocket.close();
            }
        }
    }
}
