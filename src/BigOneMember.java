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
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BigOneMember extends Pane {

	ComboBox<String> cbo = new ComboBox<>();
	ComboBox<String> cboapplicants = new ComboBox<>();
	Button btSubmit = new Button("Submit");
	TextField tfcemail = new TextField();
	TextField tfpassword = new TextField();
	Button btSubmitac = new Button("Submit");
	TextField JobName = new TextField();
	TextArea tfa = new TextArea();
	TextArea comments = new TextArea();
	ComboBox<String> cboprogress = new ComboBox<>();
	TextArea tfAllcomments = new TextArea();

	public Pane setUp() {

		BorderPane pane = new BorderPane();
		String[] services = { "Select Service", "Review Applicants", "Account Setting" };
		ObservableList<String> items = FXCollections.observableArrayList(services);
		cbo.getItems().addAll(items);
		cbo.setPrefWidth(400);
		cbo.setValue("Select Service");
		pane.setTop(cbo);

		
//------------------------------------------------------------------------------------------			
		// Applicant Review
		// Applicant Review

		BorderPane paneapplicant = new BorderPane();
		paneapplicant.setPadding(new Insets(5, 5, 5, 5));

		String[] Applicants = { "Dan", "Ajith", "Arnav" };
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
		btSubmit.setStyle("-fx-border-color: black;");
		paneapplicant.setBottom(btSubmit);
		BorderPane.setAlignment(btSubmit, Pos.CENTER);


//------------------------------------------------------------------------------------------	
		// Account Setting


		String tfcemaildescription = "Change Email";
		tfcemail.setText(tfcemaildescription);
		tfcemail.setEditable(true);
		tfcemail.setPrefWidth(200);
		tfcemail.setAlignment(Pos.CENTER);

		String tfcpassworddescription = "Change Password";
		tfpassword.setText(tfcpassworddescription);
		tfpassword.setEditable(true);
		tfpassword.setPrefWidth(200);
		tfpassword.setAlignment(Pos.CENTER);

		// Button
		btSubmitac.setStyle("-fx-border-color: black;");

		VBox vboxac = new VBox(15);
		vboxac.setPadding(new Insets(20, 200, 20, 200));
		vboxac.getChildren().addAll(tfcemail, tfpassword,btSubmitac);
		vboxac.setAlignment(Pos.CENTER);

		StackPane paneac = new StackPane();
		paneac.getChildren().add(vboxac);

		// ------------------------------------------------------------------------------------------

				BorderPane pf2 = new BorderPane();
				StackPane pf = new StackPane();


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

		return pane;
		

	}

	private Node setupPane(int i) {

		String tfcApplicantName = "Position";
		JobName.setText(tfcApplicantName);
		JobName.setEditable(false);
		JobName.setPrefWidth(1000);
		JobName.setAlignment(Pos.CENTER);


		String description = "Applicant Info";
		tfa.setText(description);
		tfa.setEditable(false);
		tfa.setPrefWidth(1000);
		ScrollPane scrollPane = new ScrollPane(tfa);
		scrollPane.setPrefWidth(1000);


		String descriptioncomments = "Comments for Applicant Review";
		comments.setText(descriptioncomments);
		comments.setEditable(true);
		comments.setPrefWidth(1000);
		ScrollPane scrollPanecomments = new ScrollPane(comments);
		scrollPanecomments.setPrefWidth(1000);
		scrollPanecomments.setPadding(new Insets(5, 5, 5, 5));

		BorderPane top = new BorderPane();
		top.setPadding(new Insets(5, 5, 5, 5));
		top.setTop(JobName);
		top.setCenter(tfa);
		top.setBottom(scrollPanecomments);

		String[] progress = { "Pending Review", "Hired", "Denied" };
		ObservableList<String> items3 = FXCollections.observableArrayList(progress);
		cboprogress.getItems().addAll(items3);
		cboprogress.setPrefWidth(1000);
		cboprogress.setValue("Pending Review");


		String descriptionAllcomments = "All Applicant Review Comments";
		tfAllcomments.setText(descriptionAllcomments);
		tfAllcomments.setEditable(false);
		tfAllcomments.setPrefWidth(1000);
		ScrollPane scrollPaneAllcomments = new ScrollPane(tfAllcomments);
		scrollPaneAllcomments.setPrefWidth(1000);

		BorderPane mid = new BorderPane();
		mid.setPadding(new Insets(5, 5, 5, 5));
		mid.setTop(cboprogress);
		mid.setCenter(scrollPaneAllcomments);

		BorderPane apinfo = new BorderPane();
		apinfo.setPrefHeight(1000);
		apinfo.setPrefWidth(1000);
		apinfo.setPadding(new Insets(5, 5, 5, 5));
		apinfo.setTop(top);
		apinfo.setCenter(mid);


		return apinfo;
	}

}
