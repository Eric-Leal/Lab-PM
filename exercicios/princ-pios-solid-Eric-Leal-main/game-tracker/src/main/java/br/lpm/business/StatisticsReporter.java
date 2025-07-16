package br.lpm.business;

import java.util.List;
import java.util.Map;

public class StatisticsReporter {
    
    private RankingService rankingService;

    public StatisticsReporter(RankingService rankingService) {
        setRankingService(rankingService);
    }

    public void setRankingService(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    public String displayRankings(String gameName) {
        StringBuilder rankings = new StringBuilder("---------------------\n");
        rankings.append("Rankings for ").append(gameName).append(":\n");

        List<Player> rankedPlayers = rankingService.getRankedPlayersByGame(gameName);
        for (Player player : rankedPlayers) {
            rankings.append("ID: ")
                    .append(player.getId())
                    .append(", Name: ")
                    .append(player.getName())
                    .append(", Score: ")
                    .append(player.getPlayerScore().getScore(gameName))
                    .append(" points\n");
        }

        return rankings.toString();
    }

    public String displayPlayerStats(int playerId) {
        Player player = rankingService.getPlayerById(playerId);
        if (player == null) {
            return "Player not found";
        }

        StringBuilder stats = new StringBuilder("---------------------\n");
        stats.append("Estatísticas do jogador: ")
             .append(player.getName())
             .append(" (ID: ").append(player.getId()).append(")\n");

        Map<String, Integer> scores = rankingService.getPlayerScores(player);
        if (scores.isEmpty()) {
            stats.append("Este jogador ainda não participou de nenhum jogo.\n");
        } else {
            stats.append("Jogos participados:\n");
            scores.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .forEach(entry -> stats.append("- ")
                                       .append(entry.getKey())
                                       .append(": ")
                                       .append(entry.getValue())
                                       .append(" pontos\n"));
            stats.append("Total de pontos em todos os jogos: ")
                 .append(rankingService.getTotalScore(player))
                 .append("\n");
        }

        return stats.toString();
    }
}
