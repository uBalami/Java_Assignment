package TaskManager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UserLoginWindow extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		//setting up main window or primary stage 
		primaryStage.setHeight(235);
		primaryStage.setWidth(185);
		primaryStage.setResizable(false);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setTitle("User Login Window");

		// all the needed UI components 
		Label lblTitle, lblUserName, lblEmail, lblPassword;
		TextField txtUserName, txtEmail;
		PasswordField txtPassword;
		Button btnLogin, btnCreate;

		//Setting up pane 
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		pane.setStyle("-fx-background-color: #2E2E2E;");
		
		//label
		lblTitle = new Label("Task Manager");
		lblTitle.relocate(25, 0);
		lblTitle.setStyle("-fx-text-fill: #00A3E0;");
		lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

		lblUserName = new Label("User Name : ");
		lblUserName.relocate(10, 25);
		lblUserName.setStyle("-fx-text-fill: #CFCFCF;");
		lblUserName.setFont(Font.font("Arial", FontWeight.BOLD, 11));

		lblEmail = new Label("Email : ");
		lblEmail.relocate(10, 65);
		lblEmail.setStyle("-fx-text-fill: #CFCFCF;");
		lblEmail.setFont(Font.font("Arial", FontWeight.BOLD, 11));

		lblPassword = new Label("Password : ");
		lblPassword.relocate(10, 105);
		lblPassword.setStyle("-fx-text-fill: #CFCFCF;");
		lblPassword.setFont(Font.font("Arial", FontWeight.BOLD, 11));

		//textfield
		txtUserName = new TextField();
		txtUserName.relocate(10, 40);

		txtEmail = new TextField();
		txtEmail.relocate(10, 80);

		txtPassword = new PasswordField();
		txtPassword.relocate(10, 120);

		//button eventhandeling 
		btnLogin = new Button("Login");
		btnLogin.relocate(10, 160);
		btnLogin.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-underline: true");
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					new MainWindow().start(new Stage());
					primaryStage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		//button event handeling 
		btnCreate = new Button("Create Account");
		btnCreate.relocate(62, 160);
		btnCreate.setStyle("-fx-background-color: #00C3FF; -fx-text-fill: white;-fx-underline: true");
		btnCreate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					new SignUp().start(new Stage());
					primaryStage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		//add all to ui in pane 
		pane.getChildren().addAll(lblTitle, lblUserName, lblEmail, lblPassword, txtUserName, txtEmail, txtPassword,
				btnLogin, btnCreate);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
