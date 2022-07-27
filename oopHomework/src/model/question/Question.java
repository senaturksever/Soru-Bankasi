package model.question;

import java.io.Serializable;

public abstract class Question implements Comparable<Question>, Serializable {

    private int score;
    private Level level;
    private String question;

    // Answer's type is object because it can be either A,B,C,D or (True,False) or just a basic string
    private Object answer;

    public Question(){}
    public Question(String question, Level level, int score){
        this.question = question;
        // this.answer = answer;
        this.level = level;
        this.score = score;
    }

    @Override
    public int compareTo(Question o) {
        /*
        * compare questions by scores. */
        return this.score - o.score;
    }

    // For getting question's turkish name representation
    // And basically to achieve polymorphism.
    public abstract String getQuestionType();


    public void setAnswer(Object answer) { this.answer = answer; }
    public String getQuestion() {
        return question;
    }
    public Level getLevel() {
        return level;
    }
    public int getScore() {
        return score;
    }
    public Object getAnswer() {
        return answer;
    }
}
