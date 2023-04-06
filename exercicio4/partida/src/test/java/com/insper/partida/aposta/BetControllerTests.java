package com.insper.partida.aposta;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insper.partida.equipe.Team;
import com.insper.partida.game.Game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BetControllerTests {

    MockMvc mockMvc;

    @InjectMocks
    BetController betController;

    @Mock
    BetService betService;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(betController)
                .build();
    }

    @Test
    void test_listBets() throws Exception {

        Bet bet = new Bet();
        bet.setId(1);
        bet.setResult(BetResult.HOME);
        bet.setStatus(BetStatus.WON);

        List<Bet> bets = new ArrayList<>();
        bets.add(bet);

        Mockito.when(betService.listBets()).thenReturn(bets);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/bet"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        ObjectMapper om = new ObjectMapper();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(om.writeValueAsString(bets), resp);

    }

    @Test
    void test_createBets() throws Exception {

        Bet bet = new Bet();
        bet.setId(1);
        bet.setResult(BetResult.HOME);
        
        Mockito.when(betService.saveBet(bet)).thenReturn(bet);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/bet")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(bet)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(new ObjectMapper().writeValueAsString(bet), resp);

    }

    @Test
    void test_verifyBets() throws Exception {

        Bet bet = new Bet();
        bet.setId(1);
        bet.setResult(BetResult.HOME);
        
        Game game = new Game();
        game.setId(1);
        game.setHome(new Team());
        game.setAway(new Team());
        game.setScoreHome(1);
        game.setScoreAway(0);

        bet.setGame(game);
        bet.setStatus(BetStatus.WON);

        Mockito.when(betService.verifyBet(bet.getId())).thenReturn(bet);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/bet/1/verify")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(bet)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        
        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals(new ObjectMapper().writeValueAsString(bet), resp);

    }



}
