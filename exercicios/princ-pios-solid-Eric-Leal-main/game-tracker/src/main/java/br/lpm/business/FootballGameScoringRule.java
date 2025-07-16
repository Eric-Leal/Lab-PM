package br.lpm.business;

public class FootballGameScoringRule implements ScoringRule {

    @Override
    public int calculateScore(String result, int scoreInMatch) {
        String lowerResult = result.toLowerCase();
        
        if (lowerResult.equals("win")) {
            return 3;
        } else if (lowerResult.equals("draw")) {
            return 1;
        } else if (lowerResult.equals("loss")) {
            return 0;
        } else {
            throw new IllegalArgumentException("Invalid result: " + result);
        }
    }
    
}
