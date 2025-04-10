
package TaskManager;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import mylibs.ResultProcessing2;
import mylibs.Tasks;

public class MainWindow extends Application {
	private TableView<Tasks> taskTable = new TableView<>();

	@Override
	public void start(Stage primaryStage) {
		
		//setting up the main window or primary stage
		primaryStage.setHeight(620);
		primaryStage.setWidth(1200);
		primaryStage.setResizable(false);
		primaryStage.setAlwaysOnTop(true);

		// Labels
		Label lblTitle = new Label("Task Manager");
		lblTitle.relocate(140, 10);
		lblTitle.setStyle("-fx-text-fill: #00A3E0;");
		lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

		Label lblTaskTitle = new Label("Task Title:");
		lblTaskTitle.relocate(25, 45);
		lblTaskTitle.setStyle("-fx-text-fill: #CFCFCF;");
		lblTaskTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		Label lblDescription = new Label("Task Description:");
		lblDescription.relocate(25, 85);
		lblDescription.setStyle("-fx-text-fill: #CFCFCF;");
		lblDescription.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		Label lblDeadline = new Label("Deadline:");
		lblDeadline.relocate(25, 270);
		lblDeadline.setStyle("-fx-text-fill: #CFCFCF;");
		lblDeadline.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		Label lblPriority = new Label("Priority:");
		lblPriority.relocate(25, 310);
		lblPriority.setStyle("-fx-text-fill: #CFCFCF;");
		lblPriority.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		Label lblCategory = new Label("Category:");
		lblCategory.relocate(25, 350);
		lblCategory.setStyle("-fx-text-fill: #CFCFCF;");
		lblCategory.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		Label lblReminder = new Label("Reminder:");
		lblReminder.relocate(25, 390);
		lblReminder.setStyle("-fx-text-fill: #CFCFCF;");
		lblReminder.setFont(Font.font("Arial", FontWeight.BOLD, 12));

		// TextFields & TextArea
		TextField txtTaskTitle = new TextField();
		txtTaskTitle.relocate(25, 60);
		txtTaskTitle.setPrefWidth(250);

		TextArea txtDescription = new TextArea();
		txtDescription.relocate(25, 100);
		txtDescription.setPrefSize(250, 150);

		// DatePicker
		DatePicker dateDeadline = new DatePicker();
		dateDeadline.relocate(25, 285);
		dateDeadline.setPrefWidth(200);

		DatePicker dateReminder = new DatePicker();
		dateReminder.relocate(25, 405);
		dateReminder.setPrefWidth(200);

		// ComboBoxes
		ComboBox<String> priorityBox = new ComboBox<>();
		priorityBox.getItems().addAll("Low", "Medium", "High");
		priorityBox.setValue("Low");
		priorityBox.relocate(25, 325);
		priorityBox.setPrefWidth(150);

		ComboBox<String> categoryBox = new ComboBox<>();
		categoryBox.getItems().addAll("School", "Work", "Medicine");
		categoryBox.setValue("School");
		categoryBox.relocate(25, 365);
		categoryBox.setPrefWidth(150);

		// TableView setup
		taskTable.relocate(400, 40);
		taskTable.setPrefWidth(800);
		taskTable.setPrefHeight(475);

		// Columns for TableView

		TableColumn<Tasks, String> titleCol = new TableColumn<>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));

		TableColumn<Tasks, String> descCol = new TableColumn<>("Description");
		descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

		TableColumn<Tasks, String> deadlineCol = new TableColumn<>("Deadline");
		deadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));

		TableColumn<Tasks, String> priorityCol = new TableColumn<>("Priority");
		priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));

		TableColumn<Tasks, String> categoryCol = new TableColumn<>("Category");
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

		TableColumn<Tasks, String> reminderCol = new TableColumn<>("Reminder");
		reminderCol.setCellValueFactory(new PropertyValueFactory<>("reminderDate"));

		taskTable.getColumns().setAll(titleCol, descCol, deadlineCol, priorityCol, categoryCol, reminderCol);
		titleCol.setPrefWidth(150);
		descCol.setPrefWidth(300);
		deadlineCol.setPrefWidth(77);
		reminderCol.setPrefWidth(77);
		loadTasks(); // Load tasks into TableView

		// Save Button
		Button btnSave = new Button("Save");
		btnSave.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-underline: true");
		btnSave.relocate(25, 450);
		btnSave.setPrefWidth(120);

		// Clear Button
		Button btnClear = new Button("Clear");
		btnClear.relocate(25, 480);
		btnClear.setPrefWidth(120);
		btnClear.setStyle("-fx-background-color: #E63946; -fx-text-fill: white; -fx-underline: true");

		// Clear Button EventHandler
		btnClear.setOnMouseClicked(event -> {
			Tasks selectedTask = taskTable.getSelectionModel().getSelectedItem();

			ResultProcessing2 dbHandler = new ResultProcessing2();
			dbHandler.deleteTask(selectedTask.getId());

			taskTable.getItems().remove(selectedTask);
		});

		// Edit Button
		Button btnEdit = new Button("Edit");
		btnEdit.relocate(25, 510);
		btnEdit.setStyle("-fx-background-color: #00C3FF; -fx-text-fill: white;-fx-underline: true");
		btnEdit.setPrefWidth(120);
		btnEdit.setOnAction(event -> {
			Tasks selectedTask = taskTable.getSelectionModel().getSelectedItem();

			if (selectedTask == null) {
				System.out.println("Please select a task to edit.");
				return;
			}
			txtTaskTitle.setText(selectedTask.getTitle());
			txtDescription.setText(selectedTask.getDescription());
			priorityBox.setValue(selectedTask.getPriority());
			categoryBox.setValue(selectedTask.getCategory());
			taskTable.getSelectionModel().clearSelection();
		});

		Button btnUpdate = new Button("Update");
		btnUpdate.relocate(25, 540);
		btnUpdate.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
		btnUpdate.setPrefWidth(120);
		btnUpdate.setOnAction(event -> {
			Tasks selectedTask = taskTable.getSelectionModel().getSelectedItem();

			if (selectedTask == null) {
				System.out.println("Please select a task to Update.");
				return;
			}
			selectedTask.setTitle(txtTaskTitle.getText());
			selectedTask.setDescription(txtDescription.getText());
			selectedTask.setPriority(priorityBox.getValue());
			selectedTask.setCategory(categoryBox.getValue());
			txtTaskTitle.clear();
			txtDescription.clear();
			dateDeadline.setValue(null);
			priorityBox.setValue("Low");
			categoryBox.setValue("School");
			dateReminder.setValue(null);
			taskTable.refresh();
		});
		// Button EventHandler
		btnSave.setOnAction((ActionEvent actionEvent) -> {
			String taskTitle = txtTaskTitle.getText();
			String description = txtDescription.getText();
			LocalDate deadline = dateDeadline.getValue();
			String priority = priorityBox.getValue();
			String category = categoryBox.getValue();
			LocalDate reminder = dateReminder.getValue();

			// Insert task data into the database
			ResultProcessing2 dbHandler = new ResultProcessing2();
			dbHandler.insertTask(taskTitle, description, deadline, priority, category, reminder);

			// Refresh TableView
			loadTasks();

			// Clear fields after saving
			txtTaskTitle.clear();
			txtDescription.clear();
			dateDeadline.setValue(null);
			priorityBox.setValue("Low");
			categoryBox.setValue("School");
			dateReminder.setValue(null);
		});

		// Pane Setup
		Pane pane = new Pane();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		pane.setStyle("-fx-background-color: #2E2E2E;");

		// Adding UI component to Pane
		pane.getChildren().addAll(btnUpdate, lblTitle, lblTaskTitle, lblDescription, lblDeadline, lblPriority,
				lblCategory, lblReminder, txtTaskTitle, txtDescription, dateDeadline, dateReminder, priorityBox,
				categoryBox, btnSave, taskTable, btnClear, btnEdit);

		primaryStage.show();
	}

	private void loadTasks() {
		ResultProcessing2 dbHandler = new ResultProcessing2();
		ObservableList<Tasks> tasks = FXCollections.observableArrayList(dbHandler.getAllTasks());
		taskTable.setItems(tasks);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
