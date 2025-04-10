package mylibs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResultProcessing2 {


	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String HOST = "localhost";
	private static final int PORT = 3306;
	private static final String USER = "root";
	private static final String PASS = "unishbalami";
	private static final String DBNAME = "aspire_fitness";
	private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;

	// method to connect database
	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName(DRIVER); // Load MySQL driver
			conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Database connected successfully.");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Database connection failed.");
			e.printStackTrace();
		}
		return conn;
	}

	// Insert user record into users table
	public void insertUser(String fullName, String phoneNo, String email, String userName, String password) {
		String SQL = "INSERT INTO users (fullName, phoneNo, email, username, password) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = connect(); PreparedStatement pStat = conn.prepareStatement(SQL)) {
			if (conn != null) {
				pStat.setString(1, fullName);
				pStat.setString(2, phoneNo);
				pStat.setString(3, email);
				pStat.setString(4, userName);
				pStat.setString(5, password);
				pStat.executeUpdate();
				System.out.println("User inserted successfully.");
			}
		} catch (SQLException e) {
			System.err.println("Error inserting user.");
			e.printStackTrace();
		}
	}

	// Insert task record into tasks table
	public void insertTask(String taskTitle, String description, LocalDate deadline, String priority, String category,
			LocalDate reminder) {
		String SQL = "INSERT INTO tasks (task_title, task_description, deadline, task_priority, task_category, reminder_date) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = connect(); PreparedStatement pStat = conn.prepareStatement(SQL)) {
			if (conn != null) {
				pStat.setString(1, taskTitle);
				pStat.setString(2, description);
				pStat.setObject(3, deadline);
				pStat.setString(4, priority);
				pStat.setString(5, category);
				pStat.setObject(6, reminder);
				pStat.executeUpdate();
				System.out.println("Task inserted successfully.");
			}
		} catch (SQLException e) {
			System.err.println("Error inserting task.");
			e.printStackTrace();
		}
	}

	// get all tasks from the database
	public List<Tasks> getAllTasks() {
		List<Tasks> tasks = new ArrayList<>();
		String query = "SELECT * FROM tasks";
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				Tasks task = new Tasks();
				task.setId(rs.getInt("id"));
				task.setTitle(rs.getString("task_title"));
				task.setDescription(rs.getString("task_description"));
				task.setDeadline(rs.getTimestamp("deadline").toLocalDateTime());
				task.setPriority(rs.getString("task_priority"));
				task.setCategory(rs.getString("task_category"));
				task.setReminderDate(rs.getTimestamp("reminder_date").toLocalDateTime());
				tasks.add(task);
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving tasks.");
			e.printStackTrace();
		}
		return tasks;
	}

	// Update all task record in tasks table
	public void updateTask(int id, String taskTitle, String description, LocalDate deadline, String priority,
			String category, LocalDate reminder) {
		String SQL = "UPDATE tasks SET task_title = ?, task_description = ?, deadline = ?, task_priority = ?, task_category = ?, reminder_date = ? WHERE id = ?";
		try (Connection conn = connect(); PreparedStatement pStat = conn.prepareStatement(SQL)) {
			if (conn != null) {
				pStat.setString(1, taskTitle);
				pStat.setString(2, description);
				pStat.setObject(3, deadline);
				pStat.setString(4, priority);
				pStat.setString(5, category);
				pStat.setObject(6, reminder);
				pStat.setInt(7, id);
				int affectedRows = pStat.executeUpdate();
				if (affectedRows > 0) {
					System.out.println("Task updated successfully.");
				} else {
					System.out.println("No task found with ID: " + id);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error updating task.");
			e.printStackTrace();
		}
	}

	// Delete task from table or clear the table
	public void deleteTask(int taskId) {
		String query = "DELETE FROM tasks WHERE id = ?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {

			pstmt.setInt(1, taskId);
			int affectedRows = pstmt.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Task deleted successfully.");
			} else {
				System.out.println("No task found with ID: " + taskId);
			}

		} catch (SQLException e) {
			System.err.println("Error deleting task with ID: " + taskId);
			e.printStackTrace();
		}
	}

	// Test the database connection between java fx and mySQL
	public static void main(String[] args) {
		ResultProcessing2 rp = new ResultProcessing2();
		Connection conn = rp.connect();
		if (conn != null) {
			System.out.println("Connected to the database!");
		} else {
			System.out.println("Failed to connect to the database.");
		}
	}
}