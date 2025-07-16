package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardGameScoringRuleTest {
    
    private CardGameScoringRule rules;
    
    @BeforeEach
    void setUp() {
        rules = new CardGameScoringRule();
    }
    
    @Test
    void testCalculateScoreForWin() {
        int scoreInMatch = 10;
        String result = "win";
        int score = rules.calculateScore(result, scoreInMatch);
        assertEquals(1, score, "Uma vitória deve retornar 1 ponto independentemente da pontuação da partida");
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
        String mixedCaseLoss = "LoSs";
        assertEquals(1, rules.calculateScore(upperCaseWin, 0), "Deve aceitar 'WIN' como vitória (case insensitive)");
        assertEquals(0, rules.calculateScore(mixedCaseLoss, 0), "Deve aceitar 'LoSs' como derrota (case insensitive)");
    }
    
    @Test
    void testMatchScoreIgnored() {
        int highScore = 100;
        int lowScore = 1;
        assertEquals(1, rules.calculateScore("win", highScore), "A pontuação da partida não deve afetar os pontos de vitória");
        assertEquals(1, rules.calculateScore("win", lowScore), "A pontuação da partida não deve afetar os pontos de vitória");
        assertEquals(0, rules.calculateScore("loss", highScore), "A pontuação da partida não deve afetar os pontos de derrota");
        assertEquals(0, rules.calculateScore("loss", lowScore), "A pontuação da partida não deve afetar os pontos de derrota");
    }
    
    @Test
    void testThrowsExceptionForInvalidResults() {
        String invalidResult = "draw";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rules.calculateScore(invalidResult, 10);
        });
        assertTrue(exception.getMessage().contains(invalidResult), 
            "A mensagem da exceção deve conter o resultado inválido");
    }
}
