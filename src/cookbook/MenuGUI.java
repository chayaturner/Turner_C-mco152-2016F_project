package cookbook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import db.DBConnection;
import net.proteanit.sql.DbUtils;


public class MenuGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private final JLabel headerLabel;
	private JLabel westLabel;
	private final JLabel eastLabel;
	private final JButton newRecipeButton, searchRecipeButton, viewAllRecipesButton;
	private final JPanel northPanel, centerPanel, westPanel, eastPanel;
	private JTextField nameText, descText, ingrText, instrText, searchNameText;
	private JPanel newRecipePanel;
	private Color darkColor;
	private Color mediumColor;
	private Color lightColor;
	
	//Database connection
	//Conn connection;
	DBConnection connection;
	
	//Recipe book menu button options
	
	public MenuGUI(){
		setTitle("Recipe Book");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //open in center of screen
		
		final Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		//set database:
		connection = new DBConnection();
		 
		lightColor = new Color(230, 255, 249);
		mediumColor = new Color(130, 237, 187);
		darkColor = new Color(0, 102, 77);
		
		headerLabel = new JLabel("MY RECIPE BOOK   ");
		headerLabel.setForeground(darkColor);
		headerLabel.setFont(new Font("Jokerman", Font.ITALIC, 30));
		newRecipeButton = new JButton("Add new recipe");
		newRecipeButton.setBackground(darkColor);
		newRecipeButton.setForeground(lightColor);
		newRecipeButton.setFont(new Font("Serif", Font.BOLD, 15));
		searchRecipeButton = new JButton("Search my recipes");
		searchRecipeButton.setBackground(darkColor);
		searchRecipeButton.setForeground(lightColor);
		searchRecipeButton.setFont(new Font("Serif", Font.BOLD, 15));
		viewAllRecipesButton = new JButton("View all recipes");
		viewAllRecipesButton.setBackground(darkColor);
		viewAllRecipesButton.setForeground(lightColor);
		viewAllRecipesButton.setFont(new Font("Serif", Font.BOLD, 15));

		//WEST - space
		westPanel = new JPanel();
		westPanel.setBackground(lightColor);
		westLabel = new JLabel("");
		westLabel.setBackground(lightColor);
		westLabel.setForeground(darkColor);
		westLabel.setFont(new Font("Serif", Font.BOLD, 20));
		westPanel.add(westLabel);
		container.add(westPanel, BorderLayout.WEST);
		
		//EAST - space
		eastPanel = new JPanel();
		eastPanel.setBackground(lightColor);
		eastLabel = new JLabel("");
		eastLabel.setBackground(lightColor);
		eastLabel.setForeground(darkColor);
		eastPanel.add(eastLabel);
		container.add(eastPanel, BorderLayout.EAST);
		
		//NORTH - header 
		northPanel = new JPanel();
		northPanel.setBackground(lightColor);
		northPanel.add(headerLabel);
		northPanel.add(newRecipeButton);
		northPanel.add(searchRecipeButton);
		northPanel.add(viewAllRecipesButton);
		
		//CENTER - menu
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setBackground(lightColor);
		
		//add all content to container
		container.add(northPanel, BorderLayout.NORTH);
		container.add(centerPanel, BorderLayout.CENTER);
		
		//Action Listeners for Buttons
		
		newRecipeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// allow user to enter new recipe
				
				centerPanel.removeAll();
				
				//set up labels for new recipe
				JLabel newRecipe = new JLabel("NEW RECIPE");
				newRecipe.setFont(new Font("Serif", Font.BOLD, 20));
				
				JLabel name = new JLabel("Enter name:");
				nameText = new JTextField();
				JLabel description = new JLabel("Enter description:");
				descText = new JTextField();
				JLabel ingredients = new JLabel("Enter ingredients:");
				ingrText = new JTextField();
				JLabel instructions = new JLabel("Enter instructions:");
				instrText = new JTextField();
				JButton addButton = new JButton("Add your recipe!");
				addButton.setBackground(darkColor);
				addButton.setForeground(lightColor);
				addButton.setFont(new Font("Serif", Font.BOLD, 15));
				
				newRecipePanel = new JPanel();
				newRecipePanel.setLayout(new BoxLayout(newRecipePanel, BoxLayout.Y_AXIS));
				newRecipePanel.setBackground(lightColor);
				newRecipePanel.add(newRecipe);
				newRecipePanel.add(name);
				newRecipePanel.add(nameText);
				newRecipePanel.add(description);
				newRecipePanel.add(descText);
				newRecipePanel.add(ingredients);
				newRecipePanel.add(ingrText);
				newRecipePanel.add(instructions);
				newRecipePanel.add(instrText);
				newRecipePanel.add(addButton);

				addButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						//connect to database
						connection.insertDB(nameText.getText(), descText.getText(), ingrText.getText(), instrText.getText());						
					}					
				});
				
				centerPanel.add(newRecipePanel);
				centerPanel.updateUI(); //update gui with new results				
			}			
		});
		
		//SEARCH RECIPE BUTTON
		searchRecipeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				
				centerPanel.removeAll();
				
				JLabel searchRecipe = new JLabel("SEARCH RECIPES");
				searchRecipe.setFont(new Font("Serif", Font.BOLD, 20));
				JLabel searchName = new JLabel("Enter name:");
				searchNameText = new JTextField();
				JButton searchButton = new JButton("Search for your recipe!");
				searchButton.setBackground(darkColor);
				searchButton.setForeground(lightColor);
				searchButton.setFont(new Font("Serif", Font.BOLD, 15));
				
				searchButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						centerPanel.removeAll();
						JLabel recipeSearchResults = new JLabel("Results for: " + searchNameText.getText());
						centerPanel.add(recipeSearchResults);

						//create scrollable table to populate with search results
						JTable table = new JTable();
						table.setBackground(mediumColor);
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.getViewport().setBackground(mediumColor);
						scrollPane.setViewportView(table);
						
						//get all resulting recipes from db and populate table
						ResultSet results = connection.searchDB(searchNameText.getText());
						if(results!=null){
						table.setModel(DbUtils.resultSetToTableModel(results));
						} else {
							JOptionPane.showMessageDialog(null, "Error: no results from database");
						}
						
						centerPanel.add(table);
						centerPanel.add(scrollPane);
						
						centerPanel.updateUI(); //update the gui with new results
					}					
				});						
					
				centerPanel.add(searchRecipe);
				centerPanel.add(searchName);
				centerPanel.add(searchNameText);
				centerPanel.add(searchButton);
				centerPanel.updateUI();				
			}			
		});
		
		viewAllRecipesButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {

				centerPanel.removeAll();
				JLabel allRecipes = new JLabel("ALL MY RECIPES");
				allRecipes.setFont(new Font("Serif", Font.BOLD, 20));
				
				//scrollable table to hold all recipes
				JTable table = new JTable();
				table.setBackground(mediumColor);
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.getViewport().setBackground(mediumColor);
				scrollPane.setViewportView(table);
				
				centerPanel.add(allRecipes);
				centerPanel.add(table);
				centerPanel.add(scrollPane);
				
				//get all recipes from db and populate table
				ResultSet results = connection.getAll();
				if(results!=null){
				table.setModel(DbUtils.resultSetToTableModel(results));
				} else {
					JOptionPane.showMessageDialog(null, "Error: no results from database");
				}
				
				centerPanel.updateUI();
			}			
		});
		
	}
}	
