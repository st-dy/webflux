package com.nioserver;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * packageName    : com.nioserver
 * fileName       : JavaNIONonBlockingMultiServer
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Slf4j
public class JavaNIONonBlockingMultiServer {
    private static AtomicInteger count = new AtomicInteger(0);
    private static ExecutorService executorService = Executors.newFixedThreadPool(50);

    @SneakyThrows
    public static void main(String[] args) {
        log.info("start JavaNIONonBlockingServer");
        try (ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            serverSocket.configureBlocking(false);

            while (true) {
                SocketChannel clientSocket = serverSocket.accept();
                if (clientSocket == null) {
                    Thread.sleep(100);
//                    log.info("wait accept");
                    continue;
                }

                CompletableFuture.runAsync(() -> {
                    handleRequest(clientSocket);
                }, executorService);
            }
        }
    }

    @SneakyThrows
    private static void handleRequest(SocketChannel clientSocket) {
        ByteBuffer requestByteBuffer = ByteBuffer.allocateDirect(1024);
        while (clientSocket.read(requestByteBuffer) == 0) {
            log.info("Reading...");
        }

        Thread.sleep(10);

        requestByteBuffer.flip();
        String requestBody = StandardCharsets.UTF_8.decode(requestByteBuffer).toString();
        log.info("request: {}", requestBody);

        ByteBuffer responseByteBuffer = ByteBuffer.wrap("This is server".getBytes());
        clientSocket.write(responseByteBuffer);
        clientSocket.close();

        int c = count.incrementAndGet();
        log.info("count: {}", c);
    }
}
