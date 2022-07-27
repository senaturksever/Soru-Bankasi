package model.exam;

import model.question.MultipleChoice;
import model.question.Question;

import java.util.*;

public class TestExam extends Exam {

    public TestExam(String title) {
        super(title);
        setExamType(ExamType.TEST);
    }

    @Override
    public Exam createExam(List<Question> questionList) {
        int check = 0;
        List<Question> mChoiceQuestionsList = new ArrayList<>();
        // Check if there is enough questions exist to create exam.
        for(Question q: questionList) {
            if(q instanceof MultipleChoice){
                check+=q.getScore();
                mChoiceQuestionsList.add(q);
            }
        }

        // If check is smaller than 100 we can't create exams because we have not enough questions.
        if(check<100) return null;

        Random r = new Random();
        int total = 0;
        Set<Question> examQuestions = new HashSet<>();
        int currentQuestionCount = 0;
        while(total<100){
            /*
            * Create random numbers between 0 and questionList
            * And get new questions until we pass 100 hundred total score
            * */
            int randomNum = r.nextInt(mChoiceQuestionsList.size());
            examQuestions.add(mChoiceQuestionsList.get(randomNum));

            // Control if we add unique question to our list
            // If we really add new question to list update total score
            if(currentQuestionCount<examQuestions.size()) {
                currentQuestionCount = examQuestions.size();
                total += mChoiceQuestionsList.get(randomNum).getScore();
            }
        }

        this.setQuestions(new ArrayList<>(examQuestions));
        this.setTotalScore(total);

        return this;
    }
}
