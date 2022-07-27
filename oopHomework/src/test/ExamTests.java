package test;

import model.exam.*;
import model.question.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.List;

public class ExamTests {

    private List<Question> questionList;
    private Exam ex1, ex2, ex3;


    private void initializeTestComponents(){

        questionList = new ArrayList<>();
        // Create questions
        Question q;
        q = new Classic("Klasik soru 1", Level.EASY, 10);
        q.setAnswer("Cevap 1");
        questionList.add(q);

        q = new Classic("Klasik soru 2", Level.NORMAL, 20);
        q.setAnswer("Cevap 2");
        questionList.add(q);

        q = new Classic("Klasik soru 3", Level.HARD, 30);
        q.setAnswer("Cevap 3");
        questionList.add(q);

        q = new Classic("Klasik soru 4", Level.EASY, 10);
        q.setAnswer("Cevap 4");
        questionList.add(q);

        q = new Classic("Klasik soru 5", Level.NORMAL, 20);
        q.setAnswer("Cevap 5");
        questionList.add(q);

        q = new Classic("Klasik soru 6", Level.HARD, 30);
        q.setAnswer("Cevap 6");
        questionList.add(q);

        q = new Classic("Klasik soru 7", Level.EASY, 10);
        q.setAnswer("Cevap 7");
        questionList.add(q);

        q = new TrueFalse("True/False soru 1", Level.EASY, 10);
        q.setAnswer(true);
        questionList.add(q);

        q = new TrueFalse("True/False soru 2", Level.NORMAL, 20);
        q.setAnswer(false);
        questionList.add(q);

        q = new TrueFalse("True/False soru 3", Level.HARD, 30);
        q.setAnswer(true);
        questionList.add(q);

        q = new TrueFalse("True/False soru 4", Level.EASY, 10);
        q.setAnswer(false);
        questionList.add(q);

        q = new TrueFalse("True/False soru 5", Level.NORMAL, 20);
        q.setAnswer(true);
        questionList.add(q);

        q = new TrueFalse("True/False soru 6", Level.HARD, 30);
        q.setAnswer(false);
        questionList.add(q);

        q = new TrueFalse("True/False soru 7", Level.EASY, 10);
        q.setAnswer(true);
        questionList.add(q);

        q = new MultipleChoice("Multiple Choice soru 1", Level.EASY, 10,
                new String[]{"Seçenek A 1", "Seçenek B 1", "Seçenek C 1", "Seçenek D 1"});
        q.setAnswer(1);
        questionList.add(q);

        q = new MultipleChoice("Multiple Choice soru 2", Level.NORMAL, 20,
                new String[]{"Seçenek A 2", "Seçenek B 2", "Seçenek C 2", "Seçenek D 2"});
        q.setAnswer(2);
        questionList.add(q);

        q = new MultipleChoice("Multiple Choice soru 3", Level.HARD, 30,
                new String[]{"Seçenek A 3", "Seçenek B 3", "Seçenek C 3", "Seçenek D 3"});
        q.setAnswer(3);
        questionList.add(q);

        q = new MultipleChoice("Multiple Choice soru 4", Level.EASY, 10,
                new String[]{"Seçenek A 4", "Seçenek B 4", "Seçenek C 4", "Seçenek D 4"});
        q.setAnswer(0);
        questionList.add(q);

        q = new MultipleChoice("Multiple Choice soru 5", Level.EASY, 10,
                new String[]{"Seçenek A 5", "Seçenek B 5", "Seçenek C 5", "Seçenek D 5"});
        q.setAnswer(1);
        questionList.add(q);

        q = new MultipleChoice("Multiple Choice soru 6", Level.NORMAL, 20,
                new String[]{"Seçenek A 6", "Seçenek B 6", "Seçenek C 6", "Seçenek D 6"});
        q.setAnswer(2);
        questionList.add(q);

        q = new MultipleChoice("Multiple Choice soru 7", Level.HARD, 30,
                new String[]{"Seçenek A 7", "Seçenek B 7", "Seçenek C 7", "Seçenek D 7"});
        q.setAnswer(2);
        questionList.add(q);

        ex1 = new ClassicExam("Exam1").createExam(questionList);
        ex2 = new MixedExam("Exam2").createExam(questionList);
        ex3 = new TestExam("Exam3").createExam(questionList);
    }

    @Test
    public void isClassicExamHasCorrectQuestions(){
        initializeTestComponents();

        assert ex1.getExamType() == ExamType.CLASSIC: "Exam type should be classic";
        for(Question q : ex1.getQuestions()) assert q instanceof Classic: "Every question type should classic.";
    }

    @Test
    public void isTestExamHasCorrectQuestions(){
        initializeTestComponents();

        assert ex3.getExamType()==ExamType.TEST: "Exam 3 should be TEST type";
        for(Question q :ex3.getQuestions()) assert q instanceof MultipleChoice: "Every question type should multiple choice";
    }


    @ParameterizedTest
    @EnumSource(ExamType.class)
    public void isTestExamHasDuplicateElements(ExamType type){
        initializeTestComponents();

        Exam exo = null;
        if(type == ExamType.CLASSIC) exo = new ClassicExam("classic").createExam(questionList);
        else if(type == ExamType.MIXED) exo = new MixedExam("mixed").createExam(questionList);
        else exo = new TestExam("test").createExam(questionList);

        assert exo.getExamType()== type: "Exam type should be type";
        assert exo.getTotalScore()>90:"Exam score should bigger than 90";
        assert exo.getQuestions()!=null: "Exam questions should be ready.";

        Question tmp = exo.getQuestions().get(0);
        for(int i=1;i<exo.getQuestions().size(); ++i){
            assert exo.getQuestions().get(i)!=tmp:"It shouldn't be any duplicate elements on the exam.";
            tmp = exo.getQuestions().get(i);
        }

        System.out.println(exo);
    }


    @Test
    public void calculateTheTestExamScore(){
        // Get a test exam which is ex3
        initializeTestComponents();

        assert ex3.getExamType() == ExamType.TEST: "It should be test";

        // First of all create answers to that test exam
        // Lets say all of our exam answers are 1.
        List<Object> answers = new ArrayList<>();
        for(int i=0; i<ex3.getQuestions().size(); ++i){
            answers.add(1);
        }

        // Now calculate the actual exam score compared with 1
        int actualTotalScore = 0;
        for(int i=0; i<ex3.getQuestions().size(); ++i)
            if((int)ex3.getQuestions().get(i).getAnswer() == 1)
                actualTotalScore+= ex3.getQuestions().get(i).getScore();

        int calculatedTotalScore = 0;
        for(int i=0; i<ex3.getQuestions().size(); ++i)
            if((int)ex3.getQuestions().get(i).getAnswer() == (int)answers.get(i))
                calculatedTotalScore+= ex3.getQuestions().get(i).getScore();

        assert actualTotalScore == calculatedTotalScore : "It should be same.";
    }
}
