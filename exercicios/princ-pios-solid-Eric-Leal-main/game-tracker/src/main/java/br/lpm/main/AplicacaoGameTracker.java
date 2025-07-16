package br.lpm.main;

import br.lpm.business.*;

public class AplicacaoGameTracker {
    public static void main(String[] args) {
        PlayerManager playerManager = new PlayerManager();
        GameManager gameManager = new GameManager();
        

        RankingService rankingService = new RankingService(playerManager);
        
        StatisticsReporter reporter = new StatisticsReporter(rankingService);

        // Adicionar jogadores

        Player rebeca = new Player("Rebeca");
        Player jorge = new Player("Jorge");
        Player renato = new Player("Renato");

        playerManager.addPlayer(rebeca);
        playerManager.addPlayer(jorge);
        playerManager.addPlayer(renato);

        // Adicionar jogos
        Game football = new FootballGame(new FootballGameScoringRule());
        Game cardGame = new CardGame(new CardGameScoringRule());
        Game diceGame = new DiceGame(10, new DiceGameScoringRule());

        gameManager.addGame(football);
        gameManager.addGame(cardGame);
        gameManager.addGame(diceGame);
        
        rebeca = playerManager.getPlayerById(1);
        jorge = playerManager.getPlayerById(2);   

        // Criando seções de jogos
        new GameSession(rebeca, diceGame, "win", 3).recordScore();
        new GameSession(jorge, diceGame, "Draw", 9).recordScore();
        new GameSession(rebeca, diceGame, "loss", 2).recordScore();
        new GameSession(rebeca, diceGame, "win", 4).recordScore();

        new GameSession(rebeca, football, "win", 3).recordScore();
        new GameSession(jorge, football, "draw", 2).recordScore();
        new GameSession(jorge, football, "loss", 1).recordScore();

        new GameSession(rebeca, cardGame, "win", 3).recordScore();
        new GameSession(jorge, cardGame, "loss", 5).recordScore();
        new GameSession(rebeca, cardGame, "win", 3).recordScore();
        
        // Exibir rankings por jogo
        System.out.println(reporter.displayRankings("Football Game"));
        System.out.println(reporter.displayRankings("Card Game"));
        System.out.println(reporter.displayRankings("Dice Game"));

        System.out.println(reporter.displayPlayerStats(1));
        System.out.println(reporter.displayPlayerStats(2));

        //Mostrando estatísticas de um jogador que não jogou nenhum jogo
        System.out.println(reporter.displayPlayerStats(3));
    }
}