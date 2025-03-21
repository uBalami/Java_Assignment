package TaskManager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mylibs.ResultProcessing2;

public class SignUp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setHeight(320);
		primaryStage.setWidth(234); 
		primaryStage.setResizable(false);
		primaryStage.setAlwaysOnTop(true);

		Label lblTitle, lblFullName, lblPhoneno, lblEmail, lblUsername, lblPassword;
		TextField txtFullName, txtPhoneno, txtEmail, txtUsername;
		PasswordField txtPassword;
		Button btnSignUp;

		// Label
		lblTitle = new Label("Task Manager");
		lblTitle.relocate(58, 5);
		lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

		lblFullName = new Label("Full Name:");
		lblFullName.relocate(10, 35);
		lblFullName.setFont(Font.font("Arial", FontWeight.BOLD, 11));

		lblPhoneno = new Label("Phone Number:");
		lblPhoneno.relocate(10, 75);
		lblPhoneno.setFont(Font.font("Arial", FontWeight.BOLD, 11));

		lblEmail = new Label("Email:");
		lblEmail.relocate(10, 115);
		lblEmail.setFont(Font.font("Arial", FontWeight.BOLD, 11));

		lblUsername = new Label("Username:");
		lblUsername.relocate(10, 155);
		lblUsername.setFont(Font.font("Arial", FontWeight.BOLD, 11));

		lblPassword = new Label("Password:");
		lblPassword.relocate(10, 195);
		lblPassword.setFont(Font.font("Arial", FontWeight.BOLD, 11));

		// TextFields
		txtFullName = new TextField();
		txtFullName.relocate(10, 50);
		txtFullName.setPrefWidth(200);

		txtPhoneno = new TextField();
		txtPhoneno.relocate(10, 90);
		txtPhoneno.setPrefWidth(200);

		txtEmail = new TextField();
		txtEmail.relocate(10, 130);
		txtEmail.setPrefWidth(200);

		txtUsername = new TextField();
		txtUsername.relocate(10, 170);
		txtUsername.setPrefWidth(200);

		// Password Field
		txtPassword = new PasswordField();
		txtPassword.relocate(10, 210);
		txtPassword.setPrefWidth(200);

		// Button
		btnSignUp = new Button("Sign Up");
		btnSignUp.relocate(65, 250);
		btnSignUp.setPrefWidth(100);

		// Button EventHandler
		btnSignUp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String fullName = txtFullName.getText();
				String phoneNo = txtPhoneno.getText();
				String email = txtEmail.getText();
				String username = txtUsername.getText();
				String password = txtPassword.getText();

				if (fullName.isEmpty() || phoneNo.isEmpty() || email.isEmpty() || username.isEmpty()
						|| password.isEmpty()) {
					System.out.println("Please fill all fields so u can proceed ahead");
					return;
				}

				// Insert user data into the database
				ResultProcessing2 dbHandler = new ResultProcessing2();
				dbHandler.insertUser(fullName, phoneNo, email, username, password);
				System.out.println("User registered successfully!");
				try {
					new UserLoginWindow().start(new Stage());
					primaryStage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

		// Pane Setup
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		pane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));

		// Adding Elements to Pane
		pane.getChildren().addAll(lblTitle, lblFullName, lblPhoneno, lblEmail, lblUsername, lblPassword, txtFullName,
				txtPhoneno, txtEmail, txtUsername, txtPassword, btnSignUp);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
