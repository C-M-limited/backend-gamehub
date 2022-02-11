package com.example.gamehubbackend.game_sale_post;

import com.example.gamehubbackend.games.Games;
import com.example.gamehubbackend.subscribed_post.SubscribedPost;
import com.example.gamehubbackend.user_profile.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="gameSalePost")
public class GameSalePost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserProfile userProfile;

    @ManyToOne(cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "games_id",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Games games;
    @Column(nullable = false)
    private int price;
    @Column(length = 50,nullable = false)
    private String place_for_transaction;
    @CreationTimestamp
    private Timestamp created_date;
    @Column(columnDefinition="TEXT")
    private String description;
    private String contact_method;

    @OneToMany(mappedBy = "gameSalePost", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<SubscribedPost> subscribedPosts;

    public GameSalePost(UserProfile userProfile, Games games, int price, String place_for_transaction, String description, String contact_method) {
        this.userProfile = userProfile;
        this.games = games;
        this.price = price;
        this.place_for_transaction = place_for_transaction;
        this.description = description;
        this.contact_method = contact_method;
    }

    public Long getUser_Id(){
        return userProfile.getId();
    }
    public Long getGames_ID() { return games.getId();}


    @Override
    public String toString() {
        return "GameSalePost{" +
                "id=" + id +
                ", userProfile=" + userProfile +
                ", games=" + games +
                ", price=" + price +
                ", place_for_transaction='" + place_for_transaction + '\'' +
                ", created_date=" + created_date +
                ", description='" + description + '\'' +
                ", contact_method='" + contact_method + '\'' +
                '}';
    }
}
