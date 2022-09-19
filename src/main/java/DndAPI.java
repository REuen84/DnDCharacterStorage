import java.sql.SQLException;
import java.util.Scanner;

import Model.Account;
import Model.Actor;
import Model.User;
import Model.uActor;
import Service.ActorService;
import Service.JobService;
import Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DndAPI {
    private static final Logger myLogger = LogManager.getLogger(DndAPI.class.getName());

    public static void main(String[] args) throws SQLException {
        ActorService as = new ActorService();
        JobService js = new JobService();
        UserService us = new UserService();
        String currentUser = "";
        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(9000);

        app.get("/characters", ctx -> {
            myLogger.info("User viewed all characters.");
            ctx.json(as.getAllActors());
        });

        app.get("/description/{className}", ctx -> {
            myLogger.info("User viewed information about a class.");
            ctx.json(js.getDescriptionFromClass(ctx.pathParam("className")));
        });

        //app.post("/user/", ctx -> {
       //     ObjectMapper mapper = new ObjectMapper();
       //     User currentUser = mapper.readValue(ctx.body(), User.class);
       //     us.checkAccountInfo(currentUser.getUsername(), currentUser.getPassword());
       // });

        app.get("/users/{username}/{password}", ctx -> {
            myLogger.info("User attempted to log in.");
            ctx.json(us.checkAccountInfo(ctx.pathParam("username"), ctx.pathParam("password")));
        });

        app.get("/view/{username}", ctx -> {
            myLogger.info("User viewed a character list.");
            ctx.json(as.getActorsByUser(ctx.pathParam("username")));
        });

        app.get("/userID/{username}", ctx -> {
            ctx.json(us.getUserByUsername(ctx.pathParam("username")));
        });

        app.post("/actors", ctx -> {
            myLogger.info("User created a new character.");
            ObjectMapper mapper = new ObjectMapper();
            Actor createdActor = mapper.readValue(ctx.body(), Actor.class);
            as.addActor(createdActor.getName(), createdActor.getCla(), createdActor.getLevel(), createdActor.getUserID());
        });

        app.get("/characters/chaID/{chaID}", ctx -> {
            myLogger.info("User viewed a character page.");
            ctx.json(as.getActorfromID(ctx.pathParam("chaID")));
        });

        app.post("/update", ctx -> {
            myLogger.info("User updated a character's level.");
            ObjectMapper mapper = new ObjectMapper();
            uActor updatedActor = mapper.readValue(ctx.body(), uActor.class);
            as.updateActor(updatedActor.getActorID(), updatedActor.getNewLevel());
        });

        app.post("/updateArt", ctx -> {
            myLogger.info("User updated a character's art.");
            ObjectMapper mapper = new ObjectMapper();
            uActor updatedActor = mapper.readValue(ctx.body(), uActor.class);
            as.updateArt(updatedActor.getActorID(), updatedActor.getNewUrl());
        });

        app.post("/delete", ctx -> {
            myLogger.info("User deleted a character.");
            ObjectMapper mapper = new ObjectMapper();
            Actor targetActor = mapper.readValue(ctx.body(), Actor.class);
            as.deleteActor2(targetActor.getName(), targetActor.getUserID());
        });





















       /* Scanner userInput = new Scanner(System.in);
        UserService us = new UserService();
        boolean intro = true;
        boolean usingApp = true;
        ActorService as = new ActorService();
        JobService js = new JobService();
        String currentUser = "";
        myLogger.info("Application started.");
        //myLogger.error("Error Message Logged !!!");

        while (intro) {
            boolean login = true;
            System.out.println("\nWelcome to Character Storage! What would you like to do? 1: Login, 2: Create New User, 3: Quit");
            String line = userInput.nextLine();
            if (line.equals("1")) {
                while (login) {
                    System.out.println("\nPlease enter your username: ");
                    currentUser = userInput.nextLine();
                    System.out.println("\nPlease enter your password: ");
                    String password = userInput.nextLine();
                    if (us.checkLoginInfo(currentUser, password)) {
                        System.out.println("\nLogin successful! Welcome back, traveler.");
                        String info = currentUser + " successfully logged in.";
                        myLogger.info(info);
                        intro = false;
                    } else {
                        System.out.println("\nUh oh, that username and password didn't work. Try again.");
                        String info = currentUser + "failed to login.";
                        myLogger.info(info);
                    }
                    login = false;
                }
            } else if (line.equals("2")) {
                System.out.println("\nPlease enter your desired Username:");
                String newuser = userInput.nextLine();
                System.out.println("\nPlease enter your Password:");
                String password = userInput.nextLine();
                us.addUser(newuser, password);
                myLogger.info("User attempted to create new Username and Password.");

            } else if (line.equals("3")) {
                System.out.println("\nVery well. Maybe next time.");
                myLogger.info("Application closed.");
                intro = false;
                login = false;
                usingApp = false;
            } else {
                System.out.println("\nInvalid input, please choose one of the provided options.");
                myLogger.info("User gave invalid input.");
            }
        }


        while (usingApp) {
            User u = us.getUserByUsername(currentUser);
            int userID = u.getUserID();
            System.out.println("\nSelect an option: 1: View Class Details, 2: View Characters, 3: Manage Characters, 4: Quit");
            String line = userInput.nextLine();
            if (line.equals("1")) {
                myLogger.info("User accessed the Class Details Menu.");
                System.out.println("\nWhich Class would you like to view? 1: Artificer, 2: Barbarian, 3: Bard, 4: Cleric, 5: Druid, 6: Fighter, 7: Monk, 8: Paladin, 9: Ranger, 10: Rogue, 11: Sorcerer, 12: Warlock, 13: Wizard");
                String cline = userInput.nextLine();
                System.out.println("");
                //System.out.println(cline);
                if (cline.equals("1") || cline.equals("Artificer")) {
                    myLogger.info("User viewed Artificer details.");
                    String cla = "Artificer";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("2")) {
                    myLogger.info("User viewed Barbarian details.");
                    String cla = "Barbarian";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("3")) {
                    myLogger.info("User viewed Bard details.");
                    String cla = "Bard";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("4")) {
                    myLogger.info("User viewed Cleric details.");
                    String cla = "Cleric";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("5")) {
                    myLogger.info("User viewed Druid details.");
                    String cla = "Druid";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("6")) {
                    myLogger.info("User viewed Fighter details.");
                    String cla = "Fighter";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("7")) {
                    myLogger.info("User viewed Monk details.");
                    String cla = "Monk";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("8")) {
                    myLogger.info("User viewed Paladin details.");
                    String cla = "Paladin";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("9")) {
                    myLogger.info("User viewed Ranger details.");
                    String cla = "Ranger";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("10")) {
                    myLogger.info("User viewed Rogue details.");
                    String cla = "Rogue";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("11")) {
                    myLogger.info("User viewed Sorcerer details.");
                    String cla = "Sorcerer";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("12")) {
                    myLogger.info("User viewed Warlock details.");
                    String cla = "Warlock";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else if (cline.equals("13")) {
                    myLogger.info("User viewed Wizard details.");
                    String cla = "Wizard";
                    System.out.println(js.getDescriptionFromClass(cla));
                } else {
                    System.out.println("\nWhoops, looks like that one wasn't on the menu, friend. Let me know what else you might want.");
                    myLogger.info("User gave invalid input.");
                }

            } else if (line.equals("2")) {
                //System.out.println("List of Character names and classes here.");
                myLogger.info("User accessed the View Characters Menu.");
                System.out.println("\nWould you like to view your characters or someone else's? 1: Mine, 2: Search by Username");
                String vline = userInput.nextLine();
                if (vline.equals("1") || vline.equals("Mine")) {
                    myLogger.info("User viewed their created characters.");
                    System.out.println(as.getActorsByUser(currentUser));

                } else if (vline.equals("2") || vline.equals("by username") || vline.equals("username")) {
                    System.out.println("\nPlease enter the username for the creator of the characters you would like to view:");
                    vline = userInput.nextLine();
                    if (us.getUserByUsername(vline) == null) {
                        System.out.println("\nCould not find a user with that username. Please confirm the credentials and try again.");
                        myLogger.info("User attempted to view another user's characters but the username was invalid.");
                    } else {
                        System.out.println(as.getActorsByUser(vline));
                        myLogger.info("User viewed another user's characters.");
                    }
                } else {
                    System.out.println("\nI didn't understand that one. Have another gander at the options and try again.");
                    myLogger.info("User gave invalid input.");
                }
                //System.out.println(as.getAllActors());

            } else if (line.equals("3")) {
                myLogger.info("User accessed the Manage Characters Menu.");
                System.out.println("\nSelect an option: 1: Add new character, 2: Update character level, 3: Delete a character");
                String mline = userInput.nextLine();

                if (mline.equals("1")) {
                    int level;
                    System.out.println("\nWhat is the name of your character?");
                    String name = userInput.nextLine();
                    System.out.println("\nWhat Class is your character?");
                    String cla = userInput.nextLine();
                    if(js.checkClass(cla)) {
                        System.out.println("\nWhat level is your character?");
                        if (userInput.hasNextInt()) {
                            level = userInput.nextInt();
                            as.addActor(name, cla, level, currentUser, userID);
                            myLogger.info("User attempted to add a new character.");
                        } else {
                            System.out.println("\nLevel must be a number from 1 to 20. Returning to main menu.");
                            myLogger.info("User entered a string where an integer was expected at character creation.");
                        }
                    } else{
                        System.out.println("\nSorry, that is not a recognized class. If you are not familiar with them, check them out from the main menu!");
                        myLogger.info("User entered an invalid class at character creation.");
                    }
                    /*System.out.println("\nWhat level is your character?");
                    if (userInput.hasNextInt()) {
                        level = userInput.nextInt();
                        as.addActor(name, cla, level, currentUser, userID);
                        myLogger.info("User attempted to add a new character.");
                     else {
                        System.out.println("\nLevel must be a number from 1 to 20. Returning to main menu.");
                        myLogger.info("User entered a string where an integer was expected.");} */
                    //  String out = String.format("%s is a level %2d %s", name, level, cla);
                    //  System.out.println(out);
                    //User u = us.getUserByUsername(currentUser);
                    //int userID = u.getUserID();
                    // as.addActor(name, cla, level, currentUser, userID);

               /* } else if (mline.equals("2")) {
                    //User u = us.getUserByUsername(currentUser);
                    //int userID = u.getUserID();
                    int newLevel;
                    System.out.println("\nWhat is the name of the character you would like to update?");
                    String name = userInput.nextLine();
                    System.out.println("\nWhat level is your character now?");
                    if (userInput.hasNextInt()) {
                        newLevel = userInput.nextInt();
                        as.updateActor(name, userID, newLevel);
                        myLogger.info("User attempted to update a character's level");
                    }
                    else {
                        System.out.println("\nLevel must be a number from 1 to 20. Returning to main menu.");
                        myLogger.info("User entered a string where an integer was expected.");
                    }

                } else if (mline.equals("3")) {
                    myLogger.info("User attempted to delete a character.");
                    System.out.println("\nWhat is the name of the character you would like to delete?");
                    String name = userInput.nextLine();
                    if (as.deleteActor1(name, userID)) {
                        System.out.println("\nAre you sure you want to delete this character? 1: Yes, 2:No");
                        mline = userInput.nextLine();
                        if (mline.equals("1") || mline.equals("yes") || mline.equals("y")) {
                            as.deleteActor2(name, userID);
                            myLogger.info("User deleted a character.");
                        } else if (mline.equals("2") || mline.equals("no") || mline.equals("n")) {
                            System.out.println("\nProcess aborted, this character lives to see another day.");
                            myLogger.info("Input a valid character name but decided not to delete them.");
                        } else {
                            System.out.println("\nInput not recognized. Process aborted and returning to the main menu.");
                            myLogger.info("User gave invalid input.");
                        }
                    }
                }

            } else if (line.equals("4")) {
                System.out.println("\nUntil next time traveler, your party shall await your return.");
                myLogger.info("Application closed.");
                usingApp = false;
            } else {
                System.out.println("\nSorry, you seem to be lost, traveler. Please choose one of the listed options.");
                myLogger.info("User gave invalid input.");
            }
        }

                */
    }
}