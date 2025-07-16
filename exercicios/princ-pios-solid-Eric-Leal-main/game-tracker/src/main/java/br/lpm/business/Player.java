package br.lpm.business;

public class Player {
    private static int idCounter = 1;
    private final int id;
    private String name;
    private PlayerScore playerScore;

    public Player(String name) {
        this.id = idCounter++;
        this.name = name;
        this.playerScore = new PlayerScore();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public PlayerScore getPlayerScore() {
        return playerScore;
    }

    @Override
    public String toString() {
        return "Player: " + 
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + playerScore.getAllScores();
    }
}