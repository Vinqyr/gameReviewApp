package edu.project.gamereviewapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Account {
    @Id
    private Long id;

    private String name;

    private String lastName;


    public Account() {

    }
}
