import Backend.src.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class EarsSystem_ProjectMain extends Application {

    private static int MIN_PASS_LENGHT = 5;
    private User currUser;
    private int userType;
    private final int memberType = 2;
    private final int adminType = 1;

    //set up all the files and data
    java.io.File usrPwdfile = new File("src/usernameAndPwd.txt");
    java.io.File applicationFile = new File("src/applicationData.txt");
    java.io.File adminApplication = new File("src/adminMadeApplicationData.txt");
    java.io.File commentListing = new File("src/commentLists.txt");

    ArrayList<JobApplication> applications = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();


    public static void main(String[] args){

        launch(args);
        System.out.println("System Launched");
//        loadData();
        System.out.println("Data Loaded");
    }

    private void loadData(File file1, File file2,  ArrayList<Member> members , ArrayList<JobApplication> applications) {
        makeMembersAtStartUp(file1, members);
        makeAllApplicationsFromFile(file2, applications, members);
        readCommentsAndAddtoApplication(applications, commentListing);
    }

    @Override
    public void start(Stage primaryStage) {

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

        // login submit handler for all users
        logins.btSubmit.setOnAction(e-> {
            boolean success = false;
            try {
                success = logIn(logins.getUser(), logins.getPass(),usrPwdfile );
            } catch (EARSException exc){
                logins.setInfoError();
                exc.printStackTrace();
            }
            if(success){
                loadData(usrPwdfile, applicationFile,members, applications);
                //assumes data is already loaded
                try{setUser(logins.getUser(),members);} catch (EARSException earsException) {
                    earsException.printStackTrace();
                }

//                System.out.println(currUser.getPositionType());
//                System.out.println(currUser.getName());
//                System.out.println(currUser.getUsername());

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
                    System.out.println("Error: User type not found");
                }
            }
        });

        // member update account setting
        memberP.btSubmitac.setOnAction(event -> {
            try {
                updateCurrUser(memberP.getEmail(), memberP.getPass());
                makeChangeToUser(currUser.getUsername(),
                        memberP.getEmail(), 3,usrPwdfile );
                makeChangeToUser(currUser.getUsername(),
                        memberP.getPass(), 1,usrPwdfile );

                System.out.println("User " + getUser() + " has updated Info");
                memberP.setInfoSuccess();
            } catch (EARSException ex){
                memberP.setInfoError();
            }
        });

        // admin update account settings
        adminP.btSubmitac.setOnAction(event -> {
            try {
                makeChangeToUser(currUser.getUsername(),
                        adminP.getEmail(), 3,usrPwdfile );
                makeChangeToUser(currUser.getUsername(),
                        adminP.getPass(), 1,usrPwdfile );

                System.out.println("User " + getUser() + " has updated Info");
                // TODO: adminP.setInfoSuccess(); like member has
            } catch (EARSException ex){
                adminP.setInfoError();

            }
        });

        // admin creating a new user
        adminP.btSubmit.setOnAction(event -> {
            try {
                int posVal;
                if(adminP.getPosition().equals("Admin")){
                    posVal = 1;
                }else{
                    posVal = 2;
                }
                makeNewUser(adminP.getUsername(),adminP.getTempPass(), adminP.getName(),
                        adminP.getNewEmail(),posVal, usrPwdfile);
                System.out.println("User " + adminP.getUsername() + " has been Created");
            } catch (EARSException exc){
                    adminP.setCreateError();
            }
        });

        // admin making new job posting, faculty search
        adminP.btSubmitjob.setOnAction(event -> {
            try {
                createNewApplication(adminP.getNewJobPosition(), adminP.getStartDate(), adminP.getEndDate(),
                        adminP.getJobDes(), adminP.getNewChair(), adminP.getCommMembers(),adminApplication);
            } catch (EARSException | IOException exc ){
                exc.printStackTrace();
            }
        });

        // setting visibility of options in Application Pane
        memberP.cboapplicants.setOnAction(event -> {
            String applicantName = memberP.getCurrAppName();
            boolean isChair = hasChairCurrUser(applicantName);

            System.out.println(isChair + " " + applicantName + "");
            memberP.setChair(isChair);
            if(isChair){
                memberP.setAllComments(getAllComments(applicantName));
            }
            memberP.setAppDesc(getAppDesc(applicantName));
            memberP.setJobTitle(getAppTitle(applicantName));
            memberP.showCurrApplication();
        });
        // member submits comment - handled chair or member types
        memberP.btSubmit.setOnAction(event -> {
            String newComment = memberP.getNewCommet();
            String applicantName = memberP.getCurrAppName();
            addComments(memberP.getJobTitle(), applicantName, currUser.getUsername(), newComment, commentListing);
        });
    }


    private void createNewApplication(String posName, Date start, Date end, String jobDes, String chair, ArrayList<String> commMems, File file) throws EARSException, IOException {

        try(
                java.io.FileWriter output = new FileWriter(file, true);

        ){
            String str  = "";
            for (int i = 0; i < commMems.size(); i++){
                str += commMems.get(i) + ",";
            }

            output.write( posName + "," + start.toString() +"," + end.toString() + ","
            + jobDes +"," +chair + "," + commMems.size() + "," + str + "\r\n" );

        }
        ArrayList<String> newlist = new ArrayList<>();
        newlist.add(chair);
    }

    // Admin Related Helper Functions
    private ArrayList<String> getAdminData() {
        ArrayList<String> memberNames = new ArrayList<>();
        for(Member user : members){
            memberNames.add(user.getName());
        }
        return memberNames;
    }

    // Member Related Helper Functions
    private ArrayList<String> getMemberData() {
        ArrayList<String> appNames = new ArrayList<>();
        for(JobApplication app : applications){
            for(int i = 0; i < app.getChair().size(); i++) {
                System.out.println(app.getChair().get(i).getName());
            }
            if(app.hasMember(currUser.getUsername())){
                appNames.add(app.getCandidateName());
            }else if(app.hasChair(currUser.getUsername())){
                appNames.add(app.getCandidateName());
            }
        }
        return appNames;
    }

    private boolean hasChairCurrUser(String name){
        // checks if application with candidate=name has curruser as chair type
        for(JobApplication application: applications){
            System.out.println(application.getCandidateName());
            if (name.equals(application.getCandidateName())) {
                if (application.hasChair(currUser.getUsername())) {
                    return true;
                }
            }
        }
        return false;
    }

    private String getAllComments(String name){
        StringBuilder result = new StringBuilder();
        int c = 0;
        for(JobApplication application: applications){
            if (name.equals(application.getCandidateName())) {
                for(Comment comment: application.getComments()){
                    c++;
                    result.append(c);
                    result.append("  ");
                    result.append(comment.getCommitteeMember());
                    result.append(" -- ");
                    result.append(comment.getRemark());
                    result.append("\n");
                    System.out.println("reached");
                }
            }
        }
        return result.toString();
    }

    private String getAppTitle(String name) {
        for (JobApplication application : applications) {
            if (name.equals(application.getCandidateName())) {
                return application.getJobTitle();
            }
        }
        return "Default Title";
    }

    private String getAppDesc(String name) {
        for (JobApplication application : applications) {
            if (name.equals(application.getCandidateName())) {
                return application.getDescription();
            }
        }
        return "Default Description";
    }

    private void updateCurrUser(String email, String pass) throws EARSException {

        if(pass.length() < MIN_PASS_LENGHT){
            throw new EARSException("Email not Valid or Password Too Short! (5)");
        }
    }
    private String getUser() {
        return currUser.getName();
    }

    // ------- Login Helper Functions -------------------------------

    private void setUser(String username, ArrayList<Member> members) throws EARSException{
        for(int i = 0; i < members.size(); i++) {
            if(members.get(i).getUsername().equals(username)){
                currUser = members.get(i);
            }
        }
        if(currUser == null){
            throw new EARSException("User Not found. File corrupted");
        }

        if(currUser.getPositionType() == 1 || currUser.getPositionType() == 0){
        setUserType(1);
        }else{
            setUserType(2);
        }
    }

    public void setUserType(int t) {
        userType = t;
    }

    public boolean logIn(String username, String password, File file) throws EARSException{
        String user;
        String pwd;
        try (
                Scanner input = new Scanner(file);
        ) {
            while (input.hasNext()) {
                user = input.next().trim();  //gets the next user name;

                //we can have some username based exceptions here

                if (user.equals(username)) {
                    pwd = input.next().trim();   //gets the password

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


    // -----Backend Functions----------
    public void makeNewUser(String username, String password, String name,
                                   String email, int postition, File file) throws EARSException {
        /**
         * This function will make changes to the given text fields if a unique username is given, throw an expection if
         * the user name is not unique.
         */

        try (
                java.io.FileWriter output = new FileWriter(file, true);
                java.util.Scanner input = new Scanner(file);

        ) {
            while (input.hasNext()) {
                String tempUsername = input.next();
                if(tempUsername.equals("")){
                    throw new EARSException("Create User Error");
                }
                if ((tempUsername.equals(username))) {
                    throw new EARSException("username already exists");
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

    public void makeChangeToUser(String username, String newData,
                                        int dataField, File file)
            throws EARSException {
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

                if(newData.length() < MIN_PASS_LENGHT){
                    throw new EARSException("Email not Valid or Password Too Short! (5)");
                }

                if (buffer[0].equals(username)) {
                    buffer[dataField] = newData;
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeAllApplicationsFromFile(File file, ArrayList<JobApplication> applications,
                                                   ArrayList<Member> members) {


        ArrayList<Member> chairList = new ArrayList<Member>();
        ArrayList<Member> commiteeList = new ArrayList<Member>();


        try (
                java.util.Scanner input = new Scanner(file);
        ) {
            input.useDelimiter(",");
            while (input.hasNext()) {
                String name = input.next().trim();
                String jobTtle = input.next().trim();

                String description = input.next().trim();

                long startDate = Long.parseLong((input.next()).trim());

                long endDate = Long.parseLong(input.next().trim());

                int numberOfChair = Integer.parseInt(input.next().trim());



                for (int i = 0; i < numberOfChair; i++) {
                    String chairUsername = input.next().trim();
                    for (int j = 0; j < members.size(); j++) {
                        if (chairUsername.equals(members.get(j).getUsername())) {
                            chairList.add(members.get(j));
                        }
                    }
                }

                int numberOfMembers = Integer.parseInt((input.next().trim()));


                for (int i = 0; i < numberOfMembers; i++) {

                    String commmiteUsername = input.next().trim();
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

    public void makeMembersAtStartUp(File file, ArrayList<Member> members){
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
                int position = input.nextInt();

                //make into a member class and add it to the array
                Member temp =  new Member(name,email,username,password,position);
                members.add(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addComments(String jobTitle, String candidateName,String username, String comment, File file){
        ArrayList<String> temp = new ArrayList<>();

        try(
                java.io.FileWriter output = new FileWriter(file, true);
                java.util.Scanner input = new Scanner(file);
                ) {
            input.useDelimiter(",");
            String[] buffer;

            boolean val = false;
            while (input.hasNext()) {
                buffer = (input.nextLine()).split(",");

                if (buffer[0].equals(jobTitle) && buffer[1].equals(candidateName)) {
                    buffer[2] =  comment + "," + username +"," + buffer[2];
                    val = true;
                }
                String str = "";
                for(int i = 0; i < buffer.length; i++){
                    str += buffer[i] + ",";
                }
                temp.add(str + "\n" );
            }
            PrintWriter pw = new PrintWriter(file);
            pw.print("");
            pw.close();

            for (int i = 0; i < temp.size(); i++) {
                output.write(temp.get(i));
            }

            if(!val){
                output.write( jobTitle +"," + candidateName + "," + comment + "," + username + "\n");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readCommentsAndAddtoApplication(ArrayList<JobApplication> applicationList,File file) {
        try (
                java.util.Scanner input = new Scanner(file);
        ) {
            input.useDelimiter(",");
            String[] buffer;

            while (input.hasNext()) {
                buffer = (input.nextLine()).split(",");
                for (int j = 0; j < applicationList.size(); j++) {
                    if (buffer[0].equals(applicationList.get(j).getJobTitle()) && buffer[1].equals(applicationList.get(j).getCandidateName())) {
                        for (int i = 2; i < buffer.length; i += 2) {
                            applicationList.get(j).addComment(new Comment(buffer[i], buffer[i + 1]));
                        }
                    }
                }
            }

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }

}
