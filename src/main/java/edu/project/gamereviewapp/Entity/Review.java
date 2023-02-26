package edu.project.gamereviewapp.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Double score;


    private String summary;


    private String similarGames;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id",
    nullable = false,
    referencedColumnName = "id")
    @JsonBackReference
    private Game game;

    public Review(Double score, String summary, String similarGames, Game game) {
        this.score = score;
        this.summary = summary;
        this.similarGames = similarGames;
        this.game = game;
    }

    public Review(int i, String nice, String dota, Long id) {

    }
}