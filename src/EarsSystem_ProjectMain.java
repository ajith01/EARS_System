import Backend.src.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.security.cert.TrustAnchor;

public class EarsSystem_ProjectMain extends Application {

    private final int MIN_PASS_LENGHT = 5;
    private User currUser;
    private int userType;
    private final int memberType = 2;
    private final int adminType = 1;

    public static void main(String[] args){

        launch(args);
        System.out.println("System Launched");
        loadData();
        System.out.println("Data Loaded");

    }

    private static void loadData() {
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
                success = loginNewUser(logins.getUser(), logins.getPass());
                System.out.println("User " + getUser() + " has Logged In");
            } catch (EARSException exc){
                logins.setInfoError();
                exc.printStackTrace();
            }
            if(success){
                if(userType == memberType) {
                    primaryStage.setTitle("Member");
                    primaryStage.setScene(sceneMember);
                } else if(userType == adminType){
                    primaryStage.setTitle("Admin");
                    primaryStage.setScene(sceneAdmin);
                } else {
                    //TODO:error
                }
            }
        });
        memberP.btSubmitac.setOnAction(event -> {
            try {
                updateCurrUser(memberP.getEmail(), memberP.getPass());
                System.out.println("User " + getUser() + " has updated Info");
                memberP.setInfoSuccess();
            } catch (EARSException ex){
                memberP.setInfoError();
            }
        });
        adminP.btSubmitac.setOnAction(event -> {
            try {
                updateCurrUser(adminP.getEmail(), adminP.getPass());
                System.out.println("User " + getUser() + " has updated Info");
                // TODO: adminP.setInfoSuccess(); like member has
            } catch (EARSException ex){
                adminP.setInfoError();
            }
        });
        adminP.btSubmit.setOnAction(event -> {
            try {
                createNewSystemUser(adminP.getName(),
                        adminP.getNewEmail(),
                        adminP.getUsername(),
                        adminP.getTempPass(),
                        adminP.getPosition());
                System.out.println("User " + adminP.getName() + " has been Created");
            } catch (EARSException exc){

            }
        });
    }




    // User Helper Functions
    private void createNewSystemUser(String name, String email, String username, String pass, String pos) throws EARSException{
        //TODO: creaet new user and save it to file,, Throw error if fields are not valid like passworkd email etc
        User newUser = new User(name, email, username, pass, pos);
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
    private void setUser() {
        //TODO
        setUserType(1);
    }
    private void setUserType(int t) {
        userType = t;
    }

    // ------- Login Helper Functions -------------------------------
    private boolean loginNewUser(String u, String p) throws EARSException {
        //TODO: handle code
        if(u.equals("")){
            throw new EARSException("USER NOT FOUND!");
        }
        setUser();
        return true;
    }


}
