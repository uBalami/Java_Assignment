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

public class UserLoginWindow extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setHeight(235);
		primaryStage.setWidth(185);
		primaryStage.setResizable(false);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setTitle("User Login Window");

		Label lblTitle, lblUserName, lblEmail, lblPassword;
		TextField txtUserName, txtEmail;
		PasswordField txtPassword;
		Button btnLogin, btnCreate;

		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		pane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));

		lblTitle = new Label("Task Manager");
		lblTitle.relocate(25, 0);
		lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

		lblUserName = new Label("User Name : ");
		lblUserName.relocate(10, 25);
		lblUserName.setFont(Font.font("Arial", FontWeight.BOLD, 11));

		lblEmail = new Label("Email : ");
		lblEmail.relocate(10, 65);
		lblEmail.setFont(Font.font("Arial", FontWeight.BOLD, 11));

		lblPassword = new Label("Password : ");
		lblPassword.relocate(10, 105);
		lblPassword.setFont(Font.font("Arial", FontWeight.BOLD, 11));

		txtUserName = new TextField();
		txtUserName.relocate(10, 40);

		txtEmail = new TextField();
		txtEmail.relocate(10, 80);

		txtPassword = new PasswordField();
		txtPassword.relocate(10, 120);

		btnLogin = new Button("Login");
		btnLogin.relocate(10, 160);
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

		btnCreate = new Button("Create Account");
		btnCreate.relocate(62, 160);
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

		pane.getChildren().addAll(lblTitle, lblUserName, lblEmail, lblPassword, txtUserName, txtEmail, txtPassword,
				btnLogin, btnCreate);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
