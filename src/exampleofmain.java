import javafx.application.Application;

//public class exampleofmain extends Application{

    import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

    public class exampleofmain extends Application {
        public guifortest gui = new guifortest();
        public BigoneAdmin gui2 = new BigoneAdmin();


        public static void main(String[] args) {
            launch(args);
        }


        @Override
        public void start(Stage primaryStage) throws Exception {

            Pane p = new Pane();


            Scene scene = new Scene(p, 400, 400);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();

//            Scene scene2 = new Scene(gui2.wow(), 1000, 1000);
//
//
//            gui.btSubmit.setOnAction(e-> {
//                primaryStage.setTitle("LMAO");
//                primaryStage.setScene(scene2);
//            });


        }

    }
//}
