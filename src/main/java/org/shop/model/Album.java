package org.shop.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.UUID;

@Builder
@JsonSerialize
@JsonDeserialize
public class Album {
//    UUID id;
    String title;
    String artist;
    float price;
}
