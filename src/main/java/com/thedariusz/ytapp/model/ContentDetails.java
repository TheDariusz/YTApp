package com.thedariusz.ytapp.model;

public record ContentDetails(
        String duration,
        String dimension,
        String definition,
        String caption,
        String licensedContent,
        ContentRating contentRating,
        String projection

) {
}
