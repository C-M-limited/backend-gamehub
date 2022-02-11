package com.example.gamehubbackend.console;

import com.example.gamehubbackend.console_brand.ConsoleBrand;
import com.example.gamehubbackend.games.Games;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="console")
public class Console implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "console_brand_id",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ConsoleBrand console_brand;

    @OneToMany(mappedBy = "console", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Games> games;

    public Console(String name, ConsoleBrand console_brand) {
        this.name = name;
        this.console_brand = console_brand;
    }

    public Console(int id, String name, ConsoleBrand console_brand) {
        this.id = id;
        this.name = name;
        this.console_brand = console_brand;
    }

    public int getConsole_Brand_Id(){
        return console_brand.getId();
    }


    @Override
    public String toString() {
        return "Console{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", console_brand=" + console_brand +
                '}';
    }
}
