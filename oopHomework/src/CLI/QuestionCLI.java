package CLI;

import model.question.*;
import util.Filter;

import java.util.List;
import java.util.Scanner;

public class QuestionCLI {

    private static QuestionCLI qCLI;
    private Scanner scanner;
    public static List<Question> questionList ;

    public static QuestionCLI getInstance(Scanner scanner){
        if(qCLI==null) qCLI = new QuestionCLI();
        if(qCLI.scanner == null) qCLI.scanner= scanner;
        return qCLI;
    }
    private QuestionCLI(){
    }

    public void loadMenuContext(){
        System.out.println(  "\n\tLütfen sorular ile yapmak istediğiniz operasyonu seçiniz.\n" +
                "\t1) Soru Ekle\n" +
                "\t2) Soru Sil\n" +
                "\t3) Soru Güncelle\n" +
                "\t4) Soruları Listele\n" +
                "\tGeri dönmek için 0 tuşlayabilirsiniz.");

        System.out.print("\tSeçiminiz: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice){
            case 1:
                addQuestionMenuContext();
                break;
            case 2:
                deleteQuestionMenuContext();
                break;
            case 3:
                break;
            case 4:
                FilterCLI.getInstance(scanner).loadMenuContext();
                break;
            case 0:
                // just break the code.
                break;
        }
    }


    private void addQuestionMenuContext(){
        System.out.println(
                "\tMerhaba lütfen eklemek istediğiniz soru tipini seçiniz.\n"+
                "\t\t1) Çoktan Seçmeli\n"+
                "\t\t2) Klasik\n"+
                "\t\t3) Doğru/Yanlış\n"+
                "\t\tGeri dönmek için 0 tuşlayabilirsiniz."
                );
        System.out.print("\t\tSeçiminiz: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 0) return;


        // Get basic question properties here.
        /*
        * Question text
        * Question level
        * Question score
        * */
        System.out.print("\t\t\tLütfen soruyu giriniz: ");
        String question = scanner.nextLine();
        System.out.print("\t\t\tLütfen soru seviyesini giriniz (Kolay, Orta, Zor): ");
        String levelString = scanner.nextLine();

        // Enum convertion here.
        Level lvl = levelString.equals("Kolay")?Level.EASY:(levelString.equals("Orta")?Level.NORMAL:Level.HARD);

        System.out.print("\t\t\tLütfen soru puanını giriniz (10,20,30): ");
        int score = scanner.nextInt();
        scanner.nextLine();
        Question q = null;
        Object answer;
        switch (choice){
            case 1:
                // Multiple Choice adding field.
                // Ask for question text .... and also a,b,c,d choices.
                System.out.print("\t\t\tA şıkkı: ");
                String a = scanner.nextLine();
                System.out.print("\t\t\tB şıkkı: ");
                String b = scanner.nextLine();
                System.out.print("\t\t\tC şıkkı: ");
                String c = scanner.nextLine();
                System.out.print("\t\t\tD şıkkı: ");
                String d = scanner.nextLine();
                System.out.print("\t\t\tLütfen doğru cevabı giriniz (1, 2, 3, 4): ");
                answer = scanner.nextInt() - 1; // we're decreasing by 1 because indices starts from 0.
                // Create question here.
                q = new MultipleChoice(question, lvl, score, a,b,c,d);
                q.setAnswer(answer);
                break;
            case 2:
                // Classic question adding field.
                System.out.print("\t\t\tLütfen doğru cevabı giriniz: ");
                answer = scanner.nextLine();
                // Create question here.
                q = new Classic(question, lvl, score);
                q.setAnswer(answer);
                break;
            case 3:
                // True/False question adding field.
                System.out.print("\t\t\tLütfen doğru cevabı giriniz (D/Y) : ");
                answer = scanner.nextLine().equals("D")?true:false;
                // Create question here.
                q = new TrueFalse(question, lvl, score);
                q.setAnswer(answer);
                break;
        }
        questionList.add(q);
    }

    private void deleteQuestionMenuContext(){
        System.out.print("\t\tLütfen soru metni içerisinde aranacak kelimeyi giriniz: ");
        String searchingString = scanner.nextLine();
        Filter f = new Filter(questionList);
        List<Question> filteredQuestionList = f.filterByQuestion(searchingString);


        if(filteredQuestionList.size()==0) {
            System.out.println("Aradığınızda kelime hiçbir soruda bulunamadı.");
            return;
        }
        for(int i=0;i<filteredQuestionList.size();++i)
            System.out.println((i+1)+") "+filteredQuestionList.get(i));

        System.out.print("\t\tLütfen çıkarmak istediğiniz sorunun numarasını giriniz: ");
        int index = scanner.nextInt();
        Question deletedQuestion = filteredQuestionList.get(index -1);
        questionList.remove(deletedQuestion);
        System.out.println("\t\tAşağıda belirtilen soru başarıyla silindi.");
        System.out.println(deletedQuestion);
    }



}
