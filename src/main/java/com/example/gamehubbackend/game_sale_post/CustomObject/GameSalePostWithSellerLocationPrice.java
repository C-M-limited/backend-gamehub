package com.example.gamehubbackend.game_sale_post.CustomObject;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameSalePostWithSellerLocationPrice {
    private Long id;
    private String seller;
    private String location;
    private int price;
    private String imageKey;
}




