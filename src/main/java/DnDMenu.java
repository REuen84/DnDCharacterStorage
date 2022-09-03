import java.sql.SQLException;
import java.util.Scanner;

import Model.User;
import Service.ActorService;
import Service.JobService;
import Service.UserService;

public class DnDMenu {
   public static void main(String[] args) throws SQLException {
       Scanner userInput = new Scanner(System.in);
       UserService us = new UserService();
       boolean intro = true;
       boolean usingApp = true;
       ActorService as = new ActorService();
       JobService js = new JobService();
       String currentUser = "";

       while(intro) {
           boolean login = true;
       System.out.println("Welcome to Character Storage! What would you like to do? 1: Login, 2: Create New User, 3: Quit");
       String line = userInput.nextLine();
       System.out.println("");
           if (line.equals("1")) {
               while (login) {
                   System.out.println("Please enter your username: ");
                   currentUser = userInput.nextLine();
                   System.out.println("");
                   System.out.println("Please enter your password: ");
                   String password = userInput.nextLine();
                   System.out.println("");
                   if (us.checkLoginInfo(currentUser, password)) {
                       System.out.println("Login successful! Welcome back, traveler.");
                       intro = false;
                       login = false;
                       System.out.println("");
                   } else {
                       System.out.println("Uh oh, that username and password didn't work. Try again.");
                       login = false;
                       System.out.println("");
                   }
               }
           } else if (line.equals("2")){
               System.out.println("Please enter your desired Username:");
               String newuser = userInput.nextLine();
               System.out.println("");
               System.out.println("Please enter your Password:");
               String password = userInput.nextLine();
               us.addUser(newuser, password);
               System.out.println("");

           }else if (line.equals("3")) {
               System.out.println("Very well. Maybe next time.");
               System.out.println("");
               intro = false;
               login = false;
               usingApp = false;
           } else {
               System.out.println("Invalid input, please choose one of the provided options.");
               System.out.println("");
           }
       }



       while(usingApp) {
           User u = us.getUserByUsername(currentUser);
           int userID = u.getUserID();
           System.out.println("Select an option: 1: View Class Details, 2: View Characters, 3: Manage Characters, 4: Quit");
           //Scanner userInput = new Scanner(System.in);
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
               System.out.println("Select an option: 1: Add new character, 2: Update character level, 3: Delete a character");
               String mline = userInput.nextLine();
               System.out.println("");

               if(mline.equals("1")) {
                   System.out.println("What is the name of your character?");
                   String name = userInput.nextLine();
                   System.out.println("");
                   System.out.println("What Class is your character?");
                   String cla = userInput.nextLine();
                   System.out.println("");
                   System.out.println("What level is your character?");
                   int level = userInput.nextInt();
                   //  String out = String.format("%s is a level %2d %s", name, level, cla);
                   //  System.out.println(out);
                   //User u = us.getUserByUsername(currentUser);
                   //int userID = u.getUserID();
                   as.addActor(name, cla, level, currentUser, userID);
                   System.out.println("");

               }else if(mline.equals("2")) {
                   //User u = us.getUserByUsername(currentUser);
                   //int userID = u.getUserID();
                   System.out.println("What is the name of the character you would like to update?");
                   String name = userInput.nextLine();
                   System.out.println("");
                   System.out.println("What level is your character now?");
                   int newLevel = userInput.nextInt();
                   as.updateActor(name, userID, newLevel);
                   System.out.println("");

               }else if(mline.equals("3")) {
                   System.out.println("What is the name of the character you would like to delete?");
                   String name = userInput.nextLine();
                   System.out.println("");
                   if(as.deleteActor1(name, userID)){
                       System.out.println("Are you sure you want to delete this character? 1: Yes, 2:No");
                       mline = userInput.nextLine();
                       System.out.println("");
                       if(mline.equals("1") || mline.equals("yes") || mline.equals("y")){
                           as.deleteActor2(name, userID);
                           System.out.println("");
                       }else if(mline.equals("2") || mline.equals("no") || mline.equals("n")){
                           System.out.println("Process aborted, this character lives to see another day.");
                           System.out.println("");
                       }else{
                           System.out.println("Input not recognized. Process aborted and returning to the main menu.");
                           System.out.println("");
                       }
                   }
               }

           } else if (line.equals("4")) {
               System.out.println("Until next time traveler, your party shall await your return.");
               usingApp = false;
           }
           else { System.out.println("Sorry, you seem to be lost, traveler. Please choose one of the listed options.");
               System.out.println("");}
           }
           }
       }