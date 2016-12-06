package week6;

public class Person {
	private String firstName;
	private final String secondName = "pedro";
	public int timeAccessed;

	public String getFirstName() {
		if(firstName == null) {
			firstName = "Gosho";
		}
		
		return firstName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(this == obj) {
			return true;
		}
		
		// ne otchita nasledqvane
		//if(this.getClass().equals( obj.getClass() )) {
		if(obj instanceof Person) {
			Person other = (Person)obj;
			if(this.getFirstName().equals( other.getFirstName() ) && this.secondName.equals( other.secondName )){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return getFirstName().hashCode();
	}
}
