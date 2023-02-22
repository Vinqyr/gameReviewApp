package edu.project.gamereviewapp.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GameServiceTest {

    private GameService testGameService;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp(){
        testGameService = new GameService()
    }

    @Test
    void findAll() {
        //Given

        //When

        //Then
    }

    @Test
    void findById() {
        //Given

        //When

        //Then
    }

    @Test
    void save() {
        //Given

        //When

        //Then
    }
}