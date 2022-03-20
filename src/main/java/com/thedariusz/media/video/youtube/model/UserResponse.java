package com.thedariusz.media.video.youtube.model;

public record UserResponse(
        String sub,
        String name,
        String given_name,
        String family_name,
        String picture,
        String local
) {
}
