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


public class MenuGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private final JLabel headerLabel;
	private final JButton newRecipeButton, searchRecipeButton, viewAllRecipesButton;
	private final JPanel northPanel, centerPanel;
	
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
		
		newRecipeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// allow user to enter new recipe
				
			}
			
		});
		
		searchRecipeButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// allow user to search for a specific recipe
			}
			
		});
		
		viewAllRecipesButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				// allow user to view all current recipes
				
			}
			
		});
		
		
		
		
		//NORTH - header 
		northPanel = new JPanel();
		northPanel.add(headerLabel);
		
		//CENTER - menu
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		newRecipeButton.setAlignmentX(CENTER_ALIGNMENT);
		searchRecipeButton.setAlignmentX(CENTER_ALIGNMENT);
		viewAllRecipesButton.setAlignmentX(CENTER_ALIGNMENT);
		centerPanel.add(newRecipeButton);
		centerPanel.add(searchRecipeButton);
		centerPanel.add(viewAllRecipesButton);
		
		//add content to container
		container.add(northPanel, BorderLayout.NORTH);
		container.add(centerPanel, BorderLayout.CENTER);
		
		
	}
	
}
