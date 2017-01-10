package SQL;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class MySQLHelper {
	private Connection conn;
	
	public MySQLHelper(String dbURL, String dbUser, String dbPass) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql:"+dbURL, dbUser, dbPass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String makeFirstLetterCapital(String s) {
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	
	public boolean insertInto(String table, Object item) {
		Field[] fields = item.getClass().getDeclaredFields();
		
		try {
			LinkedList<String> fielddsList = new LinkedList<String>();
			LinkedList<String> fieldsNameList = new LinkedList<String>();
			for(Field f: fields) {
				fieldsNameList.add(f.getName());
				fielddsList.add((String) item.getClass().getMethod("get" + makeFirstLetterCapital(f.getName())).invoke(item));
			}
			String fieldsSQL = join("", (String[])fielddsList.toArray(); 
			String.filedsNamesSQL = String.join("", (String[]) fieldsNameList.toArray());
			String sql = "INSERT INTO "+table+"(name, age) VALUES";
		
			this.executeSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean deleteFrom(String table, Object object) {
		return false;
	}
	
	public boolean update(String table, Object object) {
		return false;
	}
	
	public List<List<String>> selectFrom(String table) {
		return selectFrom(table, "1");
	}
	
	public List<List<String>> selectFrom(String table, String where) {
		try {
			List<List<String>> result = new LinkedList<>();
			
			Statement stmt = conn.createStatement();
			String sql;
			sql = "Select * FROM " + table;
			ResultSet rs = stmt.executeQuery(sql);
				
			while(rs.next()) {
				ResultSetMetaData md = rs.getMetaData();
				int columnCount = md.getColumnCount();
				List<String> row = new LinkedList<String>();
				
				for (int i = 1; i <= columnCount; i++ ) {
				  String colName = md.getColumnName(i);
				  row.add(rs.getString(colName));
				}
				result.add(row);
			}
		
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
				
	}
}
