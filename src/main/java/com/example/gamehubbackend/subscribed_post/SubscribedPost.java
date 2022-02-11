package com.example.gamehubbackend.subscribed_post;

import com.example.gamehubbackend.game_sale_post.GameSalePost;
import com.example.gamehubbackend.user_profile.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="subscribed_post")
public class SubscribedPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "game_sale_post_id",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    GameSalePost gameSalePost;
    @ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    UserProfile userProfile;

    public SubscribedPost(GameSalePost gameSalePost, UserProfile userProfile) {
        this.gameSalePost = gameSalePost;
        this.userProfile = userProfile;
    }
    public Long getUser_Id(){
        return userProfile.getId();
    }
    public Long getGame_ID() {return gameSalePost.getId();}

    @Override
    public String toString() {
        return "SubscribedPost{" +
                "id=" + id +
                ", gameSalePost=" + gameSalePost +
                ", userProfile=" + userProfile +
                '}';
    }
}
