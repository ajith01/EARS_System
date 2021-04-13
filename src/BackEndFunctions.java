//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Scanner;
//import java.util.concurrent.TimeUnit;
//
//import Backend.src.*;
//
//public class guifortest extends Application {
//
//    public static void main(String[] args) {   //main method
//        launch(args);
//        //start the start up process of reading the rules and creating users
//
//        //open files
//
//
//    }
//
//    public void start(Stage primaryStage) {
////        // Image
//
//        java.io.File usrPwdfile = new File("src/usernameAndPwd.txt");
//        java.io.File applicationFile = new File("src/applicationData.txt");
//        ArrayList<JobApplication> applications = new ArrayList<>();
//        ArrayList<Member> members = new ArrayList<>();
//
//        if (! usrPwdfile.exists()) {
//            //gui for critial error
//            System.exit(0);
//        }
//
//        //check if the file exists
//        if (! applicationFile.exists()) {
//            //gui for critial error
//            System.exit(0);
//        }
//
//
//
//        Image image = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqxBPBAczjUp-JFp4P5eTcmU5_0ECShsY2mw&usqp=CAU");
//        ImageView imageview1 = new ImageView(image);
//        imageview1.setFitHeight(100);
//        imageview1.setFitWidth(200);
//        StackPane paneimage = new StackPane();
//        paneimage.getChildren().add(imageview1);
//        paneimage.setAlignment(Pos.CENTER);
//
//
//        // Username
//        TextField tfUsername = new TextField();
//        tfUsername.setPrefWidth(202);
//        HBox hBox = new HBox(15);
//        hBox.setPadding(new Insets(20, 10, 10, 10));
//        hBox.getChildren().add(new Label("Username"));
//        hBox.getChildren().add(tfUsername);
//
//        // Password
//        TextField tfpassword = new TextField();
//        tfpassword.setPrefWidth(202);
//        HBox hBox2 = new HBox(15);
//        hBox2.setPadding(new Insets(20, 10, 10, 10));
//        hBox2.getChildren().add(new Label("Password "));
//        hBox2.getChildren().add(tfpassword);
//
//        // Button
//        Button btSubmit = new Button("Submit");
//        btSubmit.setStyle("-fx-border-color: black;");
//        HBox hBox3 = new HBox(15);
//        hBox3.setPadding(new Insets(20, 10, 10, 10));
//        hBox3.getChildren().add(btSubmit);
//        hBox3.setAlignment(Pos.CENTER);
//
//        // Put 3 elements into Vbox
//        VBox vBox = new VBox(10);
//        vBox.setPadding(new Insets(5, 5, 5, 5));
//        vBox.getChildren().addAll(paneimage, hBox, hBox2, hBox3);
////        vBox.getChildren().addAll( hBox, hBox2, hBox3);
//
//        vBox.setAlignment(Pos.CENTER);
//
//        // To Centre it all
//        StackPane pane = new StackPane();
//        pane.getChildren().add(vBox);
//        pane.setStyle("-fx-border-color: black; -fx-background-color: silver;");
//
//        Scene scene = new Scene(pane, 400, 400);
//        primaryStage.setTitle("EARS LOGIN");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        btSubmit.setOnAction(e-> {
//            for(int i = 0 ; i <1; i++) {
//
//                if (logIn(tfUsername.getText(), tfpassword.getText(), usrPwdfile)) {
//                    primaryStage.hide();
//
//                    System.out.println("Sucess");
//
//
//                    makeMembersAtStartUp(usrPwdfile, members);
//                    //always make members before applications
//                    makeAllApplicationsFromFile(applicationFile, applications, members);
//
//                } else {
//                    tfUsername.setText("Incorrect Enrty");
//                    tfpassword.setText("Incorrect Entry");
//                    System.exit(0);
//                }
//            }
//        });
//
////        Stage stage = new Stage();
////        stage.setTitle("Second Stage");
//
//
//
//
//
//        //----------------------Tests ----------------------------///
//        int userval;
//
//        if (! usrPwdfile.exists()) {
//            //gui for critial error
//            System.exit(0);
//        }
//
//        //check if the file exists
//        if (! applicationFile.exists()) {
//            //gui for critial error
//            System.exit(0);
//        }
//
//
//        System.out.println(logIn("master", "pass", usrPwdfile));
//        //didnt set up a user value yet, might set up after if required
//        //may use a string builder
//
//        try {
//            makeNewUser("ada", "ada", "adsa", "asda", 12, usrPwdfile);
//        } catch (UserException e) {
//            System.out.println(e.getMessage());
//        }
//
//
//        try {
////            java.io.PrintWriter writer = new PrintWriter(usrPwdfile);
//            makeChangeToUser("ada", "ada", "123", 1, usrPwdfile);
////            writer.println("Hello");
////            writer.close();
//
//            //makeAllApplicationsFromFile(applicationFile);
//
//        } catch (UserException u) {
//            System.out.println(u.getMessage());
//        }
//
//
//        //makeAllApplicationsFromFile(applicationFile, applications);
//
//
//        //---------------------- End of Tests ----------------------------///
//
//
//    }
//
//    public static boolean logIn(String username, String password, File file) {
//        String user;
//        String pwd;
//
//        try (
//                Scanner input = new Scanner(file);
//        ) {
//            while (input.hasNext()) {
//                user = input.next();  //gets the next user name;
//                if (user.equals(username)) {
//                    pwd = input.next();   //gets the password
//
//                    if (pwd.equals(password)) {   //check if password is equal
//                        return true;
//                    }
//                }
//                input.nextLine();   //just skip the password
//
//            }
//
//        } catch (FileNotFoundException e) {  //if there is an exception
//            e.printStackTrace();
//        }
//
//        return false;                        //if none found
//    }
//
//    public static void makeNewUser(String username, String password, String name,
//                                   String email, int postition, File file) throws UserException {
//        /**
//         * This function will make changes to the given text fules if a unique username is given, throw an expection if
//         * the user name is not unique.
//
//         */
//
//        try (
//                java.io.FileWriter output = new FileWriter(file, true);
//                java.util.Scanner input = new Scanner(file);
//
//        ) {
//            while (input.hasNext()) {
//                if (((input.next()).equals(username))) {
//                    throw new UserException("username already exists");
//                }
//                input.nextLine();  //throws away the rest of the line
//            }
//            //makes a string and writes it out to file
//            output.write("\n" + username + " " + password + " "
//                    + name + " " + email + " " + postition);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void makeChangeToUser(String username, String oldPassword,
//                                        String newData, int dataField, File file)
//            throws UserException {
//        /**datafeild is either 1 , 2 , 3
//         1 changes the password
//         2 changes the name
//         3 changes the email
//         dont give 0 or 4
//
//         This can be made into 3 seperate functions if you dont like this design
//         */
//
//
//        //assumes that username is unique
//        ArrayList<String> temp = new ArrayList<>();
//
//        try (
//                java.io.FileWriter output = new FileWriter(file, true);
//                java.util.Scanner input = new Scanner(file);
//
//        ) {
//            String[] buffer;
//
//            while (input.hasNext()) {
//                buffer = (input.nextLine()).split(" ");
//                if (buffer[0].equals(username)) {
//                    if (buffer[1].equals(oldPassword)) {
//                        buffer[dataField] = newData;
//                    } else {
//                        throw new UserException("Password does not match");
//                    }
//                }
//
//                temp.add(buffer[0] + " " + buffer[1] + " "
//                        + buffer[2] + " " + buffer[3] + " " + buffer[4] + "\n");
//
//            }
//            PrintWriter pw = new PrintWriter(file);
//            pw.print("");
//            pw.close();
//
//            for (int i = 0; i < temp.size(); i++) {
//                output.write(temp.get(i));
//            }
//
//
//            //            clear.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void makeAllApplicationsFromFile(File file, ArrayList<JobApplication> applications,
//                                                   ArrayList<Member> members) {
//
//
//        ArrayList<Member> chairList = new ArrayList<Member>();
//        ArrayList<Member> commiteeList = new ArrayList<Member>();
//
//
//        try (
//                java.util.Scanner input = new Scanner(file);
//        ) {
//            input.useDelimiter(",");
//            while (input.hasNext()) {
//                String name = input.next();
//                String jobTtle = input.next();
//                String description = input.next();
//                long startDate = input.nextLong();
//                long endDate = input.nextLong();
//                int numberOfChair = input.nextInt();
//
//
//                for (int i = 0; i < numberOfChair; i++) {
//                    String chairUsername = input.next();
//                    for (int j = 0; j < members.size(); j++) {
//                        if (chairUsername.equals(members.get(j).getUsername())) {
//                            chairList.add(members.get(j));
//                        }
//                    }
//                }
//
//                int numberOfMembers = input.nextInt();
//
//                for (int i = 0; i < numberOfChair; i++) {
//
//                    String commmiteUsername = input.next();
//                    for (int j = 0; j < members.size(); j++) {
//                        if (commmiteUsername.equals(members.get(j).getUsername())) {
//                            commiteeList.add(members.get(j));
//                        }
//                    }
//                }
//
//                applications.add(new JobApplication(name,jobTtle,description, new Date(startDate)
//                        , new Date(endDate), chairList, commiteeList));
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void makeMembersAtStartUp(File file, ArrayList<Member> members){
//        /**
//         * This function will make member class from the information given in the password file
//         * and and it will make changes to the given main array to have the classes
//         * assumes that there are no members in the array
//         */
//
//
//        try (
//                //create a scanner class
//                java.util.Scanner input = new Scanner(file);
//        ) {
//            while (input.hasNext()) {
//                //get the values in the order that they exist in the textfile
//                String username = input.next();
//                String password = input.next();
//                String name = input.next();
//                String email = input.next();
//                String position = input.next();
//
//                //make into a member class and add it to the array
//                members.add( new Member(name,email,username,password,position));
//
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//}
