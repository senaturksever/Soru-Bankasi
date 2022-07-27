package model.exam;

import model.question.Classic;
import model.question.Question;

import java.util.*;

public class ClassicExam extends Exam {

    public ClassicExam(String title) {
        super(title);
        setExamType(ExamType.CLASSIC);
    }

    @Override
    public Exam createExam(List<Question> questionList) {
        int check = 0;
        List<Question> classicQuestionsList = new ArrayList<>();
        // Check if there is enough questions exist to create exam.
        for(Question q: questionList) {
            if(q instanceof Classic){
                check+=q.getScore();
                classicQuestionsList.add(q);
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
            int randomNum = r.nextInt(classicQuestionsList.size());
            examQuestions.add(classicQuestionsList.get(randomNum));

            // Control if we add unique question to our list
            // If we really add new question to list update total score
            if(currentQuestionCount<examQuestions.size()) {
                currentQuestionCount = examQuestions.size();
                total += classicQuestionsList.get(randomNum).getScore();
            }
        }

        this.setQuestions(new ArrayList<>(examQuestions));
        this.setTotalScore(total);

        return this;
    }




}
