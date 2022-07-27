package test;

import model.question.*;
import org.junit.jupiter.api.Test;
import util.Filter;

import java.util.ArrayList;
import java.util.List;

public class FilterTests {

    private List<Question> questions;
    private Filter filter;
    private Question q1, q2, q3, q4;

    public void initializeTestComponents(){
        questions = new ArrayList<>();
        filter = new Filter(questions);
        q1 = new Classic("Question Classic", Level.EASY, 10); q1.setAnswer("Classic");
        q2 = new TrueFalse("Question True False", Level.NORMAL, 20); q2.setAnswer(true);
        q3 = new MultipleChoice("Question Multiple Choice", Level.EASY, 20, new String[]{"Choice A", "Choice B", "Choice C", "Choice D"}); q3.setAnswer(1);
        q4 = new TrueFalse("Question True False", Level.HARD, 10); q4.setAnswer(true);
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
    }

    @Test
    public void filterQuestionsByLevel(){
        initializeTestComponents();

        Level searchLevel = Level.NORMAL;
        List<Question> filteredQuestions = filter.filterByLevel(searchLevel);

        // Only q2 is normal level so only the q2 should pass test
        assert filteredQuestions.contains(q2):"Question q2's level is NORMAL. It should've found";
        assert filteredQuestions.size()==1:"It should only has to contain 1 element";

    }

    @Test
    public void filterQuestionsByQuestionContent(){
        initializeTestComponents();
        /*
        * add questions first.
        * Declare questions and also a question content
        * and try to know if the correct questions finding or not.
        * */


        String searchedContent = "true";
        /*
        * q1 and q3 has the content
        * so both of them should be in the results after filtering operation
        * */

        List<Question> filteredQuestions = filter.filterByQuestion(searchedContent);

        assert filteredQuestions.contains(q2):"Question q2 has the searched content.";
        assert filteredQuestions.contains(q4):"Question q4 has the searched content.";

        for(Question q : filteredQuestions)
        System.out.println(q+"\n");
    }

    @Test
    public void filterQuestionByScore(){
        initializeTestComponents();

        int score = 10;
        List<Question> filteredQuestions = filter.filterByScore(score);

        assert filteredQuestions.contains(q1):"Q1 Has 10 score point it should on the list.";
        assert filteredQuestions.contains(q4):"Q4 has 10 score point it should on the list.";
        assert filteredQuestions.size()==2:"It should've size 2 because 2 10 scores question exists";

    }

    @Test
    public void filterQuestionByQuestionChoiceContent(){
        initializeTestComponents();
        // First of all in our question bank we have only one multiple choice question
        // and the multiple choice question has choice string content in every choice
        // so it has to find q3 and nothing more
        List<Question> filteredQuestions = filter.filterByQuestionTypeAndChoiceContents("choice");

        assert filteredQuestions.contains(q3):"Q3 has the choice word in every choices.";
        assert filteredQuestions.size()==1:"Only one multiple choice question exist in this question bank";

    }
}
