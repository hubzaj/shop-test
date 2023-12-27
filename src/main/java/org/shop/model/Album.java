package org.shop.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Builder
@Data
@Setter
@Getter
@ToString
public class Album {
    UUID id;
    String title;
    String artist;
    float price;

    @JsonCreator
    public Album(@JsonProperty("id") UUID id,
                 @JsonProperty("title") String title,
                 @JsonProperty("artist") String artist,
                 @JsonProperty("price") float price) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.price = price;
    }
}
