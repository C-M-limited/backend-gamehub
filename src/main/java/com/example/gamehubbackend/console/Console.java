package com.example.gamehubbackend.console;

import com.example.gamehubbackend.console_brand.ConsoleBrand;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="console")
public class Console {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String name;
//(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "console_brand_id",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ConsoleBrand console_brand;

    public int getConsole_Brand_Id(){
        return console_brand.getId();
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Console )) return false;
//        return id != null && id.equals(((Console) o).getId());
//    }
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }

    @Override
    public String toString() {
        return "Console{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", console_brand=" + console_brand +
                '}';
    }
}
