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

import java.util.ArrayList;

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
	BorderPane paneSpecificApplicant = new BorderPane();
	private ArrayList<String> appNames = new ArrayList<>();
	private boolean isChair = false;

	public Pane setUp() {

		BorderPane pane = new BorderPane();
		String[] services = { "Review Applicants", "Account Setting" };
		ObservableList<String> items = FXCollections.observableArrayList(services);
		cbo.getItems().addAll(items);
		cbo.setPrefWidth(400);
		cbo.setValue("---Select---");
		pane.setTop(cbo);

		
//------------------------------------------------------------------------------------------			
		// Applicant Review
		// Applicant Review

		BorderPane paneapplicant = new BorderPane();
		paneapplicant.setPadding(new Insets(5, 5, 5, 5));

		// FIXME: same issue as admin file. using the hlper to add appnames doesnt seem to work
		// only these 3 names are gonna show up
		String[] appNames = {};// = { "Dan", "Ajith", "Arnav" };
		ObservableList<String> apps = FXCollections.observableArrayList();
		cboapplicants.getItems().addAll(apps);
		cboapplicants.setPrefWidth(1000);
		cboapplicants.setValue("---Select Applicant---");

		paneapplicant.setTop(cboapplicants);

		paneSpecificApplicant.setPadding(new Insets(5, 5, 5, 5)); /// CHange
		paneSpecificApplicant.setPrefHeight(700);
		paneSpecificApplicant.setPrefWidth(1000);

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
					

		return pane;

	}


	private Node setupPane(int i) {


		JobName.setEditable(false);
		JobName.setPrefWidth(1000);
		JobName.setAlignment(Pos.CENTER);

		tfa.setEditable(false);
		tfa.setPrefWidth(1000);
		ScrollPane scrollPane = new ScrollPane(tfa);
		scrollPane.setPrefWidth(1000);
		scrollPane.isDisabled();


		String descriptioncomments = "Enter Your Comments for Applicant Review";
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
		cboprogress.getItems().removeAll();
		cboprogress.getItems().addAll(items3);
		cboprogress.setPrefWidth(1000);
		cboprogress.setValue("Pending Review");

		tfAllcomments.setEditable(false);
		tfAllcomments.setPrefWidth(1000);

		if (!isChair){
			cboprogress.hide();
			tfAllcomments.setVisible(false);
		}

		ScrollPane scrollPaneAllcomments = new ScrollPane(tfAllcomments);
		scrollPaneAllcomments.setPrefWidth(1000);

		BorderPane mid = new BorderPane();
		mid.setPadding(new Insets(5, 5, 5, 5));

		if(isChair){
			mid.setTop(cboprogress);
			mid.setCenter(scrollPaneAllcomments);
		}

		BorderPane apinfo = new BorderPane();
		apinfo.setPrefHeight(1000);
		apinfo.setPrefWidth(1000);
		apinfo.setPadding(new Insets(5, 5, 5, 5));
		apinfo.setTop(top);
		apinfo.setCenter(mid);


		return apinfo;
	}

	public String getEmail() {
		return tfcemail.getText();
	}
	public String getPass() {
		return tfpassword.getText();
	}

	public void setInfoError() {
		tfcemail.setText("...@example.com");
		tfpassword.setText("More than 4 characters");
	}

	public void setInfoSuccess() {
		//TODO; if we display success mesaage next time the info will not show up
		//tfpassword.setText("All Changes saved Successfully");
	}

	public void setApplications(ArrayList<String> appNames) {
		cboapplicants.getItems().addAll(appNames);
	}

	public void setChair(boolean val){
		isChair = val;
	}

	public void showCurrApplication(){
		paneSpecificApplicant.setCenter(setupPane(1000));
	}

	public void setAllComments(String s){
		tfAllcomments.setText(s);
	}

	public void setAppDesc(String desc) {
		tfa.setText(desc);
	}

	public void setJobTitle(String job) {
		JobName.setText(job);
	}
	public String getJobTitle(){
		return JobName.getText();
	}

	public String getNewCommet() {
		return comments.getText();
	}
	public String getCurrAppName(){
		return cboapplicants.getValue();
	}
}
