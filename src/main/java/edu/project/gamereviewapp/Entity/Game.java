package edu.project.gamereviewapp.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.project.gamereviewapp.Enum.Genre;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.ArrayList;
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
    @Type(PostgreSQLEnumType.class)
    private Genre genre;


    private LocalDate releaseDate;

    @OneToMany(mappedBy = "game",orphanRemoval = true,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Review> reviews;

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        for(int i=0;i<reviews.size();i++){
            reviews.get(i).setGame(this);
        }
    }


    public Game(String name, String developer, Genre genre, LocalDate releaseDate, List<Review> reviews) {
        this.name = name;
        this.developer = developer;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.reviews = reviews;
    }
}