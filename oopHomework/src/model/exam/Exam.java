package model.exam;

import model.question.Question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Exam implements Serializable {

    private String title;
    private int totalScore;
    private List<Question> questions;
    private ExamType examType;

    // After exam creation fill this areas while solving the exam
    private String userFullName;
    private List<Object> userAnswers;

    public abstract Exam createExam(List<Question> questionList);

    public Exam(String title){
        this.title = title;
    }


    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getTitle() {
        return title;
    }

    public ExamType getExamType() {
        return examType;
    }

    public void setExamType(ExamType examType) {
        this.examType = examType;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public List<Object> getUserAnswers() {
        return userAnswers;
    }
    public String getUserFullName() {
        return userFullName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserAnswers(List<Object> userAnswers) {
        this.userAnswers = userAnswers;
    }

    @Override
    public String toString() {
        // Last commit change is :
        /*
        * I changed the foreach loop to for loop to traverse both arrays parallel
        * i tried to configure toString method i wanted to see user's answers after the question.
        * */
            String questi = "";
            for(int i=0; i<getQuestions().size(); ++i){
                questi += (i+1)+") "+ getQuestions().get(i)+"\nKullanıcının cevabı: "+
                        (getUserAnswers()!=null?getUserAnswers().get(i):"null")+"\n";
            }

            return "Başlık: "+getTitle()+
                    "\nSınav Tipi: "+getExamType()+
                    "\nToplam Puan: "+ getTotalScore()+
                    "\nSınava giren kullanıcı: "+getUserFullName()+
                    "\nSoru Listesi: \n" +questi;
    }
}
