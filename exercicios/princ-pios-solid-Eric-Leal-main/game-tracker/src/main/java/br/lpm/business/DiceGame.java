package br.lpm.business;

public class DiceGame extends Game {
    private int diceSize;

    public DiceGame(int diceSize, ScoringRule scoringRule) {
        super(scoringRule);
        setDiceSize(diceSize);
    }

    public void setDiceSize(int diceSize) {
        if (diceSize <= 20) {
            this.diceSize = diceSize;
        } else {
            throw new IllegalArgumentException("Dice cannot be larger than a d20");
        }
    }
    public int getDiceSize() {
        return diceSize;
    }

    @Override
    public String getName() {
        return "Dice Game";
    }

}
