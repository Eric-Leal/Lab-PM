package br.lpm.business;

public abstract class Game{

    private ScoringRule scoringRule;

    
    public Game(ScoringRule scoringRule) {
        this.scoringRule = scoringRule;
    }

    public abstract String getName();

    public ScoringRule getScoringRule() {
        return scoringRule;
    }
}
