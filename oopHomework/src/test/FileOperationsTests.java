package test;

import model.question.Question;
import org.junit.jupiter.api.Test;
import service.FileService;
import service.FileServiceImpl;

import java.io.IOException;
import java.util.List;

public class FileOperationsTests {

    private FileService fileService = null;
    private List<Question> questions = null;
    private final String questionFileName = "sorubankasi.dat", examFileName = "sinavlar.dat";


    private void initializeTestComponents(){
        fileService = new FileServiceImpl();
        try{
            fileService.createFileIfNotExists(questionFileName);
            fileService.createFileIfNotExists(examFileName);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void readQuestionListFromFile(){
        initializeTestComponents();

        try {
            questions = (List<Question>)fileService.read(questionFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(questions);
        assert questions!=null:"It should'nt be null because file is not empty.";

    }
}
