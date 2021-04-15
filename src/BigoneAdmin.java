import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Date;

public class BigoneAdmin extends Pane {
	ComboBox<String> cbo = new ComboBox<>();
	// cbo is for choosing service

	TextField tfname = new TextField();
	TextField tfEmail = new TextField();
	TextField tfUsername = new TextField();
	TextField tfTemporaryPassword = new TextField();
	ComboBox<String> cbo2 = new ComboBox<>();
	Button btSubmit = new Button("Submit");
	//cbo2 - for selecting type of new account in manage system users option

	// these are for making new job application faculty search
	TextArea tfa = new TextArea();
	TextField position = new TextField();
	ComboBox<String> cbocc = new ComboBox<>();
	DatePicker startDate = new DatePicker();
	DatePicker endDate = new DatePicker();
	Button btSubmitjob = new Button("Submit");

	// for drop down chair selection
	ArrayList<String> systemMembers = new ArrayList<>();
	// for returning
	ArrayList<String> selectedMembers = new ArrayList<>();

	String[] abc = {};
	ListView<String> lv = new ListView<>(FXCollections.observableArrayList(abc));

	// these are for self account settings
	TextField tfcemail = new TextField();
	TextField tfpassword = new TextField();
	Button btSubmitac = new Button("Submit");

	public Pane setUp() {

		BorderPane pane = new BorderPane();

		// Headings
		String[] services = { "Manage System Users", "Faculty Search", "Account Setting" };

		ObservableList<String> items = FXCollections.observableArrayList(services);
		cbo.getItems().addAll(items);
		cbo.setPrefWidth(400);
		cbo.setValue("---Select Service---");
		pane.setTop(cbo);

		// Manage System Users//

		tfname.setPrefWidth(215);
		HBox hBox = new HBox(15);
		hBox.setPadding(new Insets(20, 10, 10, 10));
		hBox.getChildren().add(new Label("Name"));
		hBox.getChildren().add(tfname);
		hBox.setAlignment(Pos.BASELINE_LEFT);


		tfEmail.setPrefWidth(220);
		HBox hBox2 = new HBox(15);
		hBox2.setPadding(new Insets(20, 10, 10, 10));
		hBox2.getChildren().add(new Label("Email"));
		hBox2.getChildren().add(tfEmail);
		hBox2.setAlignment(Pos.BASELINE_LEFT);


		tfUsername.setAlignment(Pos.CENTER);
		HBox hBox3 = new HBox(15);
		hBox3.setPadding(new Insets(20, 10, 10, 10));
		hBox3.getChildren().add(new Label("Username"));
		hBox3.getChildren().add(tfUsername);
		hBox3.setAlignment(Pos.BASELINE_LEFT);


		HBox hBox4 = new HBox(15);
		hBox4.setPadding(new Insets(20, 10, 10, 10));
		hBox4.getChildren().add(new Label("Password"));
		hBox4.getChildren().add(tfTemporaryPassword);
		hBox4.setAlignment(Pos.BASELINE_LEFT);

		// Account Type
		String[] accountType = { "Admin", "Member" };// "Chair"

		ObservableList<String> items2 = FXCollections.observableArrayList(accountType);
		cbo2.getItems().addAll(items2);
		cbo2.setPrefWidth(222);
		cbo2.setValue("Admin");
		HBox hBox5 = new HBox(15);
		hBox5.setPadding(new Insets(20, 10, 10, 10));
		hBox5.getChildren().add(new Label("Account Type"));
		hBox5.getChildren().add(cbo2);
		hBox5.setAlignment(Pos.BASELINE_LEFT);

		// Button

		btSubmit.setStyle("-fx-border-color: black;");
		HBox hBox6 = new HBox(15);
		hBox6.setPadding(new Insets(20, 10, 10, 10));
		hBox6.getChildren().add(btSubmit);
		hBox6.setAlignment(Pos.BASELINE_LEFT);

		// Now Put it all in a Vbox
		VBox vBox = new VBox(10);
		vBox.setPadding(new Insets(5, 5, 5, 5));
		vBox.getChildren().addAll(hBox5, hBox, hBox2, hBox3, hBox4, hBox6);
		vBox.setAlignment(Pos.BASELINE_LEFT);

		// To Centre it all
		StackPane panet = new StackPane();
		panet.getChildren().add(vBox);
		panet.setStyle("-fx-border-color: black; -fx-background-color: silver;");

//--------------------------------------------------------------------------------------------------------------------//

		// Job Posting Search//
		BorderPane paneb = new BorderPane();
		paneb.setPadding(new Insets(5, 5, 5, 5));

		// TextField

		String description = "Type Job Description Here";
		tfa.setText(description);
		tfa.setEditable(true);
		tfa.setPrefWidth(1000);
		ScrollPane scrollPane = new ScrollPane(tfa);
		scrollPane.setPrefWidth(1100);
		paneb.setCenter(scrollPane);

		// Three Descriptors at top //
		// Position

		String positiondisc = "Position Name";
		position.setText(positiondisc);
		position.setEditable(true);


		ObservableList<String> itemsfromfacultydatabase = FXCollections.observableArrayList(systemMembers); // Array
		cbocc.getItems().addAll(itemsfromfacultydatabase);
		cbocc.setPrefWidth(200);
		cbocc.setValue("---Select Chair---");

		// Put it all together
		HBox hboxra = new HBox();
		hboxra.getChildren().addAll(position, (new Label("    Start Date ")), startDate, (new Label("    End Date ")),
				endDate, (new Label("  ")), cbocc);
		hboxra.setPadding(new Insets(20, 10, 10, 10));
		paneb.setTop(hboxra);

		// Commitee Slection

		btSubmitjob.setStyle("-fx-border-color: black;");
		lv.setPrefSize(400, 400);
		lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


		ScrollPane scrollPaneCommittee = new ScrollPane(lv);
		scrollPaneCommittee.setPrefWidth(1000);

		BorderPane scrollpaneandbutton = new BorderPane();
		scrollpaneandbutton.setPadding(new Insets(20, 10, 10, 10));
		scrollpaneandbutton.setTop(new Label("Committee Selection, (Hold Shift To Select Multiple Members)"));
		scrollpaneandbutton.setCenter(scrollPaneCommittee);
		scrollpaneandbutton.setBottom(btSubmitjob);
		paneb.setBottom(scrollpaneandbutton);

		// ----------------------------------------------------------------------------------------------------------------------------------------------------------

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

//------------------------------------------------------------------------------------

		StackPane pf = new StackPane();

		// I need to Figure out how to make it switch

		cbo.setOnAction(e -> {

			if (cbo.getValue() == "Manage System Users") {
				pf.getChildren().clear();
				pf.getChildren().add(panet);
				pane.setCenter(pf);
			}

			else if (cbo.getValue() == "Faculty Search") {
				pf.getChildren().clear();
				pf.getChildren().add(paneb);
				pane.setCenter(pf);
			}

			else if (cbo.getValue() == "Account Setting") {
				pf.getChildren().clear();
				pf.getChildren().add(paneac);
				pane.setCenter(pf);
			}

		});


		return pane;

	}

	// Account Setting Helpers
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

	// New User Creation Helpers
	public String getName() {
		return tfname.getText();
	}

	public String getNewEmail() {
		return tfEmail.getText();
	}

	public String getTempPass() {
		return tfTemporaryPassword.getText();
	}

	public String getUsername() {
		return tfUsername.getText();
	}

	public String getPosition() {
		return cbo2.getValue();
	}

	public void setCreateError() {
		tfEmail.setText("Something went Wrong");
		tfTemporaryPassword.setText("ERROR - Try Again!");
	}

	// Faculty Search Helpers
	public String getJobDes(){
		return tfa.getText();
	}

	public String getNewJobPosition(){
		return  position.getText();
	}

	public String getNewChair(){
		return cbocc.getValue();
	}

	public Date getStartDate(){
		//return startDate.getValue();
		return new Date(startDate.getValue().toEpochDay());
	}

	public Date getEndDate(){
		return new Date(endDate.getValue().toEpochDay());
	}

	public ArrayList<String> getCommMembers() {
		selectedMembers.addAll(lv.getSelectionModel().getSelectedItems());
		return selectedMembers;
	}

	// Data and Set Up Helpers
	public void setMembers(ArrayList<String> memberNames) {
		cbocc.getItems().addAll(memberNames);
		lv.getItems().addAll(memberNames);
	}



}
