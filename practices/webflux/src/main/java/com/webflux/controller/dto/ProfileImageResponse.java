package com.webflux.controller.dto;

import lombok.Data;

/**
 * packageName    : com.webflux.controller.dto
 * fileName       : ProfileImageResponse
 * author         : okdori
 * date           : 12/11/23
 * description    :
 */

@Data
public class ProfileImageResponse {
    private final String id;
    private final String name;
    private final String url;
}
