package snom.storage;

import snom.exceptions.SnomException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages folder/file creation and read file for Snom
 */

public class FileManager {
    protected Path path;

    public FileManager(String filename){
        this.path = Paths.get(filename);
    }

    /**
     * Creates directory with the given path
     */
    public void createFolder(){
        try{
            Files.createDirectories(path.getParent());
        }catch(FileAlreadyExistsException e){
            System.out.println("File exist. Nothing needs to be done here");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates file with the given path
     */
    public void createFile(){
        try{
            Files.createFile(path);
        }catch(FileAlreadyExistsException e){
            System.out.println("File exist. Nothing needs to be done here");
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns an array list of strings after reading from a file.
     *
     * @return array list of strings
     */
    public List<String> readFile() throws SnomException {
        try {
            List<String> lines = Files.readAllLines(path);
            return lines;
        } catch (IOException e) {
            throw new SnomException(e.getMessage());
        }
    }

    /**
     * Returns string of given text file
     *
     * @return
     */
    public String readResourcesText(){
        InputStream is = this.getClass().getResourceAsStream(path.getParent() + "/" + path.getFileName());
        String text = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        return text;
    }
}
