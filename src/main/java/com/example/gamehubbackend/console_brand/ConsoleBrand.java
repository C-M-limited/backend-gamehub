package com.example.gamehubbackend.console_brand;

import javax.persistence.*;


@Entity
@Table
public class ConsoleBrand {
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "console_brand_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "console_brand_sequence"
    )
    private int console_brand_id;
    private String console_brand_name;

    public ConsoleBrand(){

    }

    public ConsoleBrand(int console_brand_id, String console_brand_name) {
        this.console_brand_id = console_brand_id;
        this.console_brand_name = console_brand_name;
    }

    public ConsoleBrand(String console_brand_name) {
        this.console_brand_name = console_brand_name;
    }

    public int getConsole_brand_id() {
        return console_brand_id;
    }

    public void setConsole_brand_id(int console_brand_id) {
        this.console_brand_id = console_brand_id;
    }

    public String getConsole_brand_name() {
        return console_brand_name;
    }

    public void setConsole_brand_name(String console_brand_name) {
        this.console_brand_name = console_brand_name;
    }

    @Override
    public String toString() {
        return "Console_Brand{" +
                "console_brand_id=" + console_brand_id +
                ", console_brand_name='" + console_brand_name + '\'' +
                '}';
    }
}
