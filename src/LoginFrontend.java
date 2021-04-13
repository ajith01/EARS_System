import Backend.src.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

<<<<<<< HEAD
public class LoginFrontend {
=======
public class LoginFrontend extends Pane {

    TextField tfUsername = new TextField();
    TextField tfpassword = new TextField();
    Button btSubmit = new Button("Submit");

>>>>>>> 4cb09df323a569193e1a69a37bfb8209fdbbe8cc
    private User currUser;
    // this user will be used in the main Helper backend file

    public LoginFrontend(){}
//    public static void main(String[] args) {   //main method
//        launch(args);
//    }

    public Pane setUp() {
        Image image = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqxBPBAczjUp-JFp4P5eTcmU5_0ECShsY2mw&usqp=CAU");
        ImageView imageview1 = new ImageView(image);
        imageview1.setFitHeight(100);
        imageview1.setFitWidth(200);
        StackPane paneimage = new StackPane();
        paneimage.getChildren().add(imageview1);
        paneimage.setAlignment(Pos.CENTER);

        // Username
        tfUsername.setPrefWidth(202);
        HBox hBox = new HBox(15);
        hBox.setPadding(new Insets(20, 10, 10, 10));
        hBox.getChildren().add(new Label("Username"));
        hBox.getChildren().add(tfUsername);

        // Password
        tfpassword.setPrefWidth(202);
        HBox hBox2 = new HBox(15);
        hBox2.setPadding(new Insets(20, 10, 10, 10));
        hBox2.getChildren().add(new Label("Password "));
        hBox2.getChildren().add(tfpassword);

        // Button
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

        return pane;

        // TODO: implement button handler and user test function
        // Call set User () after login
    }

    public void setUser(String user){
        // currUser = user;
    }

    public User getUser() {
        return currUser;
    }
}

