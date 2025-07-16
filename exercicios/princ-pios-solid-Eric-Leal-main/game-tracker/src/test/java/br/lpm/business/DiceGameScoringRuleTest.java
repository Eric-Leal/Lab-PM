package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiceGameScoringRuleTest {
    
    private DiceGameScoringRule rules;
    
    @BeforeEach
    void setUp() {
        rules = new DiceGameScoringRule();
    }
    
    @Test
    void testCalculateScoreForWin() {
        int scoreInMatch = 10;
        String result = "win";
        int score = rules.calculateScore(result, scoreInMatch);
        assertEquals(20, score, "Uma vitória deve retornar o dobro da pontuação da partida");
        
        scoreInMatch = 5;
        score = rules.calculateScore(result, scoreInMatch);
        assertEquals(10, score, "Uma vitória deve retornar o dobro da pontuação da partida");
    }
    
    @Test
    void testCalculateScoreForDraw() {
        int scoreInMatch = 7;
        String result = "draw";
        int score = rules.calculateScore(result, scoreInMatch);
        assertEquals(7, score, "Um empate deve retornar a mesma pontuação da partida");
        
        scoreInMatch = 3;
        score = rules.calculateScore(result, scoreInMatch);
        assertEquals(3, score, "Um empate deve retornar a mesma pontuação da partida");
    }
    
    @Test
    void testCalculateScoreForLoss() {
        int scoreInMatch = 5;
        String result = "loss";
        int score = rules.calculateScore(result, scoreInMatch);
        assertEquals(0, score, "Uma derrota deve retornar 0 pontos");
        
        scoreInMatch = 15;
        score = rules.calculateScore(result, scoreInMatch);
        assertEquals(0, score, "Uma derrota deve retornar 0 pontos");
    }
    
    @Test
    void testCalculateScoreCaseInsensitivity() {
        String upperCaseWin = "WIN";
        String mixedCaseDraw = "DrAw";
        String mixedCaseLoss = "LoSs";
        
        assertEquals(20, rules.calculateScore(upperCaseWin, 10), "Deve aceitar 'WIN' como vitória (case insensitive)");
        assertEquals(5, rules.calculateScore(mixedCaseDraw, 5), "Deve aceitar 'DrAw' como empate (case insensitive)");
        assertEquals(0, rules.calculateScore(mixedCaseLoss, 8), "Deve aceitar 'LoSs' como derrota (case insensitive)");
    }
    
    @Test
    void testThrowsExceptionForInvalidResults() {
        String invalidResult = "tie";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rules.calculateScore(invalidResult, 10);
        });
        assertTrue(exception.getMessage().contains(invalidResult), 
            "A mensagem da exceção deve conter o resultado inválido");
    }
    
    @Test
    void testThrowsExceptionForNonPositiveScore() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rules.calculateScore("win", 0);
        });
        assertTrue(exception.getMessage().contains("scoreInMatch must be greater than 0"), 
            "Deve lançar exceção para pontuação menor ou igual a zero");
        
        exception = assertThrows(IllegalArgumentException.class, () -> {
            rules.calculateScore("win", -5);
        });
        assertTrue(exception.getMessage().contains("scoreInMatch must be greater than 0"), 
            "Deve lançar exceção para pontuação menor ou igual a zero");
    }
}