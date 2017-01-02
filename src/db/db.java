package db;
import java.sql.SQLException;
public class db {

	public static void main(String[] args)  throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		Conn conn = new Conn();
		conn.CreateDB();
		conn.WriteDB("INSERT INTO 'menu' ('name', 'ingridients') VALUES ('Salad', 'Помидор, огурец, лук, масло, соль'); ");
		conn.ReadDB();
		conn.CloseDB();
	}

}
