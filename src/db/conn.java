package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conn {
	public static Connection connect;
	public static Statement state;
	public static ResultSet resSet;

	// Connecting to database
	public static void Conn() throws ClassNotFoundException, SQLException {
		connect = null;
		Class.forName("org.sqlite.JDBC");
		connect = DriverManager.getConnection("jdbc:sqlite:cooking db.s3db"); //connection to our database

		System.out.println("The Base is connected");
	}

	// -Creating a table
	public static void CreateDB() throws ClassNotFoundException, SQLException {
		state = connect.createStatement();// creates the object sending sql
										// statement to the database

		state.execute(// Выполняет заданную инструкцию SQL, которая может
						// возвращать несколько результатов.
				"CREATE TABLE if not exists 'menu' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'description' text, 'ingridients' text, 'instructions' text );");

		System.out.println("The table is created");
	}

	// Filling up the table
	public static void WriteDB() throws SQLException {

		state.execute(
				"INSERT INTO 'menu' ('name', 'ingridients') VALUES ('Salad', 'Помидор, огурец, лук, масло, соль'); ");
		state.execute("INSERT INTO 'menu' ('name', 'ingridients') VALUES ('Falafel', 'Бобы, масло, петрушка'); ");
		state.execute("INSERT INTO 'menu' ('name', 'ingridients') VALUES ('Humus', 'Нут, тхина, лимон, масло'); ");

		System.out.println("The table is filled");
	}

	// Table output
	public static void ReadDB() throws ClassNotFoundException, SQLException {
		resSet = state.executeQuery("SELECT * FROM menu");

		while (resSet.next()) {
			int id = resSet.getInt("id");
			String name = resSet.getString("name");
			String ingridients = resSet.getString("ingridients");
			System.out.println("ID = " + id);
			System.out.println("name = " + name);
			System.out.println("ingridients = " + ingridients);
			System.out.println();
		}

		System.out.println("The table is shown");
	}

	// Close
	public static void CloseDB() throws ClassNotFoundException, SQLException {

		connect.close();
		state.close();
		resSet.close();

		System.out.println("End of connection");
	}
}
