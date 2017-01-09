package db;

import java.sql.*;
import javax.swing.*;

public class DBConnection {

	private Connection con = null;
	
	public Connection dbConnect(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\CMT\\Desktop\\Touro\\Workspace\\Turner_C-mco152-2016F_project\\src\\db\\RecipesDB.sqlite");
			JOptionPane.showMessageDialog(null,  "Successful Connection");
			return con;
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
			return null;
		}
	}
	
	public ResultSet searchDB(String name){
		con = dbConnect();
		
		//code for inside button click to check against database:
		//put in try catch in case it errors
		try{
			String query = "select * from Recipe where name=?";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, name); //index of the "?", and text you are selecting - Ex: searchTextFeild.Text()
			//index are 1 based. 
			//if you want to select more things from the database, then put ? for the next ones (description=?)
			//and then use statement.setString(2, "..."); - 2 for the next index of ?'s
			
			ResultSet results = statement.executeQuery();
			
			//close connection to database:
			//resultSet.close();
			//statement.close();
			
			return results;
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
			return null;
		}
	}
	
	public void insertDB(String name, String desc, String ingr, String instr) {
		con = dbConnect();
		
		//code for inside button click to insert into database:
		//put in try catch in case it errors
		try{
			
			String query = "insert into Recipe (Name,Description,Ingredients,Instructions) values (?,?,?,?)";
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setString(1, name);
			statement.setString(2, desc);
			statement.setString(3, ingr);
			statement.setString(4, instr);
			statement.execute();
			
			JOptionPane.showMessageDialog(null, "Inserted Data");
			
			//close connection to database:
			statement.close();
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}		
	}
	
	public ResultSet getAll(){
		con = dbConnect();
		ResultSet results = null;
		ResultSet allRecipes = null;
	//code for button click to retrieve all recipes in db:
		try{
			String query = "select * from recipe";
			PreparedStatement statement = con.prepareStatement(query);
			results = statement.executeQuery();
			
			JOptionPane.showMessageDialog(null, "All Recipes");
			allRecipes = results;
			
			//close connection
			//statement.close();
			
		} catch (Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
		
		return allRecipes;
		
	}
}
