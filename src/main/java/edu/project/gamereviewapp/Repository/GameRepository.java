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

    @Query("select g1_0 from Game g1_0 where g1_0.genre=:genre" )
    Optional<List<Game>> findAllByGenre(@Param(value = "genre")Genre   genre);

//    Optional<List<Game>> findAllByGenre(Genre genre);

}
