package com.example.gamehubbackend.subscribed_post.CusotomPackage;

import com.example.gamehubbackend.game_sale_post.GameSalePost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class allSubscribedPostObject {
    private GameSalePost game_sale_post;
    private String user_name;
    private String game_name;
    private String image_url;
    private String console_brand_name;
}
