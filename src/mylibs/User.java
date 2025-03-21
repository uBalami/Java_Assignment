package mylibs;

public class User {

	private String full_name;
	private int phone_no;
	private String email;
	private String username;
	private String password;

	public User(String full_name, int phone_no, String email, String username, String password) {
		super();
		this.full_name = full_name;
		this.phone_no = phone_no;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public int getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(int phone_no) {
		this.phone_no = phone_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [full_name=" + full_name + ", phone_no=" + phone_no + ", email=" + email + ", username=" + username
				+ ", password=" + password + "]";
	}

}
