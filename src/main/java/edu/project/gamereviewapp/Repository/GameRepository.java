package edu.project.gamereviewapp.Repository;

import edu.project.gamereviewapp.Entity.Game;
import edu.project.gamereviewapp.Enum.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game,Long> {

    @Query(value =
            "SELECT id,name,developer,relese_date,genre" +
            " from game" +
            " where genre=:genre",nativeQuery = true )
    Optional<List<Game>> selectGamesByGenre(@Param("genre")Genre genre);
}
