import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;
	
public class CBallMaze extends JFrame implements ActionListener 
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
	private int nBall = 15;
	private int nGoal = 192;
	private int nSquare = 15;
	
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
	private JMenu JMenuControl, JMenuHelp, JMenuScenario, JMenuEdit, jHelp, JMExit;
	
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
	    CBallMaze frame = new CBallMaze();
	    frame.setSize(775, 650);
	    frame.createGUI();
	    frame.setLocationRelativeTo(null);
	    //frame.setResizable(false); Option to resize the gameGUI window frame
	    frame.setTitle("CBallMaze – Ball Maze Application"); //Title for the game
		frame.setIconImage(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("greenfoot.png")));
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
        	iconAct = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("step.png")));
        	iconStart = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("run.png")));
        	iconReset = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("reset.png")));
        	
        	iconWall = new ImageIcon(((new ImageIcon("images\\\\white32x32.jpg").getImage().getScaledInstance(32, 32,java.awt.Image.SCALE_SMOOTH))));

    		//iconWall = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("white32x32.jpg")));
    		
    		iconSand = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("sand.jpg")));
    		iconSandstone = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("sandstone.jpg")));
    		cNorth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("north.jpg")));
    		cSouth = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("south.jpg")));
    		cEast = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("east.jpg")));
    		cWest = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CBallMaze.class.getResource("west.jpg")));
    		
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
		
		JMExit = new JMenu("Exit");
		JMenuControl.add(JMExit);
		JMExit.addActionListener(this);

		JMenuHelp = new JMenu("Help");
		menuBar.add(JMenuHelp);
		JMenuHelp.addActionListener(this);
		
		jHelp = new JMenu("Help Button");
		JMenuHelp.add(jHelp);
		jHelp.addActionListener(this);

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
	    
	    //Sand blocks	    
	    if(nCount==17 || nCount==33 || nCount==21 || nCount==37 || nCount==25 || nCount==41 || nCount==66 || nCount==82 || nCount==70 || nCount==86 || nCount==75 || nCount==91 || nCount==113 || nCount==129 || nCount==117 || nCount==133 || nCount==124 || nCount==140 || nCount==162 || nCount==178 || nCount==167 || nCount==183 || nCount>192)
	    {
    			numberButton[nCount].setIcon(iconSand);
	    }
		
	    //SandStone
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
		optionField.setText("Option 1"); //Shows dash on default
		optionField.setHorizontalAlignment(JTextField.CENTER); //Centered by default on startup
        coordinatePanel.add(optionField);
	    
	    squareLabel = new JLabel("Square:");
	    squareLabel.setFont(new Font ("Arial", Font.BOLD, 14));
	    coordinatePanel.add(squareLabel);
	    
	    squareField = new JTextField(2);
	    squareField.setText(Integer.toString(nSquare)); //Shows dash on default
	    squareField.setHorizontalAlignment(JTextField.CENTER); //Centered by default on startup
        coordinatePanel.add(squareField);
	    
	    directionLabel = new JLabel("Direction:");
	    directionLabel.setFont(new Font ("Arial", Font.BOLD, 14));
	    coordinatePanel.add(directionLabel);
	    
	    directionField = new JTextField(3);
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
    		
    		if(nBall==15 || nBall==14 || nBall==13 || nBall==12 || nBall==11 || nBall==10 || nBall==57 || nBall==56 || nBall==55 || nBall==54 || nBall==102 || nBall==101 || nBall==149 || nBall==148 || nBall==147 || nBall==146 || nBall==194 || nBall==193 || nBall==192) {
    			numberButton[nBall -1].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
        		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
        		nBall = nBall -1;
        		
    			nSquare = nSquare -1;
	    		squareField.setText(Integer.toString(nSquare));
    		}
    		
    		if(nBall==9 || nBall==25 || nBall==41 || nBall==57 || nBall==54 || nBall==70 || nBall==86 || nBall==102 || nBall==101 || nBall==117 || nBall==133 || nBall==149 || nBall==146 || nBall==162 || nBall==178 || nBall==194){
    			numberButton[nBall +16].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
        		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
        		nBall = nBall +16;
        		
    			nSquare = nSquare +16;
	    		squareField.setText(Integer.toString(nSquare));
    		}
    		
    		if(nBall==192) {
    			endGame();
    		}
    			
    		
    	}
    });
	    
	    //Start button on bottom left Panel
	    startButton = new JButton("Start");
	    startButton.setIcon(iconStart);
	    bottomPanelLeft.add(startButton);
	    startButton.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e) {
    		System.out.println("Start Button");
    		hoursField.setText("00");
    		minsField.setText("00");
    		secsField.setText("00");
    		
    		ticks=0;
        	timer.start();
        	
    		optionField.setText("Option 1");
    		optionField.setHorizontalAlignment(JTextField.CENTER);
        	
    		resetButton.setEnabled(true);
    		//optionButton1.setEnabled(true);
    		//optionButton2.setEnabled(true);
    		//optionButton3.setEnabled(true);
    		upButton.setEnabled(true);
    		downButton.setEnabled(true);
    		rightButton.setEnabled(true);
    		leftButton.setEnabled(true);
    		actButton.setEnabled(true);
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
    		//topPanel.removeAll();
    		//topPanel.updateUI();
    		
    		numberButton[nBall].validate();
    		numberButton[nBall].repaint();
    		
    		topPanel.revalidate();
    		topPanel.repaint();
    		
    		//Remove ball and set it back to 15
    		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
    		nBall=15;
    		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
    		
    		//Leaves Sandstone on 192 and doesn't remove it
    		numberButton[nGoal].setIcon(new ImageIcon(((new ImageIcon("images\\sandstone.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
    		
    		optionField.setText("-");
    		
    		nSquare=15;
    		squareField.setText("15");
    		
    		directionField.setText("-");
    		
    		optionField.setHorizontalAlignment(JTextField.CENTER);
    		squareField.setHorizontalAlignment(JTextField.CENTER);
    		directionField.setHorizontalAlignment(JTextField.CENTER);
    		
    		timer.stop();
    		secsField.setText("00"); 
    		minsField.setText("00");
    		hoursField.setText("00");
    	    compassImage.setIcon(cNorth);
    		JOptionPane.showMessageDialog(null, "The game has been reset!","Reset Notification!", JOptionPane.INFORMATION_MESSAGE);
    		System.out.println("The game has been reset!");
    		resetButton.setEnabled(false);
    		//optionButton1.setEnabled(false);
    		//optionButton2.setEnabled(false);
    		//optionButton3.setEnabled(false);
    		upButton.setEnabled(false);
    		downButton.setEnabled(false);
    		rightButton.setEnabled(false);
    		leftButton.setEnabled(false);
    		actButton.setEnabled(false);
    		startButton.setEnabled(true);
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
	    		//System.exit(0);
	    		
	    		//Reference - https://stackoverflow.com/questions/21330682/confirmation-before-press-yes-to-exit-program-in-java
	    		int confirmed = JOptionPane.showConfirmDialog(null, 
	    		        "Are you sure you want to exit the game?", "CBallMaze – Ball Maze Application",
	    		        JOptionPane.YES_NO_OPTION);

	    		    if (confirmed == JOptionPane.YES_OPTION) {
	    		      dispose();
	    		    }
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
	    		
	    		numberButton[nBall -16].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    		nBall = nBall -16;
	    		
	    		if(nBall==16 || nBall==18 || nBall==19 || nBall==20 || nBall==20 || nBall==22 || nBall==23 || nBall==24 || nBall==26 || nBall==27 || nBall==28 || nBall==29 || nBall==30 || nBall==31 || nBall==32 || nBall==34 || nBall==35 || nBall==36 || nBall==38 || nBall==39 || nBall==40 || nBall==42 || nBall==43 || nBall==44 || nBall==45 || nBall==46 || nBall==47 || nBall==64 || nBall==65 || nBall==67 || nBall==68 || nBall==69 || nBall==71 || nBall==72 || nBall==73 || nBall==74 || nBall==76 || nBall==77 || nBall==78 || nBall==79 || nBall==80 || nBall==81 || nBall==83 || nBall==84 || nBall==84 || nBall==85 || nBall==87 || nBall==88 || nBall==89 || nBall==90 || nBall==92 || nBall==93 || nBall==94 || nBall==95 || nBall==112 || nBall==114 || nBall==115 || nBall==116 || nBall==118 || nBall==119 || nBall==120 || nBall==121 || nBall==122 || nBall==123 || nBall==125 || nBall==126 || nBall==127 || nBall==128 || nBall==130 || nBall==131 || nBall==132 || nBall==134 || nBall==135 || nBall==136 || nBall==137 || nBall==138 || nBall==139 || nBall==141 || nBall==142 || nBall==143 || nBall==160 || nBall==161|| nBall==163 || nBall==164 || nBall==165 || nBall==166 || nBall==168 || nBall==169 || nBall==170 || nBall==171 || nBall==172 || nBall==173 || nBall==174 || nBall==175 || nBall==176 || nBall==177 || nBall==179 || nBall==180 || nBall==181 || nBall==182 || nBall==184 || nBall==185 || nBall==186 || nBall==187 || nBall==188 || nBall==189 || nBall==190 || nBall==191) {
	    			System.out.println("Unable to move there");
	    			numberButton[nBall +16].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		    		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\white32x32.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    			nBall = nBall +16;
	    			
	    			nSquare=nSquare +0;
	    			squareField.setText(Integer.toString(nSquare));
	    		}else {
	    			nSquare = nSquare -16;
		    		squareField.setText(Integer.toString(nSquare));
	    		}
	    		
	    		//Change Square number field
	    		//nSquare = nSquare -16;
	    		//squareField.setText(Integer.toString(nSquare));
	    		endGame();
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
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("Left Button");
	    		compassImage.setIcon(cWest);
	    		directionField.setText("West");
	    		directionField.setHorizontalAlignment(JTextField.CENTER);
	    		
	    		numberButton[nBall -1].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    		nBall = nBall -1;
	    		
	    		if(nBall==16 || nBall==18 || nBall==19 || nBall==20 || nBall==20 || nBall==22 || nBall==23 || nBall==24 || nBall==26 || nBall==27 || nBall==28 || nBall==29 || nBall==30 || nBall==31 || nBall==32 || nBall==34 || nBall==35 || nBall==36 || nBall==38 || nBall==39 || nBall==40 || nBall==42 || nBall==43 || nBall==44 || nBall==45 || nBall==46 || nBall==47 || nBall==64 || nBall==65 || nBall==67 || nBall==68 || nBall==69 || nBall==71 || nBall==72 || nBall==73 || nBall==74 || nBall==76 || nBall==77 || nBall==78 || nBall==79 || nBall==80 || nBall==81 || nBall==83 || nBall==84 || nBall==84 || nBall==85 || nBall==87 || nBall==88 || nBall==89 || nBall==90 || nBall==92 || nBall==93 || nBall==94 || nBall==95 || nBall==112 || nBall==114 || nBall==115 || nBall==116 || nBall==118 || nBall==119 || nBall==120 || nBall==121 || nBall==122 || nBall==123 || nBall==125 || nBall==126 || nBall==127 || nBall==128 || nBall==130 || nBall==131 || nBall==132 || nBall==134 || nBall==135 || nBall==136 || nBall==137 || nBall==138 || nBall==139 || nBall==141 || nBall==142 || nBall==143 || nBall==160 || nBall==161|| nBall==163 || nBall==164 || nBall==165 || nBall==166 || nBall==168 || nBall==169 || nBall==170 || nBall==171 || nBall==172 || nBall==173 || nBall==174 || nBall==175 || nBall==176 || nBall==177 || nBall==179 || nBall==180 || nBall==181 || nBall==182 || nBall==184 || nBall==185 || nBall==186 || nBall==187 || nBall==188 || nBall==189 || nBall==190 || nBall==191) {
	    			System.out.println("Unable to move there");
	    			numberButton[nBall +1].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		    		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\white32x32.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    			nBall = nBall +1;

	    			nSquare=nSquare +0;
	    			squareField.setText(Integer.toString(nSquare));
	    		}else {
	    			nSquare = nSquare -1;
		    		squareField.setText(Integer.toString(nSquare));
	    		}
	    		
	    		//Change Square number field
	    		//nSquare = nSquare -1;
	    		//squareField.setText(Integer.toString(nSquare));
	    		
	    		endGame();
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

	    		numberButton[nBall +1].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    		nBall = nBall +1;
	    		
	    		if(nBall==16 || nBall==18 || nBall==19 || nBall==20 || nBall==20 || nBall==22 || nBall==23 || nBall==24 || nBall==26 || nBall==27 || nBall==28 || nBall==29 || nBall==30 || nBall==31 || nBall==32 || nBall==34 || nBall==35 || nBall==36 || nBall==38 || nBall==39 || nBall==40 || nBall==42 || nBall==43 || nBall==44 || nBall==45 || nBall==46 || nBall==47 || nBall==64 || nBall==65 || nBall==67 || nBall==68 || nBall==69 || nBall==71 || nBall==72 || nBall==73 || nBall==74 || nBall==76 || nBall==77 || nBall==78 || nBall==79 || nBall==80 || nBall==81 || nBall==83 || nBall==84 || nBall==84 || nBall==85 || nBall==87 || nBall==88 || nBall==89 || nBall==90 || nBall==92 || nBall==93 || nBall==94 || nBall==95 || nBall==112 || nBall==114 || nBall==115 || nBall==116 || nBall==118 || nBall==119 || nBall==120 || nBall==121 || nBall==122 || nBall==123 || nBall==125 || nBall==126 || nBall==127 || nBall==128 || nBall==130 || nBall==131 || nBall==132 || nBall==134 || nBall==135 || nBall==136 || nBall==137 || nBall==138 || nBall==139 || nBall==141 || nBall==142 || nBall==143 || nBall==160 || nBall==161|| nBall==163 || nBall==164 || nBall==165 || nBall==166 || nBall==168 || nBall==169 || nBall==170 || nBall==171 || nBall==172 || nBall==173 || nBall==174 || nBall==175 || nBall==176 || nBall==177 || nBall==179 || nBall==180 || nBall==181 || nBall==182 || nBall==184 || nBall==185 || nBall==186 || nBall==187 || nBall==188 || nBall==189 || nBall==190 || nBall==191) {
	    			System.out.println("Unable to move there");
	    			numberButton[nBall -1].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		    		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\white32x32.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    			nBall = nBall -1;
	    			
	    			nSquare=nSquare +0;
	    			squareField.setText(Integer.toString(nSquare));
	    		}else {
	    			nSquare = nSquare +1;
		    		squareField.setText(Integer.toString(nSquare));
	    		}
	    		
	    		//Change Square number field
	    		//nSquare = nSquare +1;
	    		//squareField.setText(Integer.toString(nSquare));
	    		
	    		endGame();
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
	    		
	    		numberButton[nBall +16].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\sand.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    		nBall = nBall +16;
	    		
	    		
	    		if(nBall==16 || nBall==18 || nBall==19 || nBall==20 || nBall==20 || nBall==22 || nBall==23 || nBall==24 || nBall==26 || nBall==27 || nBall==28 || nBall==29 || nBall==30 || nBall==31 || nBall==32 || nBall==34 || nBall==35 || nBall==36 || nBall==38 || nBall==39 || nBall==40 || nBall==42 || nBall==43 || nBall==44 || nBall==45 || nBall==46 || nBall==47 || nBall==64 || nBall==65 || nBall==67 || nBall==68 || nBall==69 || nBall==71 || nBall==72 || nBall==73 || nBall==74 || nBall==76 || nBall==77 || nBall==78 || nBall==79 || nBall==80 || nBall==81 || nBall==83 || nBall==84 || nBall==84 || nBall==85 || nBall==87 || nBall==88 || nBall==89 || nBall==90 || nBall==92 || nBall==93 || nBall==94 || nBall==95 || nBall==112 || nBall==114 || nBall==115 || nBall==116 || nBall==118 || nBall==119 || nBall==120 || nBall==121 || nBall==122 || nBall==123 || nBall==125 || nBall==126 || nBall==127 || nBall==128 || nBall==130 || nBall==131 || nBall==132 || nBall==134 || nBall==135 || nBall==136 || nBall==137 || nBall==138 || nBall==139 || nBall==141 || nBall==142 || nBall==143 || nBall==160 || nBall==161|| nBall==163 || nBall==164 || nBall==165 || nBall==166 || nBall==168 || nBall==169 || nBall==170 || nBall==171 || nBall==172 || nBall==173 || nBall==174 || nBall==175 || nBall==176 || nBall==177 || nBall==179 || nBall==180 || nBall==181 || nBall==182 || nBall==184 || nBall==185 || nBall==186 || nBall==187 || nBall==188 || nBall==189 || nBall==190 || nBall==191) {
	    			System.out.println("Unable to move there");
	    			numberButton[nBall -16].setIcon(new ImageIcon(((new ImageIcon("images\\sand60x60.png").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
		    		numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\white32x32.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
	    			nBall = nBall -16;
	    			
	    			nSquare=nSquare +0;
	    			squareField.setText(Integer.toString(nSquare));
	    		}else {
	    			nSquare = nSquare +16;
		    		squareField.setText(Integer.toString(nSquare));
	    		}
	    			
		    		//Change Square number field
		    		//nSquare = nSquare +16;
		    		//squareField.setText(Integer.toString(nSquare));
		    		
		    		endGame();
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
	
	public void endGame()
	{	
		
		if(nBall == 192)
		{   
			timer.stop();
			JOptionPane.showMessageDialog(null, "Congratulations, You've finished the game!","You Won!", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("You've finished the game!");
			//numberButton[nBall].setIcon(new ImageIcon(((new ImageIcon("images\\sandstone.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
			
			//Leaves Sandstone on 192 and doesn't remove it
    		numberButton[nGoal].setIcon(new ImageIcon(((new ImageIcon("images\\sandstone.jpg").getImage().getScaledInstance(41, 41,java.awt.Image.SCALE_SMOOTH)))));
    		//disable reset button
    		startButton.setEnabled(false);
    		//optionButton1.setEnabled(false);
    		//optionButton2.setEnabled(false);
    		//optionButton3.setEnabled(false);
    		upButton.setEnabled(false);
    		downButton.setEnabled(false);
    		rightButton.setEnabled(false);
    		leftButton.setEnabled(false);
    		actButton.setEnabled(false);
		}
	}
	
    public void actionPerformed(ActionEvent e) 
    {
    	DecimalFormat df = new DecimalFormat("##,00");
    	
    hoursField.setText(df.format(ticks / 3600));
	minsField.setText(df.format(ticks / 60));
	secsField.setText(df.format(ticks % 60));
	ticks = ticks + 1;
	//System.out.println(df.format(ticks)); //Outputs the ticks to Console
	
	
	/*Object source = e.getSource();
	if(source==jHelp){
		System.out.println("Help Button");

		//ImageIcon icon = new ImageIcon(((new ImageIcon("images\\icon.jpg").getImage().getScaledInstance(100, 100,java.awt.Image.SCALE_SMOOTH))));

		JOptionPane.showMessageDialog(null, "CBallMaze - Ball Maze Application ", "CBallMaze", JOptionPane.INFORMATION_MESSAGE);
	}
	
	if((source == exitButton)||(source == JMExit)){
		//System.out.println("Exit Button");
		System.exit(0);
	}*/
	
	}
    
}//DO NOT REMOVE!