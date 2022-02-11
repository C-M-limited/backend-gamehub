package com.example.gamehubbackend.games;

import com.example.gamehubbackend.console.Console;
import com.example.gamehubbackend.game_sale_post.GameSalePost;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="games")
public class Games implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,nullable = false)
    private String name;

    @Column(columnDefinition="TEXT")
    private String image_url;

    @ManyToOne(cascade=CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "console_id",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Console console;

    @OneToMany(mappedBy = "games", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<GameSalePost> gameSalePosts;

    public int getConsole_Id(){
        return console.getId();
    }

    public Games() {
    }

    public Games(String name, Console console) {
        this.name = name;
        this.console = console;
    }

    public Games(String name, String image_url, Console console) {
        this.name = name;
        this.image_url = image_url;
        this.console = console;
    }

    @Override
    public String toString() {
        return "Games{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", console=" + console +
                '}';
    }
}
