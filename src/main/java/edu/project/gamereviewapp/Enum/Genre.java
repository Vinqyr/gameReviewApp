package edu.project.gamereviewapp.Enum;


import com.fasterxml.jackson.annotation.JsonValue;
import edu.project.gamereviewapp.util.AbstractEnumConverter;
import edu.project.gamereviewapp.util.PersistableEnum;

public enum Genre implements PersistableEnum<String> {
    ACTION("action"),
    RPG("rpg"),
    STRATEGY("strategy"),
    HORROR("horror");

    private final String value;

    private Genre(String value) {
        this.value = value;
    }

    public static Genre parseString(String genre){
        return Genre.valueOf(genre.toUpperCase());
    }
    @JsonValue
    public String getGenre(){
        return value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static class Converter extends AbstractEnumConverter<Genre, String> {
        public Converter() {
            super(Genre.class);
        }
    }
}
