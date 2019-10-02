package sample;

public class Score {
    private double score;
    private String meno;



    public Score(String meno, double score) {
        this.meno = meno;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    @Override
    public String toString() {
        return String.valueOf(meno+"|"+score);
    }
}