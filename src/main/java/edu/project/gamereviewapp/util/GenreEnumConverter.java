package edu.project.gamereviewapp.util;


import edu.project.gamereviewapp.Enum.Genre;
import org.springframework.core.convert.converter.*;
import org.springframework.stereotype.Component;

@Component
public class GenreEnumConverter implements Converter<String , Genre> {


    @Override
    public Genre convert(String source) {
        try{
        return Genre.valueOf(source);
    }catch (Exception e){
            return null;
        }
    }

}
