package test;

import model.question.*;
import org.junit.jupiter.api.Test;
import util.Filter;

import java.util.ArrayList;
import java.util.List;

public class QuestionTests {

    private List<Question> questionList;
    private Filter filter;
    private Question q1, q2, q3, q4, q5;

    private void initializeTestComponents(){
        questionList = new ArrayList<>();
        filter = new Filter(questionList);

        q1 = new TrueFalse("HTML bir programlama dilidir.", Level.EASY, 10);
        q1.setAnswer(true);

        q2 = new TrueFalse("Java bir programlama dili değildir.", Level.NORMAL, 20);
        q2.setAnswer(false);

        q3 = new Classic("Cumhuriyeti kim, kaç yılında kurmuştur?", Level.HARD, 30);
        q3.setAnswer("Mustafa Kemal Atatürk 1923 yılında kurmuştur.");

        q4 = new MultipleChoice("Hangisi bir programlama dili değildir?",Level.EASY, 20,
                new String[]{"Golang","CSS","Python","Dart"});
        q4.setAnswer(1);

        q5 = new MultipleChoice("Hangisi teknolojik bir eşya değildir?",Level.EASY, 10,
                new String[]{"Tırnak Makası", "Televizyon", "Bilgisayar", "Telefon"});
        q5.setAnswer(0);

        questionList.add(q1);
        questionList.add(q2);
        questionList.add(q3);
        questionList.add(q4);
        questionList.add(q5);

    }

    @Test
    public void questionDeletionAfterFindingByQuestionContent(){
        initializeTestComponents();
        /*
        * test if we can find the right questions and after that
        * delete the question that we wanted.
        * */

        /*
        * by the way in here. we can test if we're sorting those questions
        * in order of score points or not*/

        String search = "programlama";
        /*
        * First of all find the question that you want to delete.
        * You have to search by question content.
        * */
        List<Question> filteredQuestions = filter.filterByQuestion(search);

        // q1, q2, q4 questions matches with the searching context
        // Let's say we want to delete q2. So in our new list when we see
        // the questions. We have to pick 1.index -(0 based)
        // Then we're getting the filteredQuestions.get(1) and then we are going
        // to remove the question from questions list.
        Question questionWhichIsAboutToDelete = filteredQuestions.get(1);
        questionList.remove(questionWhichIsAboutToDelete);

        assert !questionList.contains(questionWhichIsAboutToDelete):"This question should be removed from the list";

        System.out.println("Deleted Question");
        System.out.println(questionWhichIsAboutToDelete);

        System.out.println("-----------------NEW LIST-----------------");
        for (Question q : questionList) System.out.println(q);
    }
}
