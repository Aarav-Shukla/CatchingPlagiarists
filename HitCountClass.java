import java.util.*;
import java.io.*;
/**
 * This TheHitList class represents . . .
 *
 * @author  Aarav
 * @version (todays date)
 */
public class HitCountClass implements Comparable<HitCountClass>
{
    private int hitCount, alpha;
    private String first, second;
    FileClass f = new FileClass();

    public HitCountClass()
    {
        FileClass f = new FileClass();
    }

    public HitCountClass(int hitCount, String f, String s)
    {
        this.hitCount = hitCount;
        this.first = f;
        this.second = s;
    }

    public int compareTo(HitCountClass h)
    {
        return Integer.compare(h.hitCount, this.hitCount);
    }

    public int hitCounter(List<String> sml, String d, int numWords, int thold) throws FileNotFoundException 
    {
        List<HitCountClass> sort = new ArrayList<>();//ArrayList for sorting
        int hitCount = 0;//Declare an integer to be used as the hitCount
        Map<String, Set<String>> mapofsets = new HashMap();//Stores all values in a map of sets; Faster access to data
        for(String pathName : sml)
        {
            Set<String> s = new HashSet<>(f.readFiles(d, pathName, numWords));//Adds n-contiguous word phrases to a set
            mapofsets.put(pathName, s);//Adds the set to the map of sets
        }
        //Progress indicator variables:
        double totalsize = (sml.size() * (sml.size()-1)) / 2;//Size of selected list
        double currentiter = 0.0;
        //Output:
        for(int i = 0; i < sml.size() - 1; i++)//Iterates through the selected directory
        {
            for(int k = i + 1; k < sml.size(); k++)//Iterates through the selected directory at the index above i
            {
                String first = sml.get(i);//Gets the first path name to compare
                Set<String> set1 = mapofsets.get(first);
                String second = sml.get(k);//Gets the second path name to compare
                Set<String> set2 = mapofsets.get(second);
                Set<String> tracker = new HashSet<>(set1);//Adds the values of set1 to a comparer set
                int storesize = tracker.size();//Stores the size of tracker before elements are removed from it
                tracker.removeIf(word -> set2.contains(word));//The removeIf() method in java is a method which removes the elements of a list if they satisfy a filter
                int ce = storesize - tracker.size();//Gets the difference of the previous and current size of tracker, aka the amount of common elements 
                //Prints progress %:
                currentiter++;
                int pct = (int)(100 * currentiter/totalsize);
                System.out.print("\f");
                System.out.print("\n" + pct + "%" + " ready" + "\n" + "\n");
                //Threshold:
                if(ce >= thold)
                {
                    hitCount = ce;//Set hitCount to the amount of common elements
                    sort.add(new HitCountClass(hitCount, first, second));
                    Collections.sort(sort);//Sorts a list of the output
                }
            }
        }
        int alpha = sort.size();//Number of comparisons which yielded hits above the specified threshold
        System.out.println("Result:" + "\n");
        if(d == "Large number of documents")
        {
            try 
            {
                Thread.sleep(1500); // Wait for 1500 milliseconds (1.5 seconds) so it doesnt immediately spit out the hits
            } catch (InterruptedException x) 
            {
                //Nothing needed here
            }
        }
        System.out.print(sort.toString().replace("[", "").replace("]", "").replace(", ", ""));//Since a list prints with brackets and commas, this removes unnecessary text to make the output more aesthetically pleasing
        System.out.print("\n" + "Summary:" + "\n" + "•You selected the folder:" + " " +  "\"" + d + "\"" + "." + "\n" + "•You decided to compare n-contiguous word phrases with the length of " + numWords + ", and to ignore results which contain with less than " + thold + " hits." + "\n" + "•There were " + alpha + " comparisons made in " + "\"" + d + "\"" + " that yielded an amount of hits greater than the specified threshold: " + thold);//Summary report
        return hitCount;//Returns the integer hitCount
    }

    public String toString()
    {
        return "〚" + first + "" + "， " + second + "〛" + "➞ " + hitCount + "\n";
    }

}

