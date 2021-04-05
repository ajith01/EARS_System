import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class guifortest extends Application {

    public static void main(String[] args) {   //main method
        launch(args);
        //start the start up process of reading the rules and creating users

        //open files



    }

    public void start(Stage primaryStage) {
//        // Image
        Image image = new Image("https://4.img-dpreview.com/files/p/E~TS590x0~articles/4698742202/facebook.jpeg");
        ImageView imageview1 = new ImageView(image);
        imageview1.setFitHeight(100);
        imageview1.setFitWidth(200);
        StackPane paneimage = new StackPane();
        paneimage.getChildren().add(imageview1);
        paneimage.setAlignment(Pos.CENTER);

        // Username
        TextField tfUsername = new TextField();
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(20, 10, 10, 10));
        hBox.getChildren().add(new Label("Username"));
        hBox.getChildren().add(tfUsername);

        // Password
        TextField tfpassword = new TextField();
        HBox hBox2 = new HBox(15);
        hBox2.setPadding(new Insets(20, 10, 10, 10));
        hBox2.getChildren().add(new Label("Password "));
        hBox2.getChildren().add(tfpassword);

        // Button
        Button btSubmit = new Button("Submit");
        btSubmit.setStyle("-fx-border-color: black;");
        HBox hBox3 = new HBox(15);
        hBox3.setPadding(new Insets(20, 10, 10, 10));
        hBox3.getChildren().add(btSubmit);
        hBox3.setAlignment(Pos.CENTER);

        // Put 3 elements into Vbox
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.getChildren().addAll(paneimage, hBox, hBox2, hBox3);
//        vBox.getChildren().addAll( hBox, hBox2, hBox3);

        vBox.setAlignment(Pos.CENTER);

        // To Centre it all
        StackPane pane = new StackPane();
        pane.getChildren().add(vBox);
        pane.setStyle("-fx-border-color: black; -fx-background-color: silver;");

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();



        int userval;

        java.io.File usrPwdfile = new File("src/usernameAndPwd.txt");

        System.out.println(usrPwdfile.exists());
        if(!usrPwdfile.exists()){
            //gui for critial error
            System.exit(0);
        }

        System.out.println(logIn("master","pass",usrPwdfile));
        //didnt set up a user value yet, might set up after if required
        //may use a string builder

        makeNewUser("ada","ada","adsa","asda",12,usrPwdfile);

    }

    public static boolean logIn(String username, String password, File file){
        String user;
        String pwd;

        try(
                Scanner input  = new Scanner(file);
        ){
            while(input.hasNext()){
                user = input.next();  //gets the next user name;
//                System.out.println("Username " + user );
                if(user.equals(username)) {
                    pwd = input.next();   //gets the password

                    if(pwd.equals(password)){
                        return true;
                    }
                }
                input.nextLine();   //just skip the password

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void makeNewUser(String username, String password,String name,
                                   String email, int postition, File file){

        String user;
        String pwd;

        try(
                Scanner input  = new Scanner(file);
                java.io.PrintWriter output = new PrintWriter(file);

        ){
            while(input.hasNext()){
                output.write(input.nextLine());  //gets the next user name;
            }  //goes to the end of the list
            output.print(username + " " + password + " "
            + name +" " + email + " " + postition);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
