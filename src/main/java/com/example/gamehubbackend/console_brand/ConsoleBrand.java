package com.example.gamehubbackend.console_brand;

import com.example.gamehubbackend.console.Console;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "console_brand")
public class ConsoleBrand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String name;


    @OneToMany(mappedBy = "console_brand", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
