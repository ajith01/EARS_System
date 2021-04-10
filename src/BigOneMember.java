import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
		// Applicant Review

		BorderPane paneapplicant = new BorderPane();
		paneapplicant.setPadding(new Insets(5, 5, 5, 5)); /// CHange

		String[] Applicants = { "Dan", "Ajith", "Arnav" };
		ComboBox<String> cboapplicants = new ComboBox<>();
		ObservableList<String> apps = FXCollections.observableArrayList(Applicants);
		cboapplicants.getItems().addAll(apps);
		cboapplicants.setPrefWidth(1000);
		cboapplicants.setValue("Select Applicant");

		paneapplicant.setTop(cboapplicants);

		BorderPane paneSpecificApplicant = new BorderPane();
		paneSpecificApplicant.setPadding(new Insets(5, 5, 5, 5)); /// CHange
		paneSpecificApplicant.setPrefHeight(700);
		paneSpecificApplicant.setPrefWidth(1000);

		cboapplicants.setOnAction(e-> {
			if (cboapplicants.getValue() == "Dan") {

				paneSpecificApplicant.setCenter(setupPane(1000));
			}
		} );

		paneapplicant.setCenter(paneSpecificApplicant);
		BorderPane.setAlignment(paneSpecificApplicant, Pos.CENTER);


		//Submit button
		Button btSubmit = new Button("Submit");
		btSubmit.setStyle("-fx-border-color: black;");
		paneapplicant.setBottom(btSubmit);
		BorderPane.setAlignment(btSubmit, Pos.CENTER);


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

		// Button
		Button btSubmitac = new Button("Submit");
		btSubmitac.setStyle("-fx-border-color: black;");

		VBox vboxac = new VBox(15);
		vboxac.setPadding(new Insets(20, 200, 20, 200));
		vboxac.getChildren().addAll(tfcemail, tfpassword,btSubmitac);
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
						pf2.setCenter(paneapplicant);
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

	private Node setupPane(int i) {

		TextField JobName = new TextField(); //Change
		String tfcApplicantName = "Position";
		JobName.setText(tfcApplicantName);
		JobName.setEditable(false);
		JobName.setPrefWidth(1000);
		JobName.setAlignment(Pos.CENTER);

		TextArea tfa = new TextArea();
		String description = "Applicant Info";
		tfa.setText(description);
		tfa.setEditable(false);
		tfa.setPrefWidth(1000);
		ScrollPane scrollPane = new ScrollPane(tfa);
		scrollPane.setPrefWidth(1000);

		TextArea tfab = new TextArea();
		String descriptionb = "Comments for Applicant Review";
		tfab.setText(descriptionb);
		tfab.setEditable(true);
		tfab.setPrefWidth(1000);
		ScrollPane scrollPaneb = new ScrollPane(tfab);
		scrollPaneb.setPrefWidth(1000);

		BorderPane top = new BorderPane();
		top.setPadding(new Insets(5, 5, 5, 5));
		top.setTop(JobName);
		top.setCenter(tfa);
		top.setBottom(tfab);

		String[] progress = { "Pending Review", "Hired", "Denied" };
		ComboBox<String> cboprogress = new ComboBox<>();
		ObservableList<String> items3 = FXCollections.observableArrayList(progress);
		cboprogress.getItems().addAll(items3);
		cboprogress.setPrefWidth(1000);
		cboprogress.setValue("Pending Review");

		TextArea tfAllcomments = new TextArea();
		String descriptionAllcomments = "All Applicant Review Comments";
		tfab.setText(descriptionAllcomments);
		tfab.setEditable(false);
		tfab.setPrefWidth(1000);
		ScrollPane scrollPaneAllcomments = new ScrollPane(tfAllcomments);
		scrollPaneb.setPrefWidth(1000);

		BorderPane mid = new BorderPane();
		mid.setPadding(new Insets(5, 5, 5, 5));
		mid.setTop(cboprogress);
		mid.setCenter(scrollPaneb);

		BorderPane apinfo = new BorderPane();
		apinfo.setPrefHeight(1000);
		apinfo.setPrefWidth(1000);
		apinfo.setPadding(new Insets(5, 5, 5, 5));
		apinfo.setTop(top);
		apinfo.setCenter(mid);


		return apinfo;
	}

}
