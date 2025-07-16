package br.lpm.business;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private List<Player> players = new ArrayList<>();
    
    public void addPlayer(Player player) {
        if(!players.contains(player) && player != null) {
            players.add(player);
        }
    }
    
    public Player getPlayerById(int id) {
        for (Player player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }
    
    public List<Player> getAllPlayers() {
        return new ArrayList<>(players);
    }
}