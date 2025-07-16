package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class StatisticsReporterTest {

    private StatisticsReporter reporter;
    private PlayerManager playerManager;
    private RankingService rankingService;
    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    void setUp() {
        playerManager = new PlayerManager();
        rankingService = new RankingService(playerManager);
        reporter = new StatisticsReporter(rankingService);

        player1 = new Player("Alice");
        player2 = new Player("Bob");
        player3 = new Player("Charlie");

        playerManager.addPlayer(player1);
        playerManager.addPlayer(player2);
        playerManager.addPlayer(player3);

        List<Player> players = playerManager.getAllPlayers();

        players.get(0).getPlayerScore().addScore("Card Game", 3);
        players.get(0).getPlayerScore().addScore("Dice Game", 15);

        players.get(1).getPlayerScore().addScore("Card Game", 5);
        players.get(1).getPlayerScore().addScore("Football Game", 9);

        players.get(2).getPlayerScore().addScore("Dice Game", 20);
        players.get(2).getPlayerScore().addScore("Football Game", 6);
    }

    @Test
    void testDisplayPlayerStats() {
        List<Player> players = playerManager.getAllPlayers();
        int aliceId = players.get(0).getId();

        String stats = reporter.displayPlayerStats(aliceId);

        assertTrue(stats.contains("Alice"), "O relatório deve conter o nome do jogador");
        assertTrue(stats.contains("Card Game"), "O relatório deve mencionar Card Game");
        assertTrue(stats.contains("Dice Game"), "O relatório deve mencionar Dice Game");
        assertTrue(stats.contains("18"), "O relatório deve mostrar o total correto de pontos");
    }

    @Test
    void testDisplayRankingsForCardGame() {
        String cardGameRankings = reporter.displayRankings("Card Game");

        assertTrue(cardGameRankings.contains("Card Game"),
                "O relatório de ranking deve mencionar Card Game");
        assertTrue(cardGameRankings.contains("Alice"),
                "O relatório deve incluir Alice");
        assertTrue(cardGameRankings.contains("Bob"),
                "O relatório deve incluir Bob");
    }

    @Test
    void testDisplayRankingsForDiceGame() {
        String diceGameRankings = reporter.displayRankings("Dice Game");

        assertTrue(diceGameRankings.contains("Dice Game"),
                "O relatório de ranking deve mencionar Dice Game");
        assertTrue(diceGameRankings.contains("Alice"),
                "O relatório deve incluir Alice");
        assertTrue(diceGameRankings.contains("Charlie"),
                "O relatório deve incluir Charlie");
    }

    @Test
    void testSetRankingService() {
        RankingService newService = new RankingService(new PlayerManager());

        reporter.setRankingService(newService);

        String stats = reporter.displayPlayerStats(999);
        assertEquals("Player not found", stats,
                "Deve retornar mensagem apropriada para jogador inexistente");
    }

    @Test
    void testNonExistentPlayer() {
        String nonExistentPlayerStats = reporter.displayPlayerStats(999);
        assertEquals("Player not found", nonExistentPlayerStats,
                "Deve retornar mensagem apropriada para jogador inexistente");
    }

}