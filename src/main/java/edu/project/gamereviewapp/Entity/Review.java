package edu.project.gamereviewapp.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long id;


    private BigDecimal score;


    private String summary;


    private String similarGames;


    private Long gameId;

    public Review(BigDecimal score, String summary, String similarGames, Long gameId) {
        this.score = score;
        this.summary = summary;
        this.similarGames = similarGames;
        this.gameId = gameId;
    }
}