
import java.util.ArrayList;
import java.util.List;

public class Student {
	private int studentId;
	private String firstName;
	private String lastName;
	private List<Course> courses;

	public Student(int studentId, String firstName, String lastName) {
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.courses = new ArrayList<Course>();
	}

	public int getStudentId() {
		return studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void enroll(Course course) {
		courses.add(course);
	}

	public String toString() {
		return "Student Name: " + getFirstName() + " " + getLastName();
	}
}
