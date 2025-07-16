package br.lpm.business;

public class DiceGameScoringRule implements ScoringRule{
    
    
    @Override
    public int calculateScore(String result, int scoreInMatch) {
        if (scoreInMatch <= 0) {
            throw new IllegalArgumentException("scoreInMatch must be greater than 0");
        }
        if ("win".equalsIgnoreCase(result)) {
            return scoreInMatch * 2;
        } else if ("draw".equalsIgnoreCase(result)) {
            return scoreInMatch;
        } else if ("loss".equalsIgnoreCase(result)) {    
            return 0;
        } else {
            throw new IllegalArgumentException("Invalid result: " + result);
        }
    }
    
}
