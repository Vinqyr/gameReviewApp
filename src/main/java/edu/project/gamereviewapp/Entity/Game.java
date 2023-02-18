package edu.project.gamereviewapp.Entity;

import edu.project.gamereviewapp.Enum.Genre;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @ToString.Exclude
    private Long id;


    private String name;


    private String developer;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Genre> genre;


    private OffsetDateTime releaseDate;

    @OneToMany(mappedBy = "gameId")
    private List<Review> reviews;

    public Game(String name, String developer, List<Genre> genre, OffsetDateTime releaseDate, List<Review> reviews) {
        this.name = name;
        this.developer = developer;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.reviews = reviews;
    }
}