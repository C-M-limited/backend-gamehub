package com.example.gamehubbackend.game_sale_post;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class GameSaleResponseWithConsoleBrand {
    private GameSalePost game_sale_post;
    private String user_name;
    private String game_name;
    private String image_url;
    private String console_brand_name;
}
