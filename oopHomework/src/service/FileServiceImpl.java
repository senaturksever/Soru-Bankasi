package service;

import java.io.*;

public class FileServiceImpl implements FileService {

    private final static String baseFilePath = "files/";


    @Override
    public void createFileIfNotExists(String path) throws IOException{
        // If directory not exists create a new one
        File directory = new File("files");
        if(!directory.exists()) directory.mkdir();

        File file = new File(baseFilePath + path);
        if(file.exists()) return;
        else file.createNewFile();
    }

    @Override
    public void write(String path, Object obj) throws IOException{
        createFileIfNotExists(path);
        FileOutputStream fileOutputStream = new FileOutputStream(baseFilePath + path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(obj);

        objectOutputStream.close();
    }

    @Override
    public Object read(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(baseFilePath+ path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Object obj = objectInputStream.readObject();

        objectInputStream.close();

        return obj;
    }
}
