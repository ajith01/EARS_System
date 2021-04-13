import Backend.src.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class EarsSystem_ProjectMain extends Application {

    private final int MIN_PASS_LENGHT = 5;
    private User currUser;  //need to make this
    private int userType;
    private final int memberType = 2;
    private final int adminType = 1;

    //set up all the files and data
    java.io.File usrPwdfile = new File("src/usernameAndPwd.txt");
    java.io.File applicationFile = new File("src/applicationData.txt");

    ArrayList<JobApplication> applications = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();


    public static void main(String[] args){

        launch(args);
        System.out.println("System Launched");
//        loadData();
        System.out.println("Data Loaded");

    }

    private static void loadData(File file, ArrayList<Member> memebers , ArrayList<Application> applications) {
        makeMembersAtStartUp(file, memebers);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Set up all the panes
        LoginFrontend logins = new LoginFrontend();
        BigoneAdmin adminP = new BigoneAdmin();
        BigOneMember memberP = new BigOneMember();




        // Login
        Scene sceneLogin = new Scene(logins.setUp(), 400, 400);
        // Member Scene
        Scene sceneAdmin = new Scene(adminP.setUp(), 1000, 1000);
        // Member Scene
        Scene sceneMember = new Scene(memberP.setUp(), 1000, 1000);

        // Set the stag and show it -- START
        primaryStage.setTitle("Login");
        primaryStage.setScene(sceneLogin);
        primaryStage.show();

        logins.btSubmit.setOnAction(e-> {
            boolean success = false;
            try {
//              success = loginNewUser(logins.getUser(), logins.getPass() );
                success = logIn(logins.getUser(), logins.getPass(),usrPwdfile );
            } catch (EARSException exc){
                logins.setInfoError();
                exc.printStackTrace();
            }
            if(success){
                //assumes data is already loaded
                setUser(logins.getUser());

                if(userType == memberType) {
                    primaryStage.setTitle("Member");
                    primaryStage.setScene(sceneMember);
                    memberP.setApplications(getMemberData());
                    // this will return the applicant (names only) to the front end
                } else if(userType == adminType){
                    primaryStage.setTitle("Admin");
                    primaryStage.setScene(sceneAdmin);
                    adminP.setMembers(getAdminData());
                } else {
                    //TODO:error
                }
            }
        });
        //member update account setting
        memberP.btSubmitac.setOnAction(event -> {
            try {
                updateCurrUser(memberP.getEmail(), memberP.getPass());
                System.out.println("User " + getUser() + " has updated Info");
                memberP.setInfoSuccess();
            } catch (EARSException ex){
                memberP.setInfoError();
            }
        });
        //admin update account settings
        adminP.btSubmitac.setOnAction(event -> {
            try {
                updateCurrUser(adminP.getEmail(), adminP.getPass());
                System.out.println("User " + getUser() + " has updated Info");
                // TODO: adminP.setInfoSuccess(); like member has
            } catch (EARSException ex){
                adminP.setInfoError();

            }
        });
        //admin creating a new user
        adminP.btSubmit.setOnAction(event -> {
            try {
                createNewSystemUser(adminP.getName(),
                        adminP.getNewEmail(),
                        adminP.getUsername(),
                        adminP.getTempPass(),
                        adminP.getPosition());
                System.out.println("User " + adminP.getTempPass() + " has been Created");
            } catch (EARSException exc){
                    adminP.setCreateError();
            }
        });


    }


    // Admin Related Helper Functions
    private ArrayList<String> getAdminData() {
        ArrayList<String> memberNames = new ArrayList<>();
        for(Member user : members){
            memberNames.add(currUser.getName());
        }
        return memberNames;
    }

    // Member Related Helper Functions
    private ArrayList<String> getMemberData() {
        ArrayList<String> appNames = new ArrayList<>();
        for(JobApplication app : applications){
            if(app.hasMember(currUser.getUsername())){
                appNames.add(currUser.getName());
            }
        }
        return appNames;
    }


    // User Helper Functions
    private void createNewSystemUser(String name, String email, String username, String pass, String pos) throws EARSException{
        //TODO: creaet new user and save it to file,, Throw error if fields are not valid like passworkd email etc
        if(pass.equals("")){
            throw new EARSException("Create User Error");
        } else {
            User newUser = new User(name, email, username, pass, pos);
        }
    }
    private void updateCurrUser(String email, String pass) throws EARSException {
        // TODO: update user

        // TODO: throw exception for invalid fields. Pass >= length 5, email has 1 '@'
        if(pass.length() < MIN_PASS_LENGHT){
            throw new EARSException("Email not Valid or Password Too Short! (5)");
        }
    }
    private String getUser() {
        return currUser.getName();
    }
    private void setUser(String username) {
        //TODO
        setUserType(1);
    }
    private void setUserType(int t) {
        userType = t;
    }

    // ------- Login Helper Functions -------------------------------
//    private boolean loginNewUser(String u, String p) throws EARSException {
//        //TODO: handle code
//        if(u.equals("")){
//            throw new EARSException("USER NOT FOUND!");
//        }
//        setUser();
//        return true;
//    }


    public static boolean logIn(String username, String password, File file)
    throws EARSException{
        String user;
        String pwd;

        try (
                Scanner input = new Scanner(file);
        ) {
            while (input.hasNext()) {
                user = input.next();  //gets the next user name;

                //we can have some username based exceptions here

                if (user.equals(username)) {
                    pwd = input.next();   //gets the password

                    //we can have some password based exceptions here

                    if (pwd.equals(password)) {   //check if password is equal
                        return true;
                    }{
                        return false;
                    }
                }
                input.nextLine();   //just skip the password
            }

            //if not found til here, user name is not found so we throw exception
            throw new EARSException("USER NOT FOUND");

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

                applications.add(new JobApplication(name,jobTtle,description, new Date(startDate)
                        , new Date(endDate), chairList, commiteeList));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void makeMembersAtStartUp(File file, ArrayList<Member> members){
        /**
         * This function will make member class from the information given in the password file
         * and and it will make changes to the given main array to have the classes
         * assumes that there are no members in the array
         */


        try (
                //create a scanner class
                java.util.Scanner input = new Scanner(file);
        ) {
            while (input.hasNext()) {
                //get the values in the order that they exist in the textfile
                String username = input.next();
                String password = input.next();
                String name = input.next();
                String email = input.next();
                String position = input.next();

                //make into a member class and add it to the array
                members.add( new Member(name,email,username,password,position));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

}
