package model.question;

import java.util.Arrays;

public class MultipleChoice extends Question {

    // Multiple choice question type needs to implement some choices to user.
    // User has to pick one of them.
    // So - Object answer is an integer for this question type
    private String []choices;

    public MultipleChoice(String question, Level level, int score,
                          String ...choices) {
        super(question, level, score);
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "Soru: " +getQuestion()+
                "\nSeviye: " + (getLevel()==Level.EASY?"KOLAY":(getLevel() == Level.NORMAL?"ORTA":"ZOR"))+
                "\nPuan: " + getScore()+
                "\nSeçenekler: " + Arrays.toString(choices);
                //"\nCevap: " + choices[(int)getAnswer()];
    }

    public String[] getChoices() {
        return choices;
    }

    @Override
    public String getQuestionType() {
        return "Çoktan Seçmeli";
    }
}
