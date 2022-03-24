package com.example.gamehubbackend.game_sale_post.CustomObject;

import com.example.gamehubbackend.game_sale_post.GameSalePost;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class GameSaleResponse {
    private GameSalePost game_sale_post;
    private String user_name;
    private String game_name;
    private String image_url;
}
