package org.shop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@ToString
@Builder
public class Album {
    UUID id;
    String title;
    String artist;
    float price;
}
