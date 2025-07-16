package br.lpm.business;

public class CardGameScoringRule implements ScoringRule {
    
    @Override
    public int calculateScore(String result, int scoreInMatch) {
        if ("win".equalsIgnoreCase(result)) {
            return 1;
        } else if ("loss".equalsIgnoreCase(result)) {
            return 0;
        } else {
            throw new IllegalArgumentException("Invalid result: " + result);
        }
    }
}
