package edu.project.gamereviewapp.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.project.gamereviewapp.DTO.GameRequestDto;
import edu.project.gamereviewapp.Enum.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Should return DTO of saved entity")
    @Sql(value = {"/sql/set_up_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void saveThenReturnSavedDTO() throws Exception {
        //given
        GameRequestDto requestBody = GameRequestDto.builder()
                .id(null)
                .name("DOS2")
                .developer("LARIAN")
                .genre(Genre.HORROR)
                .releaseDate(LocalDate.now())
                .reviews(new ArrayList<>())
                .build();
        //when then
        mockMvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestBody)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(4)))
                .andExpect(jsonPath("$.name", equalTo("DOS2")))
                .andExpect(jsonPath("$.developer", equalTo("LARIAN")))
                .andExpect(jsonPath("$.genre", equalTo(Genre.HORROR.getValue())))
                .andExpect(jsonPath("$.releaseDate", equalTo(LocalDate.now().toString())))
                .andExpect(jsonPath("$.reviews", equalTo(new ArrayList())));
    }

    @Test
    @DisplayName("Should return list of DTOs with the size 3")
    @Sql(value = {"/sql/set_up_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findAllThenReturnListOfDTO() throws Exception {
        //when then
        mockMvc.perform(get("/games"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[1].id", equalTo(2)))
                .andExpect(jsonPath("$[2].id", equalTo(3)));
    }

    @Test
    @DisplayName("Should return DTO of found entity")
    @Sql(value = {"/sql/set_up_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findByValidIdThenReturnGameResponseDTO() throws Exception {
        //when then
        mockMvc.perform(get("/games/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("DOS2")))
                .andExpect(jsonPath("$.developer", equalTo("LARIAN")))
                .andExpect(jsonPath("$.genre", equalTo(Genre.HORROR.getValue())))
                .andExpect(jsonPath("$.releaseDate", equalTo(LocalDate.of(2017,9,17).toString())))
                .andExpect(jsonPath("$.reviews", equalTo(new ArrayList())));
    }

    @Test
    @DisplayName("Should throw not found exception")
    @Sql(value = {"/sql/set_up_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findByInvalidIdThenThrow() throws Exception {
        //given
        Long nonExistentId = 4L;
        //when then
        mockMvc.perform(get("/games/{id}", nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("Unable to find game by id :: " + nonExistentId)));
    }

    @Test
    @DisplayName("Should delete entity and return deleted DTO")
    @Sql(value = {"/sql/set_up_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void deleteWhenIdIsValidThenReturnDeletedGameResponseDTO() throws Exception {
        //when then
        mockMvc.perform(delete("/games/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("DOS2")))
                .andExpect(jsonPath("$.developer", equalTo("LARIAN")))
                .andExpect(jsonPath("$.genre", equalTo(Genre.HORROR.getValue())))
                .andExpect(jsonPath("$.releaseDate", equalTo(LocalDate.now().toString())))
                .andExpect(jsonPath("$.reviews", equalTo(new ArrayList())));
    }

    @Test
    @DisplayName("Should throw not found exception")
    @Sql(value = {"/sql/set_up_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void deleteWhenIdInvalidThenThrow() throws Exception {
        //given
        Long nonExistentId = 4L;
        //when then
        mockMvc.perform(delete("/games/{id}", nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("Unable to find game by id :: " + nonExistentId)));
    }

    @Test
    @DisplayName("Should update entity and return updated DTO")
    @Sql(value = {"/sql/set_up_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void updateExistingEntityThenReturnUpdatedMatchDTO() throws Exception {
        //given
        GameRequestDto requestBody = GameRequestDto.builder()
                .id(null)
                .name("Portal 3")
                .developer("VOLVO")
                .genre(Genre.MOBA)
                .releaseDate(LocalDate.of(2011,4,18))
                .reviews(new ArrayList<>())
                .build();
        //when then
        mockMvc.perform(put("/games/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestBody)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.name", equalTo("Portal 3")))
                .andExpect(jsonPath("$.developer", equalTo("VOLVO")))
                .andExpect(jsonPath("$.genre", equalTo(Genre.MOBA.getValue())))
                .andExpect(jsonPath("$.releaseDate", equalTo(LocalDate.of(2011,4,18).toString())))
                .andExpect(jsonPath("$.reviews", equalTo(new ArrayList())));
    }


}