import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

//import Client.GUI_board.block;

public class GUI_board extends JFrame{
	
	public Client client;
	private JButton start_Game;
	private JTextField name;
	private JMenu menu;
	private JMenuBar mb;
	private JMenuItem exit,restart;
	
	public Button[][] buttons;// a 3x3 Jbutton
	private String currentUserName;
	//private JFrame fr;
	//private String symbol = "O";
	//public PrintWriter output;
		
	public GUI_board()
	{
		super("Tic-Tac-Toe");
		JPanel buttonPanel = new JPanel(new GridLayout(4,3));
	    buttons = new Button[3][3];
	    int row,col;
	    
	    
	    for(row = 0; row < 3; row++)
	    {
	    	for(col = 0; col < 3; col++)
	        {
	        	buttons[row][col] = new Button("INDEX [" + row + "," + col+ "]");
	        	
	        	buttons[row][col].addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent e) {
						Button button = (Button) e.getSource();
						//System.out.println(button.getName());
						client.sendIndex(button.getName());
						//button.setMark(client.getSymbol());
					}
					public void mousePressed(MouseEvent e) {						
					}
					public void mouseReleased(MouseEvent e) {
					}
					public void mouseEntered(MouseEvent e) {
					}
					public void mouseExited(MouseEvent e) {
					}
				});
	        	buttons[row][col].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Button button = (Button) e.getSource();
						System.out.println(client.getSymbol());
						button.setMark(client.getSymbol());
					}
				});
	        	
//	        	buttons[row][col].addActionListener(new ActionListener()
//	        	{
//			        	public void actionPerformed(ActionEvent e)  
//			        	{  	
//			        		
//			        		System.out.println(button.getIndex());
//			        		//buttons[row][col].setMark(client.getSymbol());
//			        		//String a = buttons[row][col].getIndex();
//			        		client.sendIndex(button.getIndex());
//			        		//buttons[row][col].setEnabled(false);
//			        	}
//	        	});
	        	buttonPanel.add(buttons[row][col]);	
	        }
	    }
	    
	    start_Game = new JButton("Start");
	    start_Game.setBackground(Color.decode("#80FAC5"));
	    start_Game.setForeground(Color.decode("#F35050"));
	    //buttonPanel.add(start_Game);
	    //start_Game.setBounds(125,270,150,50);
	    start_Game.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
				unlockBoard();
				client.sendName(name.getText());
				client.gameStart = true;
				
				
			}
		});
//	    start_Game.addActionListener(new ActionListener() 
//	    {
//			
//			public void actionPerformed(ActionEvent e) {
//				
//				currentUserName = name.getText();
//				
//				try {
//					//unlockBoard();
//					client = new Client(GUI_board.this,currentUserName);
//					client.run();
//					//unlockBoard();
//				}catch(Exception e1){
//					System.out.println("Error");;
//				}
//				//name.setEditable(false);
//				
//			}
//			
//	    });
	    
	     /*start_Game.addActionListener(new ActionListener() 
	     {
	    		public void actionPerformed(ActionEvent e) 
	    		{
	    			output.println(name.getText());
	    			for(int i=0;i<3;i++) {
	    				for(int j=0;j<3;j++) {
	    					blo[i][j].returnBut().setEnabled(true);
	    				}
	    			}
	    			name.setEditable(false);
	    			name.setHorizontalAlignment(JTextField.CENTER);
	    			name.setForeground(Color.BLACK);
	    		}
	     });*/
	    	
	    name = new JTextField("Please enter your name");
	    name.setBackground(Color.decode("#80FAC5"));
	    	
	    name.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentUserName = name.getText();
				start_Game.doClick();
			}
		});
	    buttonPanel.add(start_Game);
	    buttonPanel.add(name);
	        
	        // set menu
		mb = new JMenuBar();
		menu = new JMenu("Menu");
		exit = new JMenuItem("Exit");
		restart = new JMenuItem("Restart");
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
		    {
		    	try {
					client.quit();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	dispose();
		    		
		    }	
		    	
		});
		
		restart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
		    {
				
				try {
					client.quit();
					clearBoard();
					lockBoard();
			    	client = new Client(GUI_board.this);
			    	//client.run();
				}catch(Exception e) {
					
				}
		    	
//		   		dispose();
		   	}
		});
		   
		menu.add(exit);
		menu.add(restart);
		mb.add(menu);
		    
		this.setJMenuBar(mb);
	    this.add(buttonPanel);
	        
	    this.setSize(400,450);
		this.setVisible(true);  
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	public void lockBoard() {
		
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3 ;col++) {
				
				buttons[row][col].setEnabled(false);
				
			}
		}
		name.setText("Please enter your name");
		name.setEditable(true);
		//start_Game.setVisible(true);
		start_Game.setEnabled(true);
	}
	public void unlockBoard() {
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3 ;col++) {
				
				buttons[row][col].setEnabled(true);
				
			}
		}
		name.setEditable(false);
		//start_Game.setVisible(false);
		start_Game.setEnabled(false);
	}
	
	public void clearBoard() {
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3 ;col++) {
				
				buttons[row][col].setMark("0");
				
			}
		}
//		name.setEditable(false);
//		start_Game.setVisible(false);
//		start_Game.setEnabled(false);
	}
}