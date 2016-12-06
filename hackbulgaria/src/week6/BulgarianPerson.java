package week6;

public class BulgarianPerson extends Person{
	private String ucn;
	
	public String getUcn() {
		return ucn;
	}

	/*
	 * A - Person			("Ivan", "Ivanov")
	 * B - BulgarianPerson	("Ivan", "Ivanov", "123123")
	 * C - BulgarianPerson	("Ivan", "Ivanov", "23452345")
	 * 
	 * A.equals(B) -> true
	 * B.equals(A) -> true
	 * 
	 * C.equals(A) -> true
	 * 
	 * A != B
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(obj instanceof BulgarianPerson) {
			BulgarianPerson other = (BulgarianPerson) obj;
			if(this.getFirstName().equals( other.getFirstName() ) && this.getSecondName().equals( other.getSecondName() )){
				return true;
			}
		}
		
		if(this == obj) {
			return true;
		}
		
		
		return false;
	}
}
