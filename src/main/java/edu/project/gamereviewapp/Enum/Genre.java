package edu.project.gamereviewapp.Enum;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Genre  {
    ACTION("action"),
    RPG("rpg"),
    STRATEGY("strategy"),
    HORROR("horror"),
    MOBA("moba");

    private final String value;

    Genre(String value) {
        this.value = value;
    }



    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Genre fromValue(String value){
        for(Genre genre: Genre.values()){
            if (genre.value.equals(value.toLowerCase()))
                return genre;
        }
        throw new RuntimeException("KEPKA");
    }

}
