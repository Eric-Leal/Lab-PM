package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class PlayerScoreTest {
    
    private PlayerScore playerScore;
    
    @BeforeEach
    void setUp() {
        playerScore = new PlayerScore();
    }
    
    @Test
    void testAddScore() {
        assertEquals(0, playerScore.getScore("Card Game"), "Score inicial deve ser 0");
        
        playerScore.addScore("Card Game", 5);
        assertEquals(5, playerScore.getScore("Card Game"), "Score deve ser atualizado após adicionar pontos");
        
        playerScore.addScore("Card Game", 3);
        assertEquals(8, playerScore.getScore("Card Game"), "Score deve ser acumulativo para o mesmo jogo");
        
        playerScore.addScore("Dice Game", 10);
        assertEquals(10, playerScore.getScore("Dice Game"), "Deve ser possível adicionar score para diferentes jogos");
        assertEquals(8, playerScore.getScore("Card Game"), "Score para jogos diferentes devem ser mantidos separadamente");
    }

    @Test
    void testHasPlayedGame() {
        assertFalse(playerScore.hasPlayedGame("Card Game"), "Inicialmente o jogador não participou de nenhum jogo");
        
        playerScore.addScore("Card Game", 1);
        assertTrue(playerScore.hasPlayedGame("Card Game"), "Após adicionar pontuação, hasPlayedGame deve retornar true");
        
        assertFalse(playerScore.hasPlayedGame("Dice Game"), "Deve retornar false para jogos em que o jogador não participou");
    }
    
    @Test
    void testGetAllScores() {
        playerScore.addScore("Card Game", 5);
        playerScore.addScore("Dice Game", 10);
        playerScore.addScore("Football Game", 3);
        
        Map<String, Integer> scores = playerScore.getAllScores();
        
        assertEquals(3, scores.size(), "Deve retornar mapa com 3 entradas");
        assertEquals(5, scores.get("Card Game"), "Card Game deve ter 5 pontos");
        assertEquals(10, scores.get("Dice Game"), "Dice Game deve ter 10 pontos");
        assertEquals(3, scores.get("Football Game"), "Football Game deve ter 3 pontos");
    }
    
    @Test
    void testGetAllScoresReturnsCopy() {
        playerScore.addScore("Card Game", 5);
        
        Map<String, Integer> scores = playerScore.getAllScores();
        
        scores.put("Dice Game", 10);
        
        assertFalse(playerScore.hasPlayedGame("Dice Game"), 
            "Modificar o mapa retornado não deve afetar os dados originais");
    }
    
    @Test
    void testGetTotalScore() {
        assertEquals(0, playerScore.getTotalScore(), "Pontuação total inicial deve ser 0");
        
        playerScore.addScore("Card Game", 5);
        assertEquals(5, playerScore.getTotalScore(), "Pontuação total deve ser atualizado após adicionar pontos");
        
        playerScore.addScore("Dice Game", 10);
        assertEquals(15, playerScore.getTotalScore(), "Pontuação total deve incluir pontos de todos os jogos");
        
        playerScore.addScore("Football Game", 3);
        assertEquals(18, playerScore.getTotalScore(), "Pontuação total deve incluir pontos de todos os jogos");
        
        playerScore.addScore("Card Game", 2);
        assertEquals(20, playerScore.getTotalScore(), "Pontuação total deve considerar pontos acumulados de jogos");
    }
    
    @Test
    void testGetScoreForNonExistentGame() {
        assertEquals(0, playerScore.getScore("Non Existent Game"), 
            "getScore deve retornar 0 para jogos que não foram jogados");
    }
}