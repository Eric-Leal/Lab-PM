package br.lpm.business;

public class CardGame extends Game {

    public CardGame(ScoringRule scoringRule) {
        super(scoringRule);
        
    }

    @Override
    public String getName() {
        return "Card Game";
    }


}