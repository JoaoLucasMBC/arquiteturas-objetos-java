package com.insper.partida.equipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insper.partida.game.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identifier;

    private String name;

    private String stadium;

    @JsonIgnore
    @OneToMany(mappedBy = "away")
    private List<Game> away;

    @JsonIgnore
    @OneToMany(mappedBy = "home")
    private List<Game> home;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != null ? !id.equals(team.id) : team.id != null) return false;
        if (identifier != null ? !identifier.equals(team.identifier) : team.identifier != null) return false;
        return true;
    }
}
