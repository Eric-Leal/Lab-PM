package br.lpm.business;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Game> games = new ArrayList<>();
    
    public void addGame(Game game) {
        if(!games.contains(game) && game != null) {
            games.add(game);
        }
    }
    
    public Game getGameByName(String name) {
        for (Game game : games) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        return null;
    }
    
    public List<Game> getAllGames() {
        return new ArrayList<>(games);
    }
}