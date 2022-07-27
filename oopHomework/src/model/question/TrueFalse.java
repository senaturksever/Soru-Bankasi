package model.question;

import java.util.Arrays;

public class TrueFalse extends Question {

    public TrueFalse(){}
    public TrueFalse(String question, Level level, int score) {
        // Object answer is - boolean for this type of question
        // True or False
        super(question, level, score);
    }

    @Override
    public String getQuestionType() {
        return "Doğru/Yanlış";
    }

    @Override
    public String toString() {
        return "Soru: " +getQuestion()+
                "\nSeviye: " + (getLevel()==Level.EASY?"KOLAY":(getLevel() == Level.NORMAL?"ORTA":"ZOR"))+
                "\nPuan: " + getScore();
                //"\nCevap: " + ((boolean)getAnswer()?"Doğru":"Yanlış");
    }
}
