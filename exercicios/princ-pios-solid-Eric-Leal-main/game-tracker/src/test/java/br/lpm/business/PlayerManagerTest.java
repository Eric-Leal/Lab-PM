package br.lpm.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PlayerManagerTest {
    
    private PlayerManager playerManager;
    
    @BeforeEach
    void setUp() {
        playerManager = new PlayerManager();
    }
    
    @Test
    void testAddPlayer() {
        List<Player> initialPlayers = playerManager.getAllPlayers();
        assertEquals(0, initialPlayers.size(), "O gerenciador deve iniciar sem jogadores");
        
        Player newPlayer = new Player("João");
        playerManager.addPlayer(newPlayer);
        List<Player> playersAfterAdd = playerManager.getAllPlayers();
        
        assertEquals(1, playersAfterAdd.size(), "O gerenciador deve conter 1 jogador após adicionar");
        assertEquals("João", playersAfterAdd.get(0).getName(), "O nome do jogador adicionado deve ser 'João'");
    }
    
    @Test
    void testAddMultiplePlayers() {
        playerManager.addPlayer(new Player("Player 1"));
        playerManager.addPlayer(new Player("Player 2"));
        playerManager.addPlayer(new Player("Player 3"));
        
        List<Player> allPlayers = playerManager.getAllPlayers();
        assertEquals(3, allPlayers.size(), "O gerenciador deve conter 3 jogadores");
        
        boolean foundPlayer1 = false;
        boolean foundPlayer2 = false;
        boolean foundPlayer3 = false;
        
        for (Player player : allPlayers) {
            if ("Player 1".equals(player.getName())) foundPlayer1 = true;
            if ("Player 2".equals(player.getName())) foundPlayer2 = true;
            if ("Player 3".equals(player.getName())) foundPlayer3 = true;
        }
        
        assertTrue(foundPlayer1, "O jogador 'Player 1' deve estar na lista");
        assertTrue(foundPlayer2, "O jogador 'Player 2' deve estar na lista");
        assertTrue(foundPlayer3, "O jogador 'Player 3' deve estar na lista");
    }
    
    @Test
    void testGetPlayerById() {
        Player player1 = new Player("Renata");
        Player player2 = new Player("Mateus");
        Player player3 = new Player("Jorge");
        
        playerManager.addPlayer(player1);
        playerManager.addPlayer(player2);
        playerManager.addPlayer(player3);
        
        int firstPlayerId = player1.getId();
        int secondPlayerId = player2.getId();
        
        Player retrievedPlayer1 = playerManager.getPlayerById(firstPlayerId);
        Player retrievedPlayer2 = playerManager.getPlayerById(secondPlayerId);
        
        assertNotNull(retrievedPlayer1, "Deve retornar um jogador para ID existente");
        assertNotNull(retrievedPlayer2, "Deve retornar um jogador para ID existente");
        assertEquals("Renata", retrievedPlayer1.getName(), "O jogador recuperado deve ser 'Renata'");
        assertEquals("Mateus", retrievedPlayer2.getName(), "O jogador recuperado deve ser 'Mateus'");
    }
    
    @Test
    void testGetPlayerByNonExistentId() {
        playerManager.addPlayer(new Player("Renata"));
        playerManager.addPlayer(new Player("Mateus"));
        
        Player nonExistentPlayer = playerManager.getPlayerById(9999);
        assertNull(nonExistentPlayer, "Deve retornar null para um ID de jogador inexistente");
    }

    @Test
    void testPlayerIdsAreUnique() {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");
        
        playerManager.addPlayer(player1);
        playerManager.addPlayer(player2);
        playerManager.addPlayer(player3);
        
        int id1 = player1.getId();
        int id2 = player2.getId();
        int id3 = player3.getId();
        
        assertNotEquals(id1, id2, "IDs de jogadores devem ser únicos");
        assertNotEquals(id1, id3, "IDs de jogadores devem ser únicos");
        assertNotEquals(id2, id3, "IDs de jogadores devem ser únicos");
    }
}