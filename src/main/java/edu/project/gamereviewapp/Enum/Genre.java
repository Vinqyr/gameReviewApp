package edu.project.gamereviewapp.Enum;


public enum Genre {
    ACTION("ACTION"),
    RPG("RPG"),
    STRATEGY("STRATEGY"),
    HORROR("HORROR");

    private String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public static Genre parseString(String genre){
        return Genre.valueOf(genre.toUpperCase());
    }
}
