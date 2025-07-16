package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameSessionTest {
    
    private Player player;
    private CardGame cardGame;
    private DiceGame diceGame;
    private FootballGame footballGame;
    
    @BeforeEach
    void setUp() {
        player = new Player("Test Player");
        cardGame = new CardGame(new CardGameScoringRule());
        diceGame = new DiceGame(6, new DiceGameScoringRule());
        footballGame = new FootballGame(new FootballGameScoringRule());
    }
    
    @Test
    void testRecordScoreCardGameWin() {
        GameSession session = new GameSession(player, cardGame, "win", 10);
        session.recordScore();
        
        assertEquals(1, player.getPlayerScore().getScore(cardGame.getName()), 
            "Card Game vitória deve adicionar 1 ponto");
    }
    
    @Test
    void testRecordScoreCardGameLoss() {
        GameSession session = new GameSession(player, cardGame, "loss", 10);
        session.recordScore();
        
        assertEquals(0, player.getPlayerScore().getScore(cardGame.getName()), 
            "Card Game derrota deve adicionar 0 pontos");
    }
    
    @Test
    void testRecordScoreDiceGameWin() {
        int scoreInMatch = 8;
        GameSession session = new GameSession(player, diceGame, "win", scoreInMatch);
        session.recordScore();
        
        assertEquals(16, player.getPlayerScore().getScore(diceGame.getName()), 
            "Dice Game vitória deve dobrar a pontuação da partida");
    }
    
    @Test
    void testRecordScoreDiceGameDraw() {
        int scoreInMatch = 5;
        GameSession session = new GameSession(player, diceGame, "draw", scoreInMatch);
        session.recordScore();
        
        assertEquals(5, player.getPlayerScore().getScore(diceGame.getName()), 
            "Dice Game empate deve manter a pontuação da partida");
    }
    
    @Test
    void testRecordScoreDiceGameLoss() {
        GameSession session = new GameSession(player, diceGame, "loss", 10);
        session.recordScore();
        
        assertEquals(0, player.getPlayerScore().getScore(diceGame.getName()), 
            "Dice Game derrota deve adicionar 0 pontos");
    }
    
    @Test
    void testRecordScoreFootballGameWin() {
        GameSession session = new GameSession(player, footballGame, "win", 10);
        session.recordScore();
        
        assertEquals(3, player.getPlayerScore().getScore(footballGame.getName()), 
            "Football Game vitória deve adicionar 3 pontos");
    }
    
    @Test
    void testRecordScoreFootballGameDraw() {
        GameSession session = new GameSession(player, footballGame, "draw", 10);
        session.recordScore();
        
        assertEquals(1, player.getPlayerScore().getScore(footballGame.getName()), 
            "Football Game empate deve adicionar 1 ponto");
    }
    
    @Test
    void testRecordScoreFootballGameLoss() {
        GameSession session = new GameSession(player, footballGame, "loss", 10);
        session.recordScore();
        
        assertEquals(0, player.getPlayerScore().getScore(footballGame.getName()), 
            "Football Game derrota deve adicionar 0 pontos");
    }
    
    @Test
    void testMultipleGamesForSamePlayer() {
        new GameSession(player, cardGame, "win", 5).recordScore();
        new GameSession(player, diceGame, "win", 10).recordScore();
        new GameSession(player, footballGame, "draw", 2).recordScore();
        
        assertEquals(1, player.getPlayerScore().getScore(cardGame.getName()), 
            "Card Game deve ter 1 ponto");
        assertEquals(20, player.getPlayerScore().getScore(diceGame.getName()), 
            "Dice Game deve ter 20 pontos (10*2)");
        assertEquals(1, player.getPlayerScore().getScore(footballGame.getName()), 
            "Football Game deve ter 1 ponto");
        assertEquals(22, player.getPlayerScore().getTotalScore(), 
            "Pontuação total deve ser 22 (1+20+1)");
    }
    
    @Test
    void testMultipleSessionsForSameGame() {
        new GameSession(player, diceGame, "win", 5).recordScore();
        new GameSession(player, diceGame, "win", 10).recordScore();
        new GameSession(player, diceGame, "loss", 15).recordScore();
        
        assertEquals(30, player.getPlayerScore().getScore(diceGame.getName()), 
            "A pontuação total do Dice Game deve ser 30 (5*2 + 10*2 + 0)");
    }
    
    @Test
    void testCaseInsensitiveResults() {
        new GameSession(player, footballGame, "WIN", 5).recordScore();
        
        assertEquals(3, player.getPlayerScore().getScore(footballGame.getName()), 
            "Resultados devem ser case insensitive");
    }
}