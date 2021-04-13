import Backend.src.JobApplication;
import Backend.src.Member;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MainFunction extends Application {

    public LoginFrontend gui = new LoginFrontend();
    public BigoneAdmin gui2 = new BigoneAdmin();
    public BigOneMember gui3 = new BigOneMember();

    public static void main(String[] args) {
        java.io.File usrPwdfile = new File("src/usernameAndPwd.txt");
        java.io.File applicationFile = new File("src/applicationData.txt");
        ArrayList<JobApplication> applications;
        ArrayList<Member> members;

        launch(args);
    }

    public static boolean logIn(String username, String password, File file) {
        String user;
        String pwd;

        try (
                Scanner input = new Scanner(file);
        ) {
            while (input.hasNext()) {
                user = input.next();  //gets the next user name;
                if (user.equals(username)) {
                    pwd = input.next();   //gets the password

                    if (pwd.equals(password)) {   //check if password is equal
                        return true;
                    }
                }
                input.nextLine();   //just skip the password

            }

        } catch (FileNotFoundException e) {  //if there is an exception
            e.printStackTrace();
        }

        return false;                        //if none found
    }

    public static void makeNewUser(String username, String password, String name,
                                   String email, int postition, File file) throws UserException {
        /**
         * This function will make changes to the given text fules if a unique username is given, throw an expection if
         * the user name is not unique.

         */

        try (
                java.io.FileWriter output = new FileWriter(file, true);
                java.util.Scanner input = new Scanner(file);

        ) {
            while (input.hasNext()) {
                if (((input.next()).equals(username))) {
                    throw new UserException("username already exists");
                }
                input.nextLine();  //throws away the rest of the line
            }
            //makes a string and writes it out to file
            output.write("\n" + username + " " + password + " "
                    + name + " " + email + " " + postition);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeChangeToUser(String username, String oldPassword,
                                        String newData, int dataField, File file)
            throws UserException {
        /**datafeild is either 1 , 2 , 3
         1 changes the password
         2 changes the name
         3 changes the email
         dont give 0 or 4

         This can be made into 3 seperate functions if you dont like this design
         */


        //assumes that username is unique
        ArrayList<String> temp = new ArrayList<>();

        try (
                java.io.FileWriter output = new FileWriter(file, true);
                java.util.Scanner input = new Scanner(file);

        ) {
            String[] buffer;

            while (input.hasNext()) {
                buffer = (input.nextLine()).split(" ");
                if (buffer[0].equals(username)) {
                    if (buffer[1].equals(oldPassword)) {
                        buffer[dataField] = newData;
                    } else {
                        throw new UserException("Password does not match");
                    }
                }

                temp.add(buffer[0] + " " + buffer[1] + " "
                        + buffer[2] + " " + buffer[3] + " " + buffer[4] + "\n");

            }
            PrintWriter pw = new PrintWriter(file);
            pw.print("");
            pw.close();

            for (int i = 0; i < temp.size(); i++) {
                output.write(temp.get(i));
            }


            //            clear.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeAllApplicationsFromFile(File file, ArrayList<JobApplication> applications,
                                                   ArrayList<Member> members) {


        ArrayList<Member> chairList = new ArrayList<Member>();
        ArrayList<Member> commiteeList = new ArrayList<Member>();


        try (
                java.util.Scanner input = new Scanner(file);
        ) {
            input.useDelimiter(",");
            while (input.hasNext()) {
                String name = input.next();
                String jobTtle = input.next();
                String description = input.next();
                long startDate = input.nextLong();
                long endDate = input.nextLong();
                int numberOfChair = input.nextInt();


                for (int i = 0; i < numberOfChair; i++) {
                    String chairUsername = input.next();
                    for (int j = 0; j < members.size(); j++) {
                        if (chairUsername.equals(members.get(j).getUsername())) {
                            chairList.add(members.get(j));
                        }
                    }
                }

                int numberOfMembers = input.nextInt();

                for (int i = 0; i < numberOfChair; i++) {

                    String commmiteUsername = input.next();
                    for (int j = 0; j < members.size(); j++) {
                        if (commmiteUsername.equals(members.get(j).getUsername())) {
                            commiteeList.add(members.get(j));
                        }
                    }
                }

                applications.add(new JobApplication(name, jobTtle, description, new Date(startDate)
                        , new Date(endDate), chairList, commiteeList));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void makeMembersAtStartUp(File file, ArrayList<Member> members) {
        /**
         * This function will make member class from the information given in the password file
         * and and it will make changes to the given main array to have the classes
         * assumes that there are no members in the array
         */


        try (
                //create a scanner class
                java.util.Scanner input = new Scanner(file);
        ) {
            int count = 0;
            while (input.hasNext()) {
                //get the values in the order that they exist in the textfile
                String username = input.next();
                String password = input.next();
                String name = input.next();
                String email = input.next();
                int position = input.nextInt();

                //make into a member class and add it to the array
                members.add(new Member(name, email, username, password, position));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane p = new Pane();

        Scene scene = new Scene(gui, 400, 400);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Scene scene2 = new Scene(gui2.wow(), 1000, 1000);


        gui.btSubmit.setOnAction(e-> {
            primaryStage.setTitle("LMAO");
            //primaryStage.setScene(scene2);
        });

    }
}
