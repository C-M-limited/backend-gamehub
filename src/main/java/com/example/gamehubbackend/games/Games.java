package com.example.gamehubbackend.games;

import com.example.gamehubbackend.console.Console;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.io.Serializable;

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
//    @JsonIgnoreProperties("games")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Console console;

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
