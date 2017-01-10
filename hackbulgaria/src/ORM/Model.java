package ORM;

import SQL.MySQLHelper;

public class Model<T> {
	MySQLHelper db = new MySQLHelper("//localhost/sqTesting", "root", "omg");
	public ObjectsModel<T> objects() {
		return new ObjectsModel<T>(this.getClass());
	}
	
	public void save() {
		db.insertInto(this.getClass().getSimpleName() + "s", this);
	}
}
