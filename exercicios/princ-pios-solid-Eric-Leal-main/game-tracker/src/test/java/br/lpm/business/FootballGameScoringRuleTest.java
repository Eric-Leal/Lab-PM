package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FootballGameScoringRuleTest {
    
    private FootballGameScoringRule rules;
    
    @BeforeEach
    void setUp() {
        rules = new FootballGameScoringRule();
    }
    
    @Test
    void testCalculateScoreForWin() {
        int scoreInMatch = 10;
        String result = "win";
        int score = rules.calculateScore(result, scoreInMatch);
        assertEquals(3, score, "Uma vitória deve retornar 3 pontos");
        
        scoreInMatch = 5;
        score = rules.calculateScore(result, scoreInMatch);
        assertEquals(3, score, "Uma vitória deve retornar 3 pontos");
    }
    
    @Test
    void testCalculateScoreForDraw() {
        int scoreInMatch = 7;
        String result = "draw";
        int score = rules.calculateScore(result, scoreInMatch);
        assertEquals(1, score, "Um empate deve retornar 1 ponto");
        
        scoreInMatch = 3;
        score = rules.calculateScore(result, scoreInMatch);
        assertEquals(1, score, "Um empate deve retornar 1 ponto");
    }
    
    @Test
    void testCalculateScoreForLoss() {
        int scoreInMatch = 5;
        String result = "loss";
        int score = rules.calculateScore(result, scoreInMatch);
        assertEquals(0, score, "Uma derrota deve retornar 0 pontos");
    }
    
    @Test
    void testCalculateScoreCaseInsensitivity() {
        String upperCaseWin = "WIN";
        String mixedCaseDraw = "DrAw";
        String mixedCaseLoss = "LoSs";
        
        assertEquals(3, rules.calculateScore(upperCaseWin, 10), "Deve aceitar 'WIN' como vitória (case insensitive)");
        assertEquals(1, rules.calculateScore(mixedCaseDraw, 5), "Deve aceitar 'DrAw' como empate (case insensitive)");
        assertEquals(0, rules.calculateScore(mixedCaseLoss, 8), "Deve aceitar 'LoSs' como derrota (case insensitive)");
    }
    
    @Test
    void testMatchScoreIgnored() {
        int highScore = 100;
        int lowScore = 1;
        assertEquals(3, rules.calculateScore("win", highScore), "A pontuação da partida não deve afetar os pontos de vitória");
        assertEquals(3, rules.calculateScore("win", lowScore), "A pontuação da partida não deve afetar os pontos de vitória");
        assertEquals(1, rules.calculateScore("draw", highScore), "A pontuação da partida não deve afetar os pontos de empate");
        assertEquals(1, rules.calculateScore("draw", lowScore), "A pontuação da partida não deve afetar os pontos de empate");
        assertEquals(0, rules.calculateScore("loss", highScore), "A pontuação da partida não deve afetar os pontos de derrota");
        assertEquals(0, rules.calculateScore("loss", lowScore), "A pontuação da partida não deve afetar os pontos de derrota");
    }
    
    @Test
    void testThrowsExceptionForInvalidResults() {
        String invalidResult = "abc";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rules.calculateScore(invalidResult, 10);
        });
        assertTrue(exception.getMessage().contains(invalidResult), 
            "A mensagem da exceção deve conter o resultado inválido");
    }
}