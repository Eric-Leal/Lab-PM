package br.lpm.business;

public interface ScoringRule {
    int calculateScore(String matchResult, int scoreInMatch);
}
