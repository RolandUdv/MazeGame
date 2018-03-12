import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
	
public class gameGUI extends JFrame implements ActionListener 
{
	
	/**********************************************************
	* Program:	Assignment 2								  *
	* Filename:	MazeGame.java								  *
	* @author:  Roland Udvarlaki (17439891)					  *
	* Course:	BSc Software Engineering Year 1				  *
	* Module:	CSY1020 Problem Solving & Programming		  *	
	***********************************************************/
	
	/*-------------------------------------------------------------------------------*/
	//Button variables
	private JButton startButton;
	private JButton actButton;
	private JButton resetButton;
	private JButton exitButton;
	private JButton numberButton;
	private JButton optionButton1;
	private JButton optionButton2;
	private JButton optionButton3;
	
	private JButton upButton;
	private JButton downButton;
	private JButton leftButton;
	private JButton rightButton;
	private JButton blankButton1;
	private JButton blankButton2;
	private JButton blankButton3;
	private JButton blankButton4;
	private JButton blankButton5;
	
	//Button icon
	private Icon iconAct;
	private Icon iconStart;
	private Icon iconReset;
	private Icon iconSand;
	private Icon iconBall;
	private Icon iconSandstone;
	private Icon iconWall;
	
	//Panel variables
	private JPanel sidePanel;
	private JPanel topPanel;
	private JPanel rightPanel;
	private JPanel coordinatePanel;
	private JPanel timerPanel;
	private JPanel optionPanel;
	private JPanel buttonPanel;
	private JPanel compassPanel;
	private JPanel bottomPanelLeft;
	private JPanel bottomPanelRight;
	
	//Timer variables
	private JTextField secsField, minsField, hoursField;
	private JLabel secsLabel, minsLabel, hoursLabel, timerLabel;
	private int ticks = 0;
	private Timer timer;
	
	//Other Labels
	private JLabel optionLabel, squareLabel, directionLabel;
	
	/*-------------------------------------------------------------------------------*/
	//Game frame size
	public static void main(String[] args) 
	{
	    gameGUI frame = new gameGUI();
	    frame.setSize(775, 650);
	    frame.createGUI();
	    frame.setLocationRelativeTo(null);
	    //frame.setResizable(false);
	    frame.setTitle("MazeGame - CSY1020"); 
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("greenfoot.png")));
	    frame.show();
	}
	
	//Icon size
	/*public static void main(String[] args) 
	{
	    iconAct frame = new iconAct();
	    frame.setSize(600, 700);
	    frame.bottomPanel();
	    frame.setVisible(true);
	}*/

	//Game layout and design, functions
	private void createGUI() {
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    Container window = getContentPane();
	    window.setLayout(new FlowLayout() );
	    
	    //Top Panel
	    topPanel = new JPanel();
	    topPanel.setLayout(new GridLayout(13, 16) );
	    topPanel.setPreferredSize(new Dimension(550, 500));
	    topPanel.setBackground(Color.WHITE);
	    window.add(topPanel);
	    
	    //Top Panel Grid Layout
	    for(int nCount=0; nCount<208; nCount++) 
	    {
	    	numberButton = new JButton(""+nCount);
	    	//numberButton.setBorderPainted(false);
	    if(nCount<16)
	    {
	    		iconSand = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("sand.jpg")));
		    	numberButton.setIcon(iconSand);
		}
	    
	    /*if(nCount==15)
	    {
    			iconBall = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("sand60x60.png")));
    			numberButton.setIcon(iconBall);
	    }*/
	    
	    if(nCount==47)
	    {
	    		iconWall = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("white32x32.jpg")));
    			numberButton.setIcon(iconWall);
	    }
	    
		if(nCount>192)
		{
				iconSand = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("sand.jpg")));
		    	numberButton.setIcon(iconSand);
		}
	    
		if(nCount==192)
		{
	    		iconSandstone = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("sandstone.jpg")));
		    	numberButton.setIcon(iconSandstone);
		}
		
			topPanel.add(numberButton);
			numberButton.addActionListener(this);
	    }
	    
	    //Icons
	    //Act Icon
	    try
	    {
        	iconAct = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("step.png")));
        }
	    catch (Exception e)
        {
            System.err.println("Act Icon ImageIcon "+e);
        }
	    
	    //Start Icon
	    try
	    {
        	iconStart = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("run.png")));
        }
	    catch (Exception e)
        {
            System.err.println("Start Icon ImageIcon "+e);
        }
	    
	    //Reset Icon
	    try
	    {
        	iconReset = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("reset.png")));
        }
	    catch (Exception e)
        {
            System.err.println("Reset Icon ImageIcon "+e);
        }
	    
	    /*-------------------------------------------------------------------------------*/
	    
	    //Right Panel 1
	    rightPanel = new JPanel();
	    rightPanel.setPreferredSize(new Dimension(185, 500));
		//rightPanel.setLayout( new FlowLayout(FlowLayout.CENTER));
		//rightPanel.setBorder(BorderFactory.createRaisedBevelBorder());
	    //rightPanel.setBackground(Color.ORANGE);
	    window.add(rightPanel);
	    
	    //Coordinate right side panel
	    coordinatePanel = new JPanel();
	    coordinatePanel.setPreferredSize(new Dimension(185, 100));
	    coordinatePanel.setLayout(new GridLayout(3, 2));
	    coordinatePanel.setBackground(Color.PINK);
	    rightPanel.add(coordinatePanel, BorderLayout.EAST);
	    
	    //Timer right side panel
	    timerPanel = new JPanel();
	    timerPanel.setPreferredSize(new Dimension(185, 100));
	    timerPanel.setBackground(Color.YELLOW);
	    rightPanel.add(timerPanel, BorderLayout.EAST);
	    
	    //Button right panel
	    buttonPanel = new JPanel();
	    buttonPanel.setPreferredSize(new Dimension(185,100));
	    buttonPanel.setLayout(new GridLayout(3, 3));
	    buttonPanel.setBackground(Color.BLUE);
	    //buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    rightPanel.add(buttonPanel, BorderLayout.EAST);
	    
	    //Option button right side panel
	    optionPanel = new JPanel();
	    optionPanel.setPreferredSize(new Dimension(185, 75));
	    optionPanel.setBackground(Color.GREEN);
	    rightPanel.add(optionPanel, BorderLayout.EAST);
	    
	    //Compass right panel
	    compassPanel = new JPanel();
	    compassPanel.setPreferredSize(new Dimension(185,125));
	    compassPanel.setBackground(Color.RED);
	    //compassPanel.setLayout( new FlowLayout(FlowLayout.CENTER));
	    rightPanel.add(compassPanel, BorderLayout.EAST);
	    
	    /*-------------------------------------------------------------------------------*/
	    
	    timerLabel = new JLabel("               DIGITAL TIMER               ");
	    timerPanel.add(timerLabel);
	    
	    hoursLabel = new JLabel("-");
	    timerPanel.add(hoursLabel);
	    
        hoursField = new JTextField(2);
        timerPanel.add(hoursField);
	    
        minsLabel = new JLabel(":");
        timerPanel.add(minsLabel);
 
        minsField = new JTextField(2);
        timerPanel.add(minsField);
 
        secsLabel = new JLabel(":");
        timerPanel.add(secsLabel);
 
        secsField = new JTextField(2);
        timerPanel.add(secsField);
        timer = new Timer(1000, this);
        timer.start();
	        
	    //Bottom Panel Left
	    bottomPanelLeft = new JPanel();
	    bottomPanelLeft.setPreferredSize(new Dimension(385, 50));
	    //bottomPanelLeft.setBackground(Color.RED);
	    window.add(bottomPanelLeft);
	    
	    //Bottom Panel Right
	    bottomPanelRight = new JPanel();
	    bottomPanelRight.setPreferredSize(new Dimension(350, 50));
	    bottomPanelRight.setBackground(Color.GREEN);
	    window.add(bottomPanelRight);
	    
	    /*-------------------------------------------------------------------------------*/
	    
	    //Labels for coordinate panel
	    optionLabel = new JLabel("Option:");
	    coordinatePanel.add(optionLabel);
	    
	    squareLabel = new JLabel("Square:");
	    coordinatePanel.add(squareLabel);
	    
	    directionLabel = new JLabel("Direction:");
	    coordinatePanel.add(directionLabel);

	    //Start button on bottom left Panel
	    actButton = new JButton("Act");
	    actButton.setIcon(iconAct);
	    bottomPanelLeft.add(actButton);
	    actButton.addActionListener(this);
	    
	    //Start button on bottom left Panel
	    startButton = new JButton("Start");
	    startButton.setIcon(iconStart);
	    bottomPanelLeft.add(startButton);
	    startButton.addActionListener(this);
	    
	    //Start button on bottom left Panel
	    resetButton = new JButton("Reset");
	    resetButton.setIcon(iconReset);
	    bottomPanelLeft.add(resetButton);
	    resetButton.addActionListener(this);
	    
	    //Option 1 button on bottom left Panel
	    optionButton1 = new JButton("Option 1");
	    optionButton1.setPreferredSize(new Dimension(85, 25));
	    optionPanel.add(optionButton1);
	    optionButton1.addActionListener(this);
	    
	    //Option 2 button on bottom left Panel
	    optionButton2 = new JButton("Option 2");
	    optionButton2.setPreferredSize(new Dimension(85, 25));
	    optionPanel.add(optionButton2);
	    optionButton2.addActionListener(this);
	    
	    //Option 3 button on bottom left Panel
	    optionButton3 = new JButton("Option 3");
	    optionButton3.setPreferredSize(new Dimension(85, 25));
	    optionPanel.add(optionButton3);
	    optionButton3.addActionListener(this);
	    
	    //Exit button on bottom left Panel
	    exitButton = new JButton("Exit");
	    exitButton.setPreferredSize(new Dimension(85, 25));
	    optionPanel.add(exitButton);
	    exitButton.addActionListener(this);
	    
	    /* Button Panel Buttons */
	    
	    blankButton1 = new JButton("");
	    blankButton1.setPreferredSize(new Dimension(30, 25));
	    buttonPanel.add(blankButton1);
	    blankButton1.addActionListener(this);
	    
	    upButton = new JButton("^");
	    upButton.setPreferredSize(new Dimension(45, 25));
	    buttonPanel.add(upButton);
	    upButton.addActionListener(this);
	    
	    blankButton2 = new JButton("");
	    blankButton2.setPreferredSize(new Dimension(30, 25));
	    buttonPanel.add(blankButton2);
	    blankButton2.addActionListener(this);
	    
	    leftButton = new JButton("<");
	    leftButton.setPreferredSize(new Dimension(45, 25));
	    buttonPanel.add(leftButton);
	    leftButton.addActionListener(this);
	    
	    blankButton3 = new JButton("");
	    blankButton3.setPreferredSize(new Dimension(30, 25));
	    buttonPanel.add(blankButton3);
	    blankButton3.addActionListener(this);
	    
	    rightButton = new JButton(">");
	    rightButton.setPreferredSize(new Dimension(45, 25));
	    buttonPanel.add(rightButton);
	    rightButton.addActionListener(this);
	    
	    blankButton4 = new JButton("");
	    blankButton4.setPreferredSize(new Dimension(30, 25));
	    buttonPanel.add(blankButton4);
	    blankButton4.addActionListener(this);
	    
	    downButton = new JButton("v");
	    downButton.setPreferredSize(new Dimension(45, 25));
	    buttonPanel.add(downButton);
	    downButton.addActionListener(this);
	    
	    blankButton5 = new JButton("");
	    blankButton5.setPreferredSize(new Dimension(30, 25));
	    buttonPanel.add(blankButton5);
	    blankButton5.addActionListener(this);
	    
	}
	
	public void exitButton(ActionEvent e)
	{
	System.exit(0);
	}
	
    public void actionPerformed(ActionEvent e) 
    {
        //Exit program
        //System.exit(0);

    hoursField.setText(Integer.toString(ticks / 600));
	minsField.setText(Integer.toString(ticks / 60));
	secsField.setText(Integer.toString(ticks % 60));
	ticks = ticks + 1;
	}
	
}