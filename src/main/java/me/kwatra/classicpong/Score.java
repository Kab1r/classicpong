package me.kwatra.classicpong;

public class Score {
    private int score0;
    private int score1;

    public Score() {
        score0 = 0;
        score1 = 0;
    }

    public Score(int score0, int score1) {
        this.score0 = score0;
        this.score1 = score1;
    }

    public int incrementScore0() {
        return ++this.score0;
    }

    public int incrementScore1() {
        return ++this.score1;
    }

    public int getScore0() {
        return score0;
    }

    public int getScore1() {
        return score1;
    }

}