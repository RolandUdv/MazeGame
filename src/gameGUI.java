import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
	
public class gameGUI extends JFrame implements ActionListener 
{
	
	/**********************************************************
	* Program:	Assignment 2: Ball Maze						  *
	* Filename:	MazeGame.java								  *
	* @author:	Roland Udvarlaki (17439891)					  *
	* Course:	BSc Software Engineering Year 1				  *
	* Module:	CSY1020 Problem Solving & Programming		  *
	* Tutor: 	Gary Hill									  *	
	* @version:	1.0											  *
	* Date:		27/03/2018									  *
	***********************************************************/
	
    /*----------------------------------------VARIABELS START----------------------------------------*/
	//Button variables
	private JButton startButton;
	private JButton actButton;
	private JButton resetButton;
	private JButton exitButton;
	private JButton optionButton1;
	private JButton optionButton2;
	private JButton optionButton3;
	private JButton compassImage;
	
	private JButton[] numberButton = new JButton[208];
	private int xBall =0, yBall=15, nBall=15;
	
	private JButton upButton;
	private JButton downButton;
	private JButton leftButton;
	private JButton rightButton;
	private JButton blankButton1;
	private JButton blankButton2;
	private JButton blankButton3;
	private JButton blankButton4;
	private JButton blankButton5;
	
	//Menu variables
	private JMenuBar menuBar;
	private JMenu JMenuControl, JMenuHelp, JMenuScenario, JMenuEdit;
	
	//Slider
	private JSlider speedSlider;
	private JLabel SpeedLabel;
	
	//Button icons
	private Icon iconAct;
	private Icon iconStart;
	private Icon iconReset;
	private Icon iconSand;
	private Icon iconBall;
	private Icon iconSandstone;
	private Icon iconWall;
	private Icon cNorth, cSouth, cEast, cWest;
	
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
	
	//Option variables
	private JLabel optionLabel, squareLabel, directionLabel;
	private JTextField optionField, squareField, directionField;
 
	
    /*----------------------------------------VARIABELS END----------------------------------------*/
	//Game frame size
	public static void main(String[] args) 
	{
	    gameGUI frame = new gameGUI();
	    frame.setSize(775, 650);
	    frame.createGUI();
	    frame.setLocationRelativeTo(null);
	    //frame.setResizable(false); Option to resize the gameGUI window frame
	    frame.setTitle("MazeGame - CSY1020"); //Title for the game
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
	    
	    //Icons
	    try
	    {
        	iconAct = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("step.png")));
        	iconStart = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("run.png")));
        	iconReset = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("reset.png")));
        	
        	iconWall = new ImageIcon(((new ImageIcon("images\\\\white32x32.jpg").getImage().getScaledInstance(32, 32,java.awt.Image.SCALE_SMOOTH))));

    		//iconWall = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("white32x32.jpg")));
    		
    		iconSand = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("sand.jpg")));
    		iconSandstone = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("sandstone.jpg")));
    		cNorth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("north.jpg")));
    		cSouth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("south.jpg")));
    		cEast = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("east.jpg")));
    		cWest = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("west.jpg")));
    		
    		//Reference - https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    		iconBall = new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH))));
    		
    		//iconBall = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("sand60x60.png")));
    		
        }
	    catch (Exception e)
        {
            System.err.println("Icon ImageIcon "+e);
        }

	    /*----------JMENU TOP START----------*/
	    menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    JMenuScenario = new JMenu("Scenario");
	    menuBar.add(JMenuScenario);
		
		JMenuEdit= new JMenu("Edit");
		menuBar.add(JMenuEdit);

		JMenuControl = new JMenu("Controls");
		menuBar.add(JMenuControl);

		JMenuHelp = new JMenu("Help");
		menuBar.add(JMenuHelp); 
	    /*----------JMENU TOP END----------*/

	    //Top, Game panel with the grid
	    topPanel = new JPanel();
	    topPanel.setLayout(new GridLayout(13, 16) );
	    topPanel.setPreferredSize(new Dimension(550, 500));
	    topPanel.setBackground(Color.WHITE);
	    window.add(topPanel);
	    
	    //Top Panel Grid Layout
	    for(int nCount=0; nCount<208; nCount++) 
	    {
	    	numberButton[nCount] = new JButton();
			numberButton[nCount].setIcon(iconWall);
			numberButton[nCount].setBackground(Color.WHITE);//Added white background to remove the leftover borders
	    	numberButton[nCount].setBorderPainted(false); //Removes border from grid
			
	    if(nCount<16)
	    {
		    	numberButton[nCount].setIcon(iconSand);
		}
	    
	    //ICON BALL
	    if(nCount==15)
	    {
    			numberButton[nCount].setIcon(iconBall);
	    }
	    
	    if(nCount>47 && nCount<64) //ROW
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount>95 && nCount<112) //ROW
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount>143 && nCount<160) //ROW
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==17)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==33)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==21)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==37)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==25)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==41)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==66)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==82)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==70)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==86)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==75)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==91)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==113)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==129)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==117)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==133)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==124)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==140)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    ///////////////////////////////////////////
	    
	    if(nCount==162)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==178)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==167)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
	    
	    if(nCount==183)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }	    
	    
		if(nCount>192)
		{
				numberButton[nCount].setIcon(iconSand);
		}
	    
		if(nCount==192)
		{
	    		numberButton[nCount].setIcon(iconSandstone);
		}
		
			topPanel.add(numberButton[nCount]);
			numberButton[nCount].addActionListener(this);
	    }
	    
	    
	    /*----------------------------------------PANELS START----------------------------------------*/
	    
	    //BIG Right side panel
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
	    //coordinatePanel.setBackground(Color.PINK);
	    rightPanel.add(coordinatePanel, BorderLayout.EAST);
	    
	    //Timer right side panel
	    timerPanel = new JPanel();
	    timerPanel.setPreferredSize(new Dimension(185, 100));
	    //timerPanel.setBackground(Color.YELLOW);
	    rightPanel.add(timerPanel, BorderLayout.EAST);
	    
	    //Button right panel
	    buttonPanel = new JPanel();
	    buttonPanel.setPreferredSize(new Dimension(185,100));
	    buttonPanel.setLayout(new GridLayout(3, 3));
	    //buttonPanel.setBackground(Color.BLUE);
	    //buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    rightPanel.add(buttonPanel, BorderLayout.EAST);
	    
	    //Option button right side panel
	    optionPanel = new JPanel();
	    optionPanel.setPreferredSize(new Dimension(185, 75));
	    //optionPanel.setBackground(Color.GREEN);
	    rightPanel.add(optionPanel, BorderLayout.EAST);
	    
	    //Compass right panel
	    compassPanel = new JPanel();
	    compassPanel.setPreferredSize(new Dimension(185,125));
	    //compassPanel.setBackground(Color.RED);
	    //compassPanel.setLayout( new FlowLayout(FlowLayout.CENTER));
	    rightPanel.add(compassPanel, BorderLayout.EAST);
	    
	    //Bottom Panel Left
	    bottomPanelLeft = new JPanel();
	    bottomPanelLeft.setPreferredSize(new Dimension(385, 50));
	    //bottomPanelLeft.setBackground(Color.RED);
	    window.add(bottomPanelLeft);
	    
	    //Speed Slider - Bottom Panel Right
	    bottomPanelRight = new JPanel();
	    bottomPanelRight.setPreferredSize(new Dimension(350, 50));
	    //bottomPanelRight.setBackground(Color.GREEN);
	    window.add(bottomPanelRight);
	    
	    /*----------------------------------------PANELS END----------------------------------------*/
	    
	    /*----------------------------------------NAVIGATION, LABELS, BUTTONS AND SLIDERS START----------------------------------------*/

	    /*----------TIMER----------*/
	    
	    timerLabel = new JLabel("               DIGITAL TIMER               ");
	    timerLabel.setFont(new Font ("Arial", Font.BOLD, 16));
	    timerPanel.add(timerLabel);
	    
	    hoursLabel = new JLabel("-");
	    
	    timerPanel.add(hoursLabel);
        hoursField = new JTextField(2);
        hoursField.setFont(new Font ("Arial", Font.BOLD, 16));
        timerPanel.add(hoursField);
	    
        minsLabel = new JLabel(":");
        timerPanel.add(minsLabel);
 
        minsField = new JTextField(2);
        minsField.setFont(new Font ("Arial", Font.BOLD, 16));
        timerPanel.add(minsField);
 
        secsLabel = new JLabel(":");
        timerPanel.add(secsLabel);
 
        secsField = new JTextField(2);
        secsField.setFont(new Font ("Arial", Font.BOLD, 16));
        timerPanel.add(secsField);
        timer = new Timer(1000, this);
        timer.start();
	    
	    /*----------COORDINATE PANEL----------*/
	    optionLabel = new JLabel("Option:");
	    optionLabel.setFont(new Font ("Arial", Font.BOLD, 14));
	    coordinatePanel.add(optionLabel);
	    
	    optionField = new JTextField(2);
		optionField.setText("-"); //Shows dash on default
		optionField.setHorizontalAlignment(JTextField.CENTER); //Centered by default on startup
        coordinatePanel.add(optionField);
	    
	    squareLabel = new JLabel("Square:");
	    squareLabel.setFont(new Font ("Arial", Font.BOLD, 14));
	    coordinatePanel.add(squareLabel);
	    
	    squareField = new JTextField(2);
	    squareField.setText("-"); //Shows dash on default
	    squareField.setHorizontalAlignment(JTextField.CENTER); //Centered by default on startup
        coordinatePanel.add(squareField);
	    
	    directionLabel = new JLabel("Direction:");
	    directionLabel.setFont(new Font ("Arial", Font.BOLD, 14));
	    coordinatePanel.add(directionLabel);
	    
	    directionField = new JTextField(2);
	    directionField.setText("-"); //Shows dash on default
	    directionField.setHorizontalAlignment(JTextField.CENTER); //Centered by default on startup
        coordinatePanel.add(directionField);

	    /*----------BOTTOM RIGHT BUTTONS----------*/
	    //Act button on bottom left Panel
	    actButton = new JButton("Act");
	    actButton.setIcon(iconAct);
	    bottomPanelLeft.add(actButton);
	    actButton.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e) {
    		System.out.println("Act Button");
    	}
    });
	    
	    //Start button on bottom left Panel
	    startButton = new JButton("Start");
	    startButton.setIcon(iconStart);
	    bottomPanelLeft.add(startButton);
	    startButton.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e) {
    		System.out.println("Start Button");
    	}
    });
	    
	    //Reset button on bottom left Panel
	    resetButton = new JButton("Reset");
	    resetButton.setIcon(iconReset);
	    bottomPanelLeft.add(resetButton);
	    resetButton.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e) {
    		System.out.println("Reset Button");
    		//rightPanel.removeAll();
    		//rightPanel.updateUI();
    		
    		optionField.setText("-");
    		squareField.setText("-");
    		directionField.setText("-");
    		
    		optionField.setHorizontalAlignment(JTextField.CENTER);
    		squareField.setHorizontalAlignment(JTextField.CENTER);
    		directionField.setHorizontalAlignment(JTextField.CENTER);
    		
    		timer.stop();
    		secsField.setText("0"); 
    		minsField.setText("0");
    		hoursField.setText("0");
    	    compassImage.setIcon(cNorth);
    		JOptionPane.showMessageDialog(null, "The game has been reset!","Reset Notification!", JOptionPane.INFORMATION_MESSAGE);
    	}
    });
	    
	    /*----------SLIDER----------*/
	    SpeedLabel = new JLabel ("Speed");
	    SpeedLabel.setFont(new Font ("Arial", Font.BOLD, 14));
	    bottomPanelRight.add(SpeedLabel);        

	    speedSlider = new JSlider(JSlider.HORIZONTAL, 5, 1000, 500); 
	    bottomPanelRight.add(speedSlider);

		/*speedSlider.setMajorTickSpacing(500);
		speedSlider.setPaintTicks(true);
		speedSlider.addActionListener(this);*/

	    /*----------OPTION BUTTONS ON RIGHT PANEL----------*/
	    
	    //Option 1 button on bottom left Panel
	    optionButton1 = new JButton("Option 1");
	    optionButton1.setPreferredSize(new Dimension(85, 25));
	    optionPanel.add(optionButton1);
	    optionButton1.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("Option 1 Button");
	    		optionField.setText("Option 1");
	    		optionField.setHorizontalAlignment(JTextField.CENTER);
	    	}
	    });
	    
	    //Option 2 button on bottom left Panel
	    optionButton2 = new JButton("Option 2");
	    optionButton2.setPreferredSize(new Dimension(85, 25));
	    optionPanel.add(optionButton2);
	    optionButton2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("Option 2 Button");
	    		optionField.setText("Option 2");
	    		optionField.setHorizontalAlignment(JTextField.CENTER);
	    	}
	    });
	    
	    //Option 3 button on bottom left Panel
	    optionButton3 = new JButton("Option 3");
	    optionButton3.setPreferredSize(new Dimension(85, 25));
	    optionPanel.add(optionButton3);
	    optionButton3.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("Option 3 Button");
	    		optionField.setText("Option 3");
	    		optionField.setHorizontalAlignment(JTextField.CENTER);
	    	}
	    });
	    
	    //Exit button on bottom left Panel
	    exitButton = new JButton("Exit");
	    exitButton.setPreferredSize(new Dimension(85, 25));
	    optionPanel.add(exitButton);
	    //exitButton.setBackground(Color.LIGHT_YELLOW);
	    //Reference - https://way2java.com/java-general/java-set-background-color/
	    //Color clr1 = new Color(255, 255, 204);
	    //exitButton.setBackground(clr1);
	    exitButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("Exit button");
	    		System.exit(0);
	    	}
	    });
	    
	    /*----------ARROW NAVIGATION BUTTONS----------*/
	    /* Button Panel Buttons */
	    
	    blankButton1 = new JButton("");
	    blankButton1.setPreferredSize(new Dimension(30, 25));
	    buttonPanel.add(blankButton1);
	    blankButton1.addActionListener(this);
	    blankButton1.setVisible(false); //hide button
	    
	    /* ----------UP BUTTON----------*/
	    upButton = new JButton("^");
	    upButton.setPreferredSize(new Dimension(45, 25));
	    buttonPanel.add(upButton);
	    upButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("Up Button");
	    		compassImage.setIcon(cNorth); 
	    		directionField.setText("North");
	    		directionField.setHorizontalAlignment(JTextField.CENTER);
	    	}
	    });
	    
	    blankButton2 = new JButton("");
	    blankButton2.setPreferredSize(new Dimension(30, 25));
	    buttonPanel.add(blankButton2);
	    blankButton2.addActionListener(this);
	    blankButton2.setVisible(false); //hide button
	    
	    /* ----------LEFT BUTTON----------*/
	    leftButton = new JButton("<");
	    leftButton.setPreferredSize(new Dimension(45, 25));
	    buttonPanel.add(leftButton);
	    leftButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {	///////////////////////////////////////////////////////////////////////////////////////
	    		System.out.println("Left Button");
	    		
	    		//numberButton[nCount] = numberButton[nCount] -1;
	    		//Start here
	    		/*if(numberButton[nCount]=iconSand) {
	    			numberButton[nCount] = nCount--;
	    		}*/
	    			

	    		
	    	    compassImage.setIcon(cWest); 
	    		directionField.setText("West");
	    		directionField.setHorizontalAlignment(JTextField.CENTER);  
	    	}
	    });
	    
	    blankButton3 = new JButton("");
	    blankButton3.setPreferredSize(new Dimension(30, 25));
	    buttonPanel.add(blankButton3);
	    blankButton3.addActionListener(this);
	    blankButton3.setVisible(false); //hide button
	    
	    /* ----------RIGHT BUTTON----------*/
	    rightButton = new JButton(">");
	    rightButton.setPreferredSize(new Dimension(45, 25));
	    buttonPanel.add(rightButton);
	    rightButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("Right Button");
	    	    compassImage.setIcon(cEast); 
	    		directionField.setText("East");
	    		directionField.setHorizontalAlignment(JTextField.CENTER);
	    	}
	    });
	    
	    blankButton4 = new JButton("");
	    blankButton4.setPreferredSize(new Dimension(30, 25));
	    buttonPanel.add(blankButton4);
	    blankButton4.addActionListener(this);
	    blankButton4.setVisible(false); //hide button
	    
	    /* ----------DOWN BUTTON----------*/
	    downButton = new JButton("v");
	    downButton.setPreferredSize(new Dimension(45, 25));
	    buttonPanel.add(downButton);
	    downButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("Down Button");
	    	    compassImage.setIcon(cSouth); 
	    		directionField.setText("South");
	    		directionField.setHorizontalAlignment(JTextField.CENTER);
	    	}
	    });
	    
	    blankButton5 = new JButton("");
	    blankButton5.setPreferredSize(new Dimension(30, 25));
	    buttonPanel.add(blankButton5);
	    blankButton5.addActionListener(this);
	    blankButton5.setVisible(false); //hide button
	    
	    /*----------COMPASS BUTTON----------*/
	    /* Compass */
	    compassImage = new JButton();      
	    compassImage.setPreferredSize(new Dimension(80, 80));
	    compassImage.setBorderPainted(false);
	    compassImage.setIcon(cNorth);     
		compassPanel.add(compassImage, compassImage); 
	    
	}
	

    /*----------------------------------------NAVIGATION, LABELS, BUTTONS AND SLIDERS END----------------------------------------*/

	/*public void squareLabel() {
		squareField.setText(Integer.toString(numberButton(nCount)));
		squareField.setHorizontalAlignment(JTextField.CENTER);
	}*/
	
    public void actionPerformed(ActionEvent e) 
    {
    hoursField.setText(Integer.toString(ticks / 3600));
	minsField.setText(Integer.toString(ticks / 60));
	secsField.setText(Integer.toString(ticks % 60));
	ticks = ticks + 1;
	}
    
}