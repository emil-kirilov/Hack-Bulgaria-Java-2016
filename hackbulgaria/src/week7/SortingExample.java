package week7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingExample {
	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();
		
		students.add(new Student("Ivan", "Penev", 2));
		students.add(new Student("Peter", "Pan", 3));
		students.add(new Student("Jenn", "Music", 4));
		students.add(new Student("Alice", "Cooper", 5));
		students.add(new Student("Alice", "Coopera", 5));
		students.add(new Student("Alice", "Cooperb", 5));
		
		Collections.sort(students);
		for(Student student : students) {
			System.out.println(student.getFirstName() + " " + student.getSecondName());
		}
		
		System.out.println();
		System.out.println("---------------");
		System.out.println();
		
		Collections.sort(students, new Comparator<Student>() {
			
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getGrade() - o2.getGrade();
			}
		});
		
		for(Student student : students) {
			System.out.println(student.getFirstName() + " " + student.getGrade());
		}
	}
}
