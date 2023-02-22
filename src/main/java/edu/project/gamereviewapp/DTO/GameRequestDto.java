package edu.project.gamereviewapp.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.project.gamereviewapp.Entity.Review;
import edu.project.gamereviewapp.Enum.Genre;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Builder
public class GameRequestDto {
    private Long id;
    private String name;
    private String developer;
    private Genre genre;
    @JsonSerialize(as = LocalDate.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private List<Review> reviews;


}
