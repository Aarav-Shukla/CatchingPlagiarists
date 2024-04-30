import java.util.*;
import java.io.*;
/**
 * This FileClass class represents . . .
 *
 * @author  Aarav
 * @version (todays date)
 */
public class FileClass 
{
    private List<String> files;
    private String pathName;

    public List<String> makeList(String directory)
    {
        File dir = new File(directory);
        String[] temp = dir.list();
        List<String> files = new ArrayList<String>();
        for(int i = 0; i < temp.length; i++)
        {
            if(temp[i].endsWith(".txt"))
                files.add(temp[i]);
        }
        return files;
    }

    public List<String> readFiles(String folder, String pathName, int numWords) throws FileNotFoundException
    {
        Scanner file = new Scanner(new File(folder, pathName));
        List<String> reader = new ArrayList<>();
        List<String> builder = new ArrayList<>();
        int wordCount = 0;
        while(file.hasNext()) 
        {
            String word = file.next().replaceAll("[^A-Za-z]", "").toLowerCase();
            if(!word.isEmpty()) 
            {
                builder.add(word + " ");
                wordCount++;
                if(wordCount == numWords) 
                {
                    reader.add(builder.toString().trim());
                    int index = builder.indexOf(" ");
                    builder.remove(index+1);
                    wordCount--;
                }
            }
        }
        file.close();
        return reader;
    }

}
