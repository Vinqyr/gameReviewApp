package edu.project.gamereviewapp.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long id;


    private Double score;


    private String summary;


    private String similarGames;


    private Long gameId;

    public Review(Double score, String summary, String similarGames, Long gameId) {
        this.score = score;
        this.summary = summary;
        this.similarGames = similarGames;
        this.gameId = gameId;
    }

    public Review(int i, String nice, String dota, Long id) {

    }
}