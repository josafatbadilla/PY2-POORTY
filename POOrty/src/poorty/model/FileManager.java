
package poorty.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileManager {
    
    private FileManager(){
        
    }
    
    public static void createFile (String path){
        File myObj = new File(path);
    }
    
    
    public static void writeToFile(String path, String text){
        try {
          FileWriter myWriter = new FileWriter(path);
          myWriter.write(text);
          myWriter.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
    
    public static String readFile(String filePath) 
    {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
        {
 
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) 
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}
