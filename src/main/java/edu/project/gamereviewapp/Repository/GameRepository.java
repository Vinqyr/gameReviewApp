package edu.project.gamereviewapp.Repository;

import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Enum.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

    @Query(value =
            "SELECT *" +
            " from game" +
            " where genre=:genre",nativeQuery = true )
    Optional<List<Game>> findAllByGenre(@Param(value = "genre")Genre  genre);
}
