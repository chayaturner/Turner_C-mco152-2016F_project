package cookbook;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MenuGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private final JLabel headerLabel;
	private final JButton newRecipeButton, searchRecipeButton, viewAllRecipesButton;
	private final JPanel northPanel, centerPanel;
	private JPanel newRecipePanel;
	
	//Recipe book menu button options
	
	public MenuGUI(){
		setTitle("Recipe Book");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		headerLabel = new JLabel("MY RECIPE BOOK");
		newRecipeButton = new JButton("Add new recipe");
		searchRecipeButton = new JButton("Search my recipes");
		viewAllRecipesButton = new JButton("View all recipes");
		
		
		//NORTH - header 
		northPanel = new JPanel();
		northPanel.add(headerLabel);
		northPanel.add(newRecipeButton);
		northPanel.add(searchRecipeButton);
		northPanel.add(viewAllRecipesButton);
		
		//CENTER - menu
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		//center buttons and add them to panel
		newRecipeButton.setAlignmentX(CENTER_ALIGNMENT);
		searchRecipeButton.setAlignmentX(CENTER_ALIGNMENT);
		viewAllRecipesButton.setAlignmentX(CENTER_ALIGNMENT);
		
		
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
				JLabel name = new JLabel("Enter name:");
				JTextField nameText = new JTextField();
				JLabel description = new JLabel("Enter description:");
				JTextField descText = new JTextField();
				JLabel ingredients = new JLabel("Enter ingredients:");
				JTextArea ingrText = new JTextArea();
				JLabel instructions = new JLabel("Enter instructions:");
				JTextArea instrText = new JTextArea();
				JButton addButton = new JButton("Add your recipe!");
				
				newRecipePanel = new JPanel();
				newRecipePanel.setLayout(new BoxLayout(newRecipePanel, BoxLayout.Y_AXIS));
				
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
				
				centerPanel.add(newRecipePanel);
				centerPanel.updateUI();
				
			}
			
		});
		
		searchRecipeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				
				centerPanel.removeAll();
				
				JLabel searchRecipe = new JLabel("SEARCH RECIPES");
				JLabel searchName = new JLabel("Enter name:");
				JTextField searchNameText = new JTextField();
				JButton searchButton = new JButton("Search for your recipe!");
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
				JLabel allRecipes = new JLabel("ALL MY RECIPES:");
				centerPanel.add(allRecipes);
				centerPanel.updateUI();
			
			}
			
		});
		
	}
	
}
