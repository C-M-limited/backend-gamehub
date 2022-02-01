package com.example.gamehubbackend.console;

import javax.persistence.*;

@Entity
@Table
public class Console {
    @Id
    @SequenceGenerator(
            name="console_sequence",
            sequenceName = "console_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "console_sequence"
    )
    private int console_id;
    private String console_name;
    private int console_brand_id;

    public Console() {
    }

    public Console(int console_id, String console_name, int console_brand_id) {
        this.console_id = console_id;
        this.console_name = console_name;
        this.console_brand_id = console_brand_id;
    }

    public Console(String console_name, int console_brand_id) {
        this.console_name = console_name;
        this.console_brand_id = console_brand_id;
    }

    public int getConsole_id() {
        return console_id;
    }

    public void setConsole_id(int console_id) {
        this.console_id = console_id;
    }

    public String getConsole_name() {
        return console_name;
    }

    public void setConsole_name(String console_name) {
        this.console_name = console_name;
    }

    public int getConsole_brand_id() {
        return console_brand_id;
    }

    public void setConsole_brand_id(int console_brand_id) {
        this.console_brand_id = console_brand_id;
    }

    @Override
    public String toString() {
        return "Console{" +
                "console_id=" + console_id +
                ", console_name='" + console_name + '\'' +
                ", console_brand_id=" + console_brand_id +
                '}';
    }
}
