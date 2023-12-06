public class Main {
    public static void main(String[] args) {
        
        Instructor instructor1 = new Instructor(1, "Ali", "Aloulou");
        Instructor instructor2 = new Instructor(2, "Ahmed", "Hamdi");
        Course course1 = new Course(101, "Math", instructor1);
        Course course2 = new Course(102, "Science", instructor2);
        Student student = new Student(1, "Alice", "Smith");

     
        student.enroll(course1);
        student.enroll(course2);

     
        System.out.println(student);
        System.out.println("Enrolled Courses:");
        for (Course course : student.getCourses()) {
            System.out.println(course);
            System.out.println(course.getInstructor());
        }
    }
}
