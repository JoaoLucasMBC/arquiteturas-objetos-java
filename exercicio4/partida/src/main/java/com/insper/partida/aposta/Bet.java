package com.insper.partida.aposta;


import com.insper.partida.game.Game;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BetStatus status;
    private BetResult result;


    @ManyToOne
    @JoinColumn(name = "id_partida")
    private Game game;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bet bet = (Bet) o;

        if (id != null ? !id.equals(bet.id) : bet.id != null) return false;
        return true;
    }

}
