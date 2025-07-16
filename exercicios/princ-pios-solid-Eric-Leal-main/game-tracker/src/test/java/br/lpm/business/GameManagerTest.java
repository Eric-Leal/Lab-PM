package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GameManagerTest {
    
    private GameManager gameManager;
    private CardGame cardGame;
    private DiceGame diceGame;
    private FootballGame footballGame;
    
    @BeforeEach
    void setUp() {
        gameManager = new GameManager();
        cardGame = new CardGame(new CardGameScoringRule());
        diceGame = new DiceGame(6, new DiceGameScoringRule());
        footballGame = new FootballGame(new FootballGameScoringRule());
    }
    
    @Test
    void testAddGame() {
        gameManager.addGame(cardGame);
        gameManager.addGame(diceGame);
        
        List<Game> allGames = gameManager.getAllGames();
        assertEquals(2, allGames.size(), "O gerenciador deve conter 2 jogos");
        assertTrue(allGames.contains(cardGame), "A lista de jogos deve conter o jogo de cartas");
        assertTrue(allGames.contains(diceGame), "A lista de jogos deve conter o jogo de dados");
    }

    @Test
    void testGetGameByName() {
        gameManager.addGame(cardGame);
        gameManager.addGame(diceGame);
        gameManager.addGame(footballGame);
        
        Game retrievedCardGame = gameManager.getGameByName("Card Game");
        Game retrievedDiceGame = gameManager.getGameByName("Dice Game");
        Game retrievedFootballGame = gameManager.getGameByName("Football Game");
        
        assertSame(cardGame, retrievedCardGame, "Deve retornar o mesmo objeto de jogo de cartas");
        assertSame(diceGame, retrievedDiceGame, "Deve retornar o mesmo objeto de jogo de dados");
        assertSame(footballGame, retrievedFootballGame, "Deve retornar o mesmo objeto de jogo de futebol");
    }
    
    @Test
    void testGetNonExistingGame() {
        Game nonExistingGame = gameManager.getGameByName("Non-Existing Game");
        assertNull(nonExistingGame, "Deve retornar null para um nome de jogo que não existe");
    }
    
    @Test
    void testGetAllGames() {
        List<Game> emptyGamesList = gameManager.getAllGames();
        assertEquals(0, emptyGamesList.size(), "Inicialmente a lista de jogos deve estar vazia");
        
        gameManager.addGame(cardGame);
        gameManager.addGame(diceGame);
        gameManager.addGame(footballGame);
        
        List<Game> allGames = gameManager.getAllGames();
        assertEquals(3, allGames.size(), "Devem existir 3 jogos no gerenciador");
        assertTrue(allGames.contains(cardGame), "A lista deve conter o jogo de cartas");
        assertTrue(allGames.contains(diceGame), "A lista deve conter o jogo de dados");
        assertTrue(allGames.contains(footballGame), "A lista deve conter o jogo de futebol");
    }
}