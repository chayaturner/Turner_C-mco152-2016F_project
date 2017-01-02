package cookbook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import db.Conn;


public class MenuGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private final JLabel headerLabel;

	private JLabel westLabel;

	private final JLabel eastLabel;
	private final JButton newRecipeButton, searchRecipeButton, viewAllRecipesButton;
	private final JPanel northPanel, centerPanel, westPanel, eastPanel;
	private JTextField nameText, descText, ingrText, instrText;
	private JPanel newRecipePanel;
	private Color darkColor;
	private Color mediumColor;
	private Color lightColor;
	
	//Database connection
	Conn connection;
	
	//Recipe book menu button options
	
	public MenuGUI(){
		setTitle("Recipe Book");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		lightColor = new Color(230, 255, 249);
		mediumColor = new Color(0, 204, 153);
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

		//WEST - picture
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
				
				//TODO: Perhaps add photo background:
				/*
				westLabel = new JLabel("NEW RECIPE");
				westLabel.setFont(new Font("Serif", Font.BOLD, 20));
				westPanel.add(westLabel);
				westPanel.updateUI();
				*/
				
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
				
				/*
				 * TODO: Add a button actionListener that adds the recipe to the database
				 */
				
				addButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						//connect to database
						try {
							connection = new Conn();
						} catch (ClassNotFoundException | SQLException e) {
							JLabel error = new JLabel("Error connecting to database");
							newRecipePanel.add(error);
							centerPanel.add(newRecipePanel);
							centerPanel.updateUI();
						}
						
						//use user input to write to db
						String name = nameText.getText();
						String description = descText.getText();
						String ingredients = ingrText.getText();
						String instructions = instrText.getText();
						
				
						String query = "INSERT INTO 'menu' ('name', 'description', 'ingridients', 'instructions') "
								+ "VALUES ('"
								+ name
								+ "', '"
								+ description
								+ "', '"
								+ ingredients
								+ "', '"
								+ instructions
								+ "'); ";
						
						try {
							connection.WriteDB(query);
						} catch (SQLException | NullPointerException ex) {
							JLabel error = new JLabel("Error writing to database.");
							newRecipePanel.add(error);
							centerPanel.add(newRecipePanel);
							centerPanel.updateUI();
						}
						
						//close db connection
						try {
							connection.CloseDB();
						} catch (ClassNotFoundException | SQLException | NullPointerException e) {
							JLabel error = new JLabel("Error closing database.");
							newRecipePanel.add(error);
							centerPanel.add(newRecipePanel);
							centerPanel.updateUI();
						}
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
				JTextField searchNameText = new JTextField();
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
						centerPanel.updateUI(); //update the gui with new results
					}
					
				});
				
				centerPanel.add(searchRecipe);
				centerPanel.add(searchName);
				centerPanel.add(searchNameText);
				centerPanel.add(searchButton);
				
				//TODO: add an actionListener to search database for recipe with matching name
				
				centerPanel.updateUI();
				
			}
			
		});
		
		viewAllRecipesButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO: List all recipes found in database by name. 
				
				centerPanel.removeAll();
				JLabel allRecipes = new JLabel("ALL MY RECIPES");
				allRecipes.setFont(new Font("Serif", Font.BOLD, 20));
				centerPanel.add(allRecipes);
				centerPanel.updateUI();
				
				//connect to database to retrieve all recipes
				try {
					connection.ReadDB();
				} catch (ClassNotFoundException | SQLException | NullPointerException ex) {
					JLabel error = new JLabel("Error retreiving recipes from database");
					centerPanel.add(error);
					centerPanel.updateUI();
				}
			
			}
			
		});
		
	}
	
}
