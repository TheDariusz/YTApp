package com.thedariusz.ytapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Thumbnails(
        @JsonProperty("default")
        Thumbnail defaulted,
        Thumbnail medium,
        Thumbnail high,
        Thumbnail standard,
        Thumbnail maxres
) {
}
