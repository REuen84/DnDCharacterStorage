import java.util.Scanner;

public class DnDMenu {
   public static void main(String[] args) {
       boolean usingApp = true;

       while(usingApp) {
           System.out.println("Select an option: 1: View Class Details, 2: View Stored Characters, 3: Add New Character, 4: Quit");
           Scanner userInput = new Scanner(System.in);
           String line = userInput.nextLine();
           if (line.equals("1")) {
               System.out.println("Enter the name of a DnD 5th Edition class you would like to view:");
               String cla = userInput.nextLine();
               System.out.println(cla);

           } else if (line.equals("2")) {
               System.out.println("List of Character names and classes here.");

           } else if (line.equals("3")) {
               System.out.println("What is the name of your character?");
               String name = userInput.nextLine();
               System.out.println("What Class is your character?");
               String cla = userInput.nextLine();
               System.out.println("What level is your character?");
               int level = userInput.nextInt();
               String out = String.format("%s is a level %2d %s", name, level, cla);
               System.out.println(out);

           } else if (line.equals("4")) {
               System.out.println("Until next time traveler, your party shall await your return.");
               usingApp = false;
           }
           }
           }
       }