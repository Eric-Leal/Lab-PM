package br.lpm.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RankingService {

    private PlayerManager playerManager;

    public RankingService(PlayerManager playerManager) {
        setPlayerManager(playerManager);
    }

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public List<Player> getRankedPlayersByGame(String gameName) {
        List<Player> filteredPlayers = new ArrayList<>();
        for (Player player : playerManager.getAllPlayers()) {
            if (player.getPlayerScore().hasPlayedGame(gameName)) {
                filteredPlayers.add(player);
            }
        }

        filteredPlayers.sort(Comparator.comparingInt(
            p -> -p.getPlayerScore().getScore(gameName)
        ));
        return filteredPlayers;
    }

    public Player getPlayerById(int id) {
        return playerManager.getPlayerById(id);
    }

    public Map<String, Integer> getPlayerScores(Player player) {
        return player.getPlayerScore().getAllScores();
    }

    public int getTotalScore(Player player) {
        return player.getPlayerScore().getTotalScore();
    }
}
