package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class RankingServiceTest {
    
    private RankingService rankingService;
    private PlayerManager playerManager;
    private Player player1, player2, player3;
    
    @BeforeEach
    void setUp() {
        playerManager = new PlayerManager();
        rankingService = new RankingService(playerManager);
        
        player1 = new Player("Alice");
        player2 = new Player("Bob");
        player3 = new Player("Charlie");
        
        playerManager.addPlayer(player1);
        playerManager.addPlayer(player2);
        playerManager.addPlayer(player3);
        
        List<Player> players = playerManager.getAllPlayers();
        player1 = players.get(0); 
        player2 = players.get(1); 
        player3 = players.get(2); 
        
        player1.getPlayerScore().addScore("Card Game", 5);
        player1.getPlayerScore().addScore("Dice Game", 15);
        
        player2.getPlayerScore().addScore("Card Game", 10);
        player2.getPlayerScore().addScore("Football Game", 3);
        
        player3.getPlayerScore().addScore("Dice Game", 20);
        player3.getPlayerScore().addScore("Football Game", 9);
    }
    
    @Test
    void testGetRankedPlayersByGame() {
        List<Player> cardGameRanking = rankingService.getRankedPlayersByGame("Card Game");
        assertEquals(2, cardGameRanking.size(), "Devem existir 2 jogadores com pontuação em Card Game");

        List<Player> diceGameRanking = rankingService.getRankedPlayersByGame("Dice Game");
        assertEquals(2, diceGameRanking.size(), "Devem existir 2 jogadores com pontuação em Dice Game");
        
        List<Player> footballGameRanking = rankingService.getRankedPlayersByGame("Football Game");
        assertEquals(2, footballGameRanking.size(), "Devem existir 2 jogadores com pontuação em Football Game");
    }
    
    @Test
    void testGetRankedPlayersByGameEmptyResult() {
        List<Player> emptyRanking = rankingService.getRankedPlayersByGame("Another Game");
        assertEquals(0, emptyRanking.size(), "Não deve haver jogadores para um jogo não jogado");
    }
    
    @Test
    void testGetPlayerScores() {
        Map<String, Integer> aliceScores = rankingService.getPlayerScores(player1);
        assertEquals(2, aliceScores.size(), "Alice deve ter pontuação em 2 jogos");
        assertEquals(5, aliceScores.get("Card Game"), "Alice deve ter 5 pontos em Card Game");
        assertEquals(15, aliceScores.get("Dice Game"), "Alice deve ter 15 pontos em Dice Game");
        
        Player emptyPlayer = new Player("Empty");
        Map<String, Integer> emptyScores = rankingService.getPlayerScores(emptyPlayer);
        assertEquals(0, emptyScores.size(), "Jogador sem pontuações deve retornar mapa vazio");
    }
    
    @Test
    void testGetTotalScore() {
        int aliceTotal = rankingService.getTotalScore(player1);
        int bobTotal = rankingService.getTotalScore(player2);
        int charlieTotal = rankingService.getTotalScore(player3);
        
        assertEquals(20, aliceTotal, "Alice deve ter 20 pontos no total (5 + 15)");
        assertEquals(13, bobTotal, "Bob deve ter 13 pontos no total (10 + 3)");
        assertEquals(29, charlieTotal, "Charlie deve ter 29 pontos no total (20 + 9)");
    }
    
}