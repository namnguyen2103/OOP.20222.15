package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import datastructure.List;

public class GUIListFrame extends JFrame {
	
	private List list;
	private JTextField demoList;
	private JTextField statusList;
	private JButton createBtn;
	private JButton insertBtn;
	private JButton sortBtn;
	private JButton findBtn;
	private JButton deleteBtn;
	private JButton resetBtn;
	
	public GUIListFrame() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		cp.add(createMain(), BorderLayout.CENTER);
		cp.add(createMenuBar(), BorderLayout.NORTH);
		cp.add(createOperations(), BorderLayout.SOUTH);

		setVisible(true);
		setTitle("List");
		setSize(1200, 400);
		setLocationRelativeTo(null);
	}
	
	JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
        JMenu optionMenu = new JMenu("Option");
        
        JMenuItem mainMenuItem = new JMenuItem("Main Menu");
        mainMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	GUIListFrame.this.dispose();
            	new GUIMain();
            }
        });
        optionMenu.add(mainMenuItem); 
        
        JMenuItem helpMenuItem = new JMenuItem("Help");
        helpMenuItem.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
            	SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //Turn off metal's use of bold fonts
                        UIManager.put("swing.boldMetal", Boolean.FALSE);
                        new TextFieldDemo("Help").setVisible(true);
                    }
                });
            }
        });
        optionMenu.add(helpMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        optionMenu.add(exitMenuItem);     
        
        menuBar.add(optionMenu);
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        return menuBar;
	}
	
	JPanel createOperations() {
		JPanel operations = new JPanel();
		operations.setLayout(new GridLayout(1, 5, 10, 10));

		createBtn = new JButton("Create");
		createBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

		       JOptionPane.showMessageDialog(null, "A list has been created successfully!.");
		       list = new List();
		       changeText("The list is currently empty. Please insert elements.", "A list has been created!");
		       updateButtons();
		       createBtn.setEnabled(false);
		       insertBtn.setEnabled(true);
			}
		});
		operations.add(createBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		        boolean validInput = false;
		        int element = 0, id = 0;

		        while (!validInput) 
		        {
		            String elementInput = JOptionPane.showInputDialog(null, "Please input an integer for the element:", "Input the element", JOptionPane.QUESTION_MESSAGE);
		            
		            if (elementInput == null) 
		            {
		                return;
		            }
		            String idInput = JOptionPane.showInputDialog(null, "Please input an integer for the ID:", "Input the ID", JOptionPane.QUESTION_MESSAGE);
		            
		            if (idInput == null) 
		            {
		                return;
		            }
		            

		            try 
		            {
		                element = Integer.parseInt(elementInput);
		                id = Integer.parseInt(idInput);
		                validInput = true;
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		        
		        if(list.size() > 0 && id < list.size()) list.insert(element, id);
		        else list.insert(element);

		        JOptionPane.showMessageDialog(null, "The element has been inserted successfully!");

		        changeText("Current list: " + list.toString(), "The element " + element + " has been added!");

		        updateButtons();
		    }
		});
		operations.add(insertBtn);
		insertBtn.setEnabled(false);
		
		sortBtn = new JButton("Sort");
		sortBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.sort();
				JOptionPane.showMessageDialog(null, "The list has been sorted successfully!.");
				changeText("Current list: " + list.toString(), "The list has been sorted.");
				sortBtn.setEnabled(false);
			}
		});
		operations.add(sortBtn);
		sortBtn.setEnabled(false);
		
		findBtn = new JButton("Find");
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validInput = false;
				final int[] elementToFind = new int[1];
				
				while (!validInput) {
		            String input = JOptionPane.showInputDialog(null, "Please input an integer:", "Input the element", JOptionPane.QUESTION_MESSAGE);
		            
		            if (input == null) 
		            {
		                return;
		            }
		            try 
		            {
		            	elementToFind[0] = Integer.parseInt(input);
		                validInput = true;
		            } 
		            catch (NumberFormatException ex)
		            {
		                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
				String[] texts = list.find(elementToFind[0]);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
		            public void run() {		            	
		                GUISlideshow slideshowGUI = new GUISlideshow(texts);
		                slideshowGUI.setVisible(true);
		            }
			    });				
				updateButtons();
				changeText("Current list: " + list.toString(), texts[texts.length-1]);
			}
		});
		operations.add(findBtn);
		findBtn.setEnabled(false);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String numInput = JOptionPane.showInputDialog(null, "Please input the number of the element to delete:", "Input N", JOptionPane.QUESTION_MESSAGE);
		        if (numInput == null) 
		        {
		            return;
		        }

		        try 
		        {
		            int numToDelete = Integer.parseInt(numInput);
		            int deleted = list.delete(numToDelete);

		            if (deleted == -1) 
		            {
		                JOptionPane.showMessageDialog(null, "The element " + numToDelete + " has been deleted successfully!");
		                if (!list.isEmpty()) 
		                {
		                    JOptionPane.showMessageDialog(null, "The element " + numToDelete + " does not exist in the list!", "Error", JOptionPane.ERROR_MESSAGE);
		                    
		                } else {
		                    changeText("The list is currently empty. Please insert elements.", "The element " + numToDelete + " has been deleted! The list is empty now.");
		                }
		            } 
		            else
		            {
	                    changeText("Current list: " + list.toString(), "The element " + numToDelete + " has been deleted!");
		            }
		            
		            updateButtons();
		        } 
		        catch (NumberFormatException ex) 
		        {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid integer for the ID.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		operations.add(deleteBtn);
		deleteBtn.setEnabled(false);
		
		resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIListFrame.this.dispose();
            	new GUIListFrame();
			}
		});
		operations.add(resetBtn);		
		
		return operations;
	}	
	
	JPanel createMain()
	{
		JPanel listDemonstration = new JPanel();
		listDemonstration.setLayout(new BoxLayout(listDemonstration, BoxLayout.Y_AXIS));
		
		String fontFamily = "Verdana";
        int fontStyle = Font.PLAIN;
        int fontSize = 20;
        Font font = new Font(fontFamily, fontStyle, fontSize);
		
		listDemonstration.add(new JLabel("Current List: "));
		demoList = new JTextField(100);
		demoList.setFont(font);
		demoList.setEditable(false);
		listDemonstration.add(demoList);
		
		listDemonstration.add(new JLabel("List Status: "));
		statusList = new JTextField(100);
		statusList.setText("Please create a list first!");
		statusList.setFont(font);
		statusList.setEditable(false);
		listDemonstration.add(statusList);
		
		return listDemonstration;
		
	}
	
	private void changeText(String str1, String str2) {
		demoList.setText(str1);
		statusList.setText(str2);
	}
	
	private void updateButtons() {
		if (list.isEmpty()) {
			deleteBtn.setEnabled(false);
			sortBtn.setEnabled(false);
			findBtn.setEnabled(false);
		}
		else {
			deleteBtn.setEnabled(true);
			sortBtn.setEnabled(true);
			findBtn.setEnabled(true);
		}

	}
}