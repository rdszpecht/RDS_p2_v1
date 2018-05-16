package ReadAndSort;

class Score{
    private String name;
    private int score;

    public Score() {
    }

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Score other){
        return Integer.compare(this.score, other.score);
    }
}