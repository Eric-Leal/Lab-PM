package br.lpm.business;

import java.util.HashMap;
import java.util.Map;

public class PlayerScore {
    private Map<String, Integer> scoresByGame;
    
    public PlayerScore() {
        this.scoresByGame = new HashMap<>();
    }
    
    public int getScore(String gameName) {
        return scoresByGame.getOrDefault(gameName, 0);
    }
    
    public void addScore(String gameName, int score) {
        scoresByGame.put(gameName, getScore(gameName) + score);
    }
    
    public Map<String, Integer> getAllScores() {
        return new HashMap<>(scoresByGame);
    }
    
    public boolean hasPlayedGame(String gameName) {
        return scoresByGame.containsKey(gameName);
    }
    
    public int getTotalScore() {
        int total = 0;
        for (Integer score : scoresByGame.values()) {
            total += score;
        }
        return total;
    }
}