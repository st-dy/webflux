package com.nioserver;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * packageName    : com.nioserver
 * fileName       : JavaIOClient
 * author         : okdori
 * date           : 2023/09/01
 * description    :
 */

@Slf4j
public class JavaIOClient {
    @SneakyThrows
    public static void main(String[] args) {
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
        }
    }
}
