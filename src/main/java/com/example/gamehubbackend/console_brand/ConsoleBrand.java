package com.example.gamehubbackend.console_brand;

import com.example.gamehubbackend.console.Console;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "console_brand")
public class ConsoleBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "console_brand", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Console> consoles;

    public void setConsoles(Set<Console> consoles) {
        this.consoles = consoles;

        for(Console b : consoles) {
            b.setConsole_brand(this);
        }
    }
    @Override
    public String toString() {
        return "ConsoleBrand{" +
                "console_brand_id=" + id +
                ", console_brand_name='" + name + '\'' +
                ", consoles=" + consoles +
                '}';
    }
}
