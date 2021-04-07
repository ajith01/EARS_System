import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BigOneMember extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {

		BorderPane pane = new BorderPane();
		String[] services = { "Select Service", "Review Applicants", "Account Setting" };
		ComboBox<String> cbo = new ComboBox<>();
		ObservableList<String> items = FXCollections.observableArrayList(services);
		cbo.getItems().addAll(items);
		cbo.setPrefWidth(400);
		cbo.setValue("Select Service");
		pane.setTop(cbo);	
		
//------------------------------------------------------------------------------------------			
		// Applicant Review
		
		BorderPane paneapplicant = new BorderPane();
		
		TextField tfApplicantName = new TextField();
		String tfcApplicantName = "Name";
		tfApplicantName.setText(tfcApplicantName);
		tfApplicantName.setEditable(false);
		tfApplicantName.setPrefWidth(200);
		tfApplicantName.setAlignment(Pos.CENTER);
		paneapplicant.setTop(tfApplicantName);
		
		TextArea tfa = new TextArea();
		String description = "Applicant Info";
		tfa.setText(description);
		tfa.setEditable(false);
		tfa.setPrefWidth(300);
		ScrollPane scrollPane = new ScrollPane(tfa);
		scrollPane.setPrefWidth(300);
		paneapplicant.setCenter(scrollPane);
		
		
		String[] progress = { "Pending Review", "Hired", "Denied" };
		ComboBox<String> cboprogress = new ComboBox<>();
		ObservableList<String> items3 = FXCollections.observableArrayList(progress);
		cboprogress.getItems().addAll(items3);
		cboprogress.setPrefWidth(400);
		cboprogress.setValue("Pending Review");
		paneapplicant.setBottom(cboprogress);	
		
		BorderPane paneapplicantb = new BorderPane();
		
		TextArea tfab = new TextArea();
		String descriptionb = "Applicant Review Comments";
		tfab.setText(descriptionb);
		tfab.setEditable(true);
		tfab.setPrefWidth(500);
		ScrollPane scrollPaneb = new ScrollPane(tfab);
		scrollPaneb.setPrefWidth(500);
		paneapplicantb.setCenter(scrollPaneb);
		
		// Table
		
		//Submit button
		Button btSubmit = new Button("Submit");
		btSubmit.setStyle("-fx-border-color: black;");
		paneapplicantb.setBottom(btSubmit);
			

//------------------------------------------------------------------------------------------	
		// Account Setting

		TextField tfcemail = new TextField();
		String tfcemaildescription = "Change Email";
		tfcemail.setText(tfcemaildescription);
		tfcemail.setEditable(true);
		tfcemail.setPrefWidth(200);
		tfcemail.setAlignment(Pos.CENTER);

		TextField tfpassword = new TextField();
		String tfcpassworddescription = "Change Password";
		tfpassword.setText(tfcpassworddescription);
		tfpassword.setEditable(true);
		tfpassword.setPrefWidth(200);
		tfpassword.setAlignment(Pos.CENTER);

		VBox vboxac = new VBox(15);
		vboxac.setPadding(new Insets(20, 20, 20, 20));
		vboxac.getChildren().addAll(tfcemail, tfpassword);
		vboxac.setAlignment(Pos.CENTER);

		StackPane paneac = new StackPane();
		paneac.getChildren().add(vboxac);

		// ------------------------------------------------------------------------------------------

		// To Centre it all

		Scene scene = new Scene(pane, 800, 600);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		//------------------------------------------------------------------------------------		

				BorderPane pf2 = new BorderPane();
				StackPane pf = new StackPane();

				// I need to Figure out how to make it switch

				cbo.setOnAction(e -> {

					if (cbo.getValue() == "Review Applicants") {
						pf.getChildren().clear();
						pf2.setTop(paneapplicant);
						pf2.setCenter(paneapplicantb);
						pane.setCenter(pf2);
					}

					else if (cbo.getValue() == "Account Setting") {
						pf.getChildren().clear();
						pf.getChildren().add(paneac);
						pane.setCenter(pf);
					}

				});
					

		//----------------------------------------------------------------------------	
		
		
		

	}

}
