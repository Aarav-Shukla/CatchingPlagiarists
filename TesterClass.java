import java.util.*;
import java.io.*;
/**
 * This TesterClass class represents . . .
 *
 * @author  Aarav
 * @version (todays date)
 */
public class TesterClass
{
    static String d;   
    static FileClass f;
    static HitCountClass h;
    public static void main(String args[]) throws FileNotFoundException
    {
        //Declarations
        FileClass f = new FileClass();
        List<HitCountClass> sort = new ArrayList<>();

        //Makes menu
        List<String> fn = new ArrayList<>();
        fn.add("Small number of documents");
        fn.add("Medium number of documents");
        fn.add("Large number of documents");

        //Keyboard & Prompts
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Welcome to Catching Plagiarists!" + "\n" + "\n");
        System.out.println("Catching Plagiarists is a software application which can be used to detect plagarism in files from a selected folder.");
        System.out.println("\n" + "Press the space bar and click " + "\"" + "enter" + "\"" + " to open the menu");
        String openmenu = keyboard.nextLine();
        System.out.print("Folder numbers:" + "\n");
        for(int i = 0; i < fn.size(); i++)
        {
            System.out.print((i+1) + "." + " " + fn.get(i) + "\n");
        }
        System.out.println("\n" + "Please enter the folder number:");
        int foldn = keyboard.nextInt();
        switch(foldn)//Essentially an If/Else statement
        {
            case 1: d = fn.get(0);
                break;
            case 2: d = fn.get(1);
                break;
            case 3: d = fn.get(2);
                break;
            default: System.out.print("Error: This folder number does not exist." + "\n" + "Please rerun the program and input a folder number between 1 and 3.");
                System.exit(0);
        }
        System.out.println("\n" + "Next, Please input the length of the n-contiguous word phrases you would like to slice each file into:");
        int nl = keyboard.nextInt();
        if(nl<=0)
        {
            System.out.print("Error: Your input must be a positive integer." + "\n" + "Please rerun the program and input a length greater than 0.");
            System.exit(0);
        }
        System.out.println("\n" + "Finally, please input a minimum threshold of hits you would like to see:");
        int th = keyboard.nextInt();
        if(th<0)
        {
            System.out.print("Error: Your input is invalid." + "\n" + "Please rerun the program and input a threshold greater than or equal to 0.");
            System.exit(0);
        }
        
        //Result
        List<String> flist = f.makeList(d);
        long s = System.currentTimeMillis();//Start time
        HitCountClass h = new HitCountClass();
        h.hitCounter(flist, d, nl, th);//Calls hitCounter
        long e = System.currentTimeMillis();//End time
        System.out.println("\n" +  "•Time taken" + ": " + (e-s) + " milliseconds."); 
        System.out.println("\n" + "Thank you!");
    }
}
