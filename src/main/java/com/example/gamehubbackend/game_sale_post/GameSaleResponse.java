package com.example.gamehubbackend.game_sale_post;

import com.example.gamehubbackend.user_profile.UserProfile;
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

}
