package com.thedariusz.ytapp.model;

public record UserDtoWrapper(
        String sub,
        String name,
        String given_name,
        String family_name,
        String picture,
        String local
) {
}
