package SQL;

public class Teacher{
	private int id;
	private String name;
	private String subject;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toStrin() {
		return "Teacher("+id+","+name+","+subject+")";
	}
}
