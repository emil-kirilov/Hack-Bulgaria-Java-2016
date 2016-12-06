package week7;

public class Student implements Comparable<Student>{
	private final String firstName;
	private final String secondName;
	private final int grade;
	
	public Student(String firstName, String secondName, int grade) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.grade = grade;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public int getGrade() {
		return grade;
	}

	@Override
	public int compareTo(Student o) {
		int comparison = this.firstName.compareTo( o.getFirstName());
		if ( comparison != 0) {
			return comparison;
		} else {
			return this.secondName.compareTo( o.getSecondName());
		}
	}
}
