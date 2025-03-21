package mylibs;

import java.time.LocalDateTime;

public class Tasks {
	private int id;
	private String tasktitle;
	private String description;
	private LocalDateTime deadline;
	private String priority;
	private String category;
	private LocalDateTime reminderDate;

	public Tasks() {
	}

	public Tasks(int id, String tasktitle, String description, LocalDateTime deadline, String priority, String category,
			LocalDateTime reminderDate) {
		this.id = id;
		this.tasktitle = tasktitle;
		this.description = description;
		this.deadline = deadline;
		this.priority = priority;
		this.category = category;
		this.reminderDate = reminderDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return tasktitle;
	}

	public void setTitle(String title) {
		this.tasktitle = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LocalDateTime getReminderDate() {
		return deadline;

	}

	public void setReminderDate(LocalDateTime reminderDate) {
		this.reminderDate = reminderDate;
	}

	@Override
	public String toString() {
		return "Tasks [id=" + id + ", title=" + tasktitle + ", description=" + description + ", deadline=" + deadline
				+ ", priority=" + priority + ", category=" + category + ", reminderSent=" + reminderDate + "]";
	}
}
