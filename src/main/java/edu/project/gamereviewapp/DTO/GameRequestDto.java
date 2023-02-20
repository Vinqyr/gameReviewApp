package edu.project.gamereviewapp.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.project.gamereviewapp.Entity.Review;
import edu.project.gamereviewapp.Enum.Genre;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
@Getter
public class GameRequestDto {
    private Long id;
    private String gameName;
    private String developer;
    private Genre genre;
    @JsonSerialize(as = LocalDate.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private List<Review> reviews;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameRequestDto that = (GameRequestDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (gameName != null ? !gameName.equals(that.gameName) : that.gameName != null) return false;
        if (developer != null ? !developer.equals(that.developer) : that.developer != null) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;
        if (releaseDate != null ? !releaseDate.equals(that.releaseDate) : that.releaseDate != null) return false;
        return reviews != null ? reviews.equals(that.reviews) : that.reviews == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (gameName != null ? gameName.hashCode() : 0);
        result = 31 * result + (developer != null ? developer.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        return result;
    }
}
