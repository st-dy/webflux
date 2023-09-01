package com.nioserver;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * packageName    : com.nioserver
 * fileName       : JavaIOMultiClient
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Slf4j
public class JavaIOMultiClient {
    private static ExecutorService executorService = Executors.newFixedThreadPool(50);

    @SneakyThrows
    public static void main(String[] args) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            var future = CompletableFuture.runAsync(() -> {
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress("localhost", 8080));

                    OutputStream out = socket.getOutputStream();
                    String requestBody = "This is client";
                    out.write(requestBody.getBytes());
                    out.flush();

                    InputStream in = socket.getInputStream();
                    byte[] responseByte = new byte[1024];
                    in.read(responseByte);
                    log.info("result: {}", new String(responseByte).trim());
                } catch (Exception e) {}
            }, executorService);
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executorService.shutdown();
        long end = System.currentTimeMillis();
        log.info("duration: {}", (end - start) / 1000.0);
    }
}
