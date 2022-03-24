package com.example.gamehubbackend.game_sale_post.CustomObject;

import com.example.gamehubbackend.game_sale_post.GameSalePost;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class GameSaleResponseWithConsoleBrand implements Serializable {
    private GameSalePost game_sale_post;
    private String user_name;
    private String game_name;
    private String image_url;
    private String console_brand_name;
}
