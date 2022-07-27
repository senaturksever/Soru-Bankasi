package CLI;

import model.exam.*;
import model.question.MultipleChoice;
import model.question.Question;
import model.question.TrueFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExamCLI {

    private static ExamCLI instance;
    private Scanner scanner;
    public static List<Exam> examList;

    private ExamCLI(){
    }
    public static ExamCLI getInstance(Scanner scanner){
        if(instance==null) instance = new ExamCLI();
        if(instance.scanner == null) instance.scanner = scanner;
        return instance;
    }


    public void loadMenuContext(){

        System.out.println(
                "\n\tLütfen sınavlar ile yapmak istediğiniz operasyonu seçiniz.\n"+
                "\t1) Sınav Ol\n"+
                "\t2) Sınavları Listele\n"+
                "\tGeri dönmek için 0 tuşlayabilirsiniz."
        );

        System.out.print("\tSeçiminiz: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice == 0) return;


        switch (choice){
            case 1:
                Exam exam = null;
                // Exam creation
                // Pick exam title, exam type and create it
                System.out.print("\t\tSınavınızın başlığını giriniz: ");
                String title = scanner.nextLine();
                System.out.print("\t\tSınavınızın türünü giriniz (Klasik, Test, Karışık [sırasıyla 0,1,2]): ");
                int type = scanner.nextInt();
                scanner.nextLine();
                ExamType examType = type==0?ExamType.CLASSIC:((type==1)?ExamType.TEST:ExamType.MIXED);
                if(examType == ExamType.MIXED) exam = new MixedExam(title).createExam(QuestionCLI.questionList);
                else if(examType == ExamType.TEST) exam = new TestExam(title).createExam(QuestionCLI.questionList);
                else exam = new ClassicExam(title).createExam(QuestionCLI.questionList);

                // If exam==null, which means we can't create exam with those questions.
                // Print an error message
                if(exam == null) {
                    System.out.println("\tÜzgünüz. İstediğiniz sınavın yaratılması mümkün gözükmüyor. Lütfen daha fazla soru ekleyiniz.\n");
                    return;
                }

                System.out.print("Sınava girecek kişinin tam adı: ");
                exam.setUserFullName(scanner.nextLine());

                /*
                * Exam starts  here.
                * And start storing answers.
                * */
                List<Object> answers = new ArrayList<>();
                Object ans = null;
                for(Question q : exam.getQuestions()){
                    System.out.println(q);
                    if(q instanceof MultipleChoice){
                        System.out.print("Lütfen doğru cevabı giriniz (1,2,3,4): ");
                        ans = scanner.nextInt() - 1;
                        scanner.nextLine();
                    }else if(q instanceof TrueFalse){
                        System.out.print("Lütfen doğru cevabı giriniz (D/Y): ");
                        ans = scanner.nextLine().equals("D")?true:false;
                    }else {
                        System.out.print("Lütfen doğru cevabı giriniz: ");
                        ans = scanner.nextLine();
                    }
                    answers.add(ans);
                }
                // Set user answers to exam object
                exam.setUserAnswers(answers);
                if(exam.getExamType() == ExamType.TEST){
                    // calculate the exam score and print for the user.
                    // compare every question with user answers.
                    // if the exam is test. You can convert every answer to integer.
                    int totalScore = 0;
                    for(int i=0; i<answers.size(); ++i){
                        if((int)answers.get(i) == (int)exam.getQuestions().get(i).getAnswer()){
                            System.out.println((i+1)+". Soru Doğru.");
                            totalScore += exam.getQuestions().get(i).getScore();
                        }else System.out.println((i+1)+". Soru Yanlış.");
                    }

                    System.out.println("Sınavdan alınan toplam puan: "+totalScore);
                }

                examList.add(exam);

                break;
            case 2:
                /*
                * List all exams
                * */
                for(int i = 0; i<examList.size(); ++i){
                    System.out.println("--------------("+(i+1)+" SINAV )-----------------------");
                    System.out.println(examList.get(i));
                }
                break;

        }

    }
}
