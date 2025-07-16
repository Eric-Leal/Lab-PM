package br.lpm.business;

public class GameSession {
    private Player player;
    private Game game;
    private String result;
    private int scoreInMatch;

    public GameSession(Player player, Game game, String result, int scoreInMatch) {
        this.player = player;
        this.game = game;
        this.result = result;
        this.scoreInMatch = scoreInMatch;
    }

    public void recordScore() {
        int score = game.getScoringRule().calculateScore(result, scoreInMatch);
        player.getPlayerScore().addScore(game.getName(), score);
    }
}