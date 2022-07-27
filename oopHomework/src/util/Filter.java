package util;

import model.question.Level;
import model.question.MultipleChoice;
import model.question.Question;

import java.util.ArrayList;
import java.util.List;

public class Filter {

    private List<Question> questions;

    public Filter(List<Question> questions){
        this.questions = questions;
    }

    public List<Question> filterByQuestion(String content){
        List<Question> filteredQuestions = new ArrayList<>();
        for(Question q : questions)
            if(q.getQuestion().toUpperCase().contains(content.toUpperCase()))
                filteredQuestions.add(q);

        return filteredQuestions;
    }
    /*
    Filter by choices content
    Filter by answers content
     */

    public List<Question> filterByQuestionTypeAndChoiceContents(String content){
        List<Question> filteredQuestions = new ArrayList<>();
        for(Question q: questions) {
            if(q instanceof MultipleChoice){
                String choices[] = ((MultipleChoice)q).getChoices();
                for(String c : choices) {
                    if(c.toUpperCase().contains(content.toUpperCase())) {filteredQuestions.add(q);break;}
                }
            }
        }

        return filteredQuestions;
    }

    public List<Question> filterByAnswer(Object answer){
        List<Question> filteredQuestions = new ArrayList<>();
        for(Question q : questions)
            if(q.getAnswer().toString().toUpperCase().contains(answer.toString().toUpperCase())) filteredQuestions.add(q);

        return filteredQuestions;
    }

    public List<Question> filterByScore(int score){
        List<Question> filteredQuestions = new ArrayList<>();
        for(Question q : questions)
            if(q.getScore() == score)
                filteredQuestions.add(q);

        return filteredQuestions;
    }

    public List<Question> filterByLevel(Level level){
        List<Question> filteredQuestion = new ArrayList<>();
        for(Question q : questions)
            if(q.getLevel() == level) filteredQuestion.add(q);

        return filteredQuestion;
    }

 }
