package CLI;

import model.question.Level;
import model.question.Question;
import util.Filter;

import java.util.*;

public class FilterCLI {

    private static FilterCLI filterCLI;
    private FilterCLI(){}

    private Scanner scanner;

    public static FilterCLI getInstance(Scanner scanner){
        if(filterCLI == null) filterCLI = new FilterCLI();
        if(filterCLI.scanner == null) filterCLI.scanner = scanner;
        return filterCLI;
    }

    public void loadMenuContext(){

        System.out.println(
                "\n\t\tListeleme yapmak için istediğiniz metodu seçiniz.\n"+
                "\t\t1) Soru metnine göre\n"+
                "\t\t2) Cevaba göre\n"+
                "\t\t3) Soru puanına göre\n"+
                "\t\t4) Seviyeye göre\n"+
                "\t\t5) Çoktan seçmeli sorularda şıkka göre\n"+
                "\t\t6) Tüm soruları listele");

        System.out.print("\t\tSeçiminiz: ");
        int choice = scanner.nextInt();
        scanner.nextLine();


        if(choice==0) return;

        Filter f = new Filter(QuestionCLI.questionList);
        List<Question> filteredQuestions = new ArrayList<>();

        switch (choice){
            case 1:
                System.out.print("\t\t\tAranacak kelimeyi giriniz: ");
                String qContent = scanner.nextLine();
                filteredQuestions = f.filterByQuestion(qContent);
                break;
            case 2:
                System.out.print("\t\t\tAranacak cevabı giriniz: ");
                Object answer = scanner.nextLine();
                filteredQuestions = f.filterByAnswer(answer);
                break;
            case 3:
                System.out.print("\t\t\tAranacak puan değerini giriniz: ");
                int score = scanner.nextInt();
                scanner.nextLine();
                filteredQuestions = f.filterByScore(score);
                break;
            case 4:
                System.out.print("\t\t\tAranacak soru seviyesini giriniz (Kolay, Orta, Zor): ");
                String levelString =  scanner.nextLine();
                Level lvl = levelString.equals("Kolay")?Level.EASY:(levelString.equals("Orta")?Level.NORMAL:Level.HARD);
                filteredQuestions = f.filterByLevel(lvl);
                break;
            case 5:
                System.out.print("\t\t\tAranacak kelimeyi giriniz: ");
                String content = scanner.nextLine();
                filteredQuestions = f.filterByQuestionTypeAndChoiceContents(content);
                break;
            case 6:
                filteredQuestions = QuestionCLI.questionList;
                break;
        }

        // sort by score
        Collections.sort(filteredQuestions);
        for(int i=0;i< filteredQuestions.size(); ++i){
            System.out.println((i+1)+") "+filteredQuestions.get(i));
        }

    }
}
