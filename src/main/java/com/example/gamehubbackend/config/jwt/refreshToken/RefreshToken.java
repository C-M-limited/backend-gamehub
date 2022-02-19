package com.example.gamehubbackend.config.jwt.refreshToken;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String refreshToken;

    private Timestamp expiry_date;

    private Long user_id;

    public RefreshToken(String refreshToken,Long user_id) {
        this.refreshToken = refreshToken;
        this.user_id = user_id;

    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "id=" + id +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiry_date=" + expiry_date +
                ", user_id=" + user_id +
                '}';
    }
}
