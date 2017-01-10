package ORM;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import SQL.MySQLHelper;

public class ObjectsModel<T> {
	MySQLHelper db = new MySQLHelper("//localhost/sqTesting", "root", "omg");
	
	Class callerClass;
	
	public ObjectsModel(Class callerClass) {
		this.callerClass = callerClass;
	}
	
	private String makeFirstLetterCapital(String s) {
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	
	private T convertToObject(List<String> row) {
		try { 
			Field[] fields = callerClass.getDeclaredFields();
			Object instance = callerClass.newInstance();
			
			for (int i = 0; i < fields.length; i++) {
				String methodName = "set" + makeFirstLetterCapital(fields[0].getName());
				Method setter = callerClass.getMethod(methodName, fields[i].getType());
				
				switch(fields[i].getType().getSimpleName()) {
				case "int": { setter.invoke(instance, Integer.parseInt(row.get(i))); break; }
				case "String": { setter.invoke(instance, row.get(i)); break; }
				}
			}
			
			return (T) instance;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<T> all() {
		List<T> res= new LinkedList<T>();
		for (List row: db.selectFrom(callerClass.getSimpleName() + "s")) {
			convertToObject(row);
		}
		return res;
	}
	
	public T getById(int id) {
		String tableName = callerClass.getSimpleName() + "s";
		return convertToObject(db.selectFrom(tableName, "id = " + id).get(0));
	}
}
