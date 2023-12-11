package com.websocket.handler;

import lombok.Data;

/**
 * packageName    : com.websocket.handler
 * fileName       : Chat
 * author         : okdori
 * date           : 12/11/23
 * description    :
 */

@Data
public class Chat {
    private final String from;
    private final String message;

    @Override
    public String toString() {
        return this.from + ": " + this.message;
    }
}
