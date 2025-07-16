package br.lpm.business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DiceGameTest {
    
    private DiceGameScoringRule scoringRule;
    
    @BeforeEach
    void setUp() {
        scoringRule = new DiceGameScoringRule();
    }
    
    @Test
    void testConstructorValidDiceSize() {
        DiceGame d4 = new DiceGame(4, scoringRule);
        assertEquals(4, d4.getDiceSize(), "diceSize deve ser 4");
        
        DiceGame d6 = new DiceGame(6, scoringRule);
        assertEquals(6, d6.getDiceSize(), "diceSize deve ser 6");
    }
    
    @Test
    void testConstructorInvalidDiceSize() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DiceGame(21, scoringRule);
        });
        
        assertTrue(exception.getMessage().contains("d20"), 
            "Mensagem de erro deve mencionar o limite d20");
    }
    
    @Test
    void testSetDiceSize() {
        DiceGame diceGame = new DiceGame(6, scoringRule);
        
        diceGame.setDiceSize(10);
        assertEquals(10, diceGame.getDiceSize(), "diceSize deve ser atualizado para 10");
        
        diceGame.setDiceSize(20);
        assertEquals(20, diceGame.getDiceSize(), "diceSize deve ser atualizado para 20");
    }
    
    @Test
    void testSetDiceSizeInvalid() {
        DiceGame diceGame = new DiceGame(6, scoringRule);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            diceGame.setDiceSize(21);
        });
        
        assertTrue(exception.getMessage().contains("d20"), 
            "Mensagem de erro deve mencionar o limite d20");
        
        assertEquals(6, diceGame.getDiceSize(), "diceSize não deve ser alterado após falha");
    }

}