package br.lpm.business;

public class FootballGame extends Game {

    public FootballGame(ScoringRule scoringRule) {
        super(scoringRule);
    }
  
    @Override
    public String getName() {
        return "Football Game";
    }

}