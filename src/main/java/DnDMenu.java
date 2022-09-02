import java.sql.SQLException;
import java.util.Scanner;
import Service.ActorService;
import Service.JobService;

public class DnDMenu {
   public static void main(String[] args) throws SQLException {
       boolean usingApp = true;
       ActorService as = new ActorService();
       JobService js = new JobService();

       while(usingApp) {
           System.out.println("Select an option: 1: View Class Details, 2: View Stored Characters, 3: Add New Character, 4: Quit");
           Scanner userInput = new Scanner(System.in);
           String line = userInput.nextLine();
           System.out.println("");
           if (line.equals("1")) {
               System.out.println("Which Class would you like to view? 1: Artificer, 2: Barbarian, 3: Bard, 4: Cleric, 5: Druid, 6: Fighter, 7: Monk, 8: Paladin, 9: Ranger, 10: Rogue, 11: Sorcerer, 12: Warlock, 13: Wizard");
               String cline  = userInput.nextLine();
               System.out.println("");
               //System.out.println(cline);
               if (cline.equals("1")) {
                   String cla = "Artificer";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("2")) {
                   String cla = "Barbarian";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("3")) {
                   String cla = "Bard";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("4")) {
                   String cla = "Cleric";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("5")) {
                   String cla = "Druid";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("6")) {
                   String cla = "Fighter";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("7")) {
                   String cla = "Monk";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("8")) {
                   String cla = "Paladin";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("9")) {
                   String cla = "Ranger";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("10")) {
                   String cla = "Rogue";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("11")) {
                   String cla = "Sorcerer";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("12")) {
                   String cla = "Warlock";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else if (cline.equals("13")) {
                   String cla = "Wizard";
                   System.out.println(js.getDescriptionFromClass(cla));
                   System.out.println("");
               } else {
                   System.out.println("Whoops, looks like that one wasn't on the menu, friend. Let me know what else you might want.");
               }

           } else if (line.equals("2")) {
               //System.out.println("List of Character names and classes here.");
               System.out.println(as.getAllActors());
               System.out.println("");

           } else if (line.equals("3")) {
               System.out.println("What is the name of your character?");
               String name = userInput.nextLine();
               System.out.println("");
               System.out.println("What Class is your character?");
               String cla = userInput.nextLine();
               System.out.println("");
               System.out.println("What level is your character?");
               int level = userInput.nextInt();
               System.out.println("");
             //  String out = String.format("%s is a level %2d %s", name, level, cla);
             //  System.out.println(out);
               as.addActor(name, cla, level);

           } else if (line.equals("4")) {
               System.out.println("Until next time traveler, your party shall await your return.");
               usingApp = false;
           }
           else { System.out.println("Sorry, you seem to be lost, traveler. Please choose one of the listed options.");
               System.out.println("");}
           }
           }
       }