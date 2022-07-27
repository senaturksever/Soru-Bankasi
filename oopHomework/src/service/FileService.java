package service;

import java.io.IOException;

public interface FileService {

    void createFileIfNotExists(String path) throws IOException;
    /*
    * write any kind of object type to any file
    * with the given path.
    * */
    void write(String path, Object obj) throws IOException;

    /*
    * read any kind of object information from
    * the file at given path
    * */
    Object read(String path) throws IOException, ClassNotFoundException;
}
