package CLI;

import model.exam.Exam;
import model.question.Question;
import service.FileService;
import service.FileServiceImpl;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String examFileName = "sinavlar.dat";
    private static final String questionFileName = "sorubankasi.dat";
    private static String
        startingMessage = "\nMerhaba Soru Bankası Uygulamasına Hoşgeldiniz...\n"+
            "Lütfen görmek istediğiniz menüyü seçiniz.\n" +
            "1) Sorular\n"+
            "2) Sınavlar\n"+
            "Uygulamadan çıkmak için lütfen 0 tuşlayınız.";

    private static Scanner scanner = new Scanner(System.in);
    private static FileService fileService = new FileServiceImpl();


    public static void main(String[] args) {

        // Make the initilizations
        try{
            fileService.createFileIfNotExists(questionFileName);
            fileService.createFileIfNotExists(examFileName);

            QuestionCLI.questionList = (List<Question>)fileService.read(questionFileName);
            ExamCLI.examList = (List<Exam>)fileService.read(examFileName);
        }catch (Exception e){
            e.printStackTrace();
        }

        int choice = -1;

        while(choice != 0){
            System.out.println(startingMessage);
            System.out.print("Seçiminiz: ");

            /*
            * we have to check inputMismatchError
            * if user tries to enter something different than a number.
            * it gives an error. If we control it with try, catch program
            * wont stop after this happens
            * */
            choice = scanner.nextInt();
            /*try{
                choice = scanner.nextInt();
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Yanlış bir menü seçimi yaptınız. Lütfen tekrar deneyiniz.");
                continue;
            }*/

            switch (choice) {
                case 1:
                    QuestionCLI.getInstance(scanner).loadMenuContext();
                    break;
                case 2:
                    ExamCLI.getInstance(scanner).loadMenuContext();
                    break;
                default:
                    break;
            }
        }

        /*
        * After closing the application
        * Check if anything changed on the files.
        * If anything changes rewrite files with current objects.
        * */
        try {
            fileService.write(questionFileName, QuestionCLI.questionList);
            fileService.write(examFileName, ExamCLI.examList);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
