package model.question;

import java.util.Arrays;

public class Classic extends Question {

    public Classic(){}
    public Classic(String question, Level level, int score) {
        super(question, level, score);
    }

    @Override
    public String getQuestionType() {
        return "Klasik";
    }

    @Override
    public String toString() {
        return "Soru: " +getQuestion()+
                "\nSeviye: " + (getLevel()==Level.EASY?"KOLAY":(getLevel() == Level.NORMAL?"ORTA":"ZOR"))+
                "\nPuan: " + getScore();
                //"\nCevap: " + getAnswer();
    }
}
