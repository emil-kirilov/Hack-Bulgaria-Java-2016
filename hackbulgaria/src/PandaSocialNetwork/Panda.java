package PandaSocialNetwork;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Panda{
	private static class Gender {
		static int male = 0;
		static int female = 1;
		
		private static int set(String gender) throws Exception {
			int result;
			
			if(gender.equals("female")) {
				result = female;
			} else if(gender.equals("male")) {
				result = male;
			} else {
				throw new Exception("Invalid gender");
			}
				
			return result;
		}

		public static String get(int gender) {
			if(gender == 0) {
				return "male";
			} else {
				return "female";
			}
		}
	}
	
	private String name;
	private String email;
	private int gender;
	
	public Panda (String name, String email, String gender) throws Exception {
		this.name = name;
		if(checkEmail(email)) {
			this.email = email;			
		} else {
			throw new Exception("Invalid email");
		}
		
		this.gender = Gender.set(gender);
	}
	
	private boolean checkEmail(String email) {
		boolean result = true;
		
		if(email.length() < 5) {
			result = false;
		} else {
			Pattern p = Pattern.compile("\\w+@\\w+\\.[a-z]+");
			Matcher m = p.matcher(email);
			boolean b = m.matches();
			result = b;
		}
		
		return result;
	}
	
	public boolean isMale() {
		return gender == 0;
	}
	
	public boolean isFemale() {
		return !isMale();
	}
		
	public String name() {
		return name;
	}
	
	public String email() {
		return email;
	}
	
	public String gender() {
		return Gender.get(gender);
	}
	
	public String toString() {
		String result = "";
		result += "name: " + name + "\n";
		result += "email: " + email+ "\n";
		result += "gender: " + Gender.get(gender);
		
		return result;
	}
	
	public boolean equals(Panda other) {
		return (name.equals(other.name) && email.equals(other.email) && gender == other.gender);		
	}
	
	public static void main(String[] args) throws Exception {
		Panda p = new Panda("Ivancho","sf@asfds.com","male");
		System.out.println(p);
		Panda p2 = new Panda("Ivancho","aaa@asd.com","female");
		System.out.println(p2);
	}
}
