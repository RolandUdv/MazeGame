import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ImageExample extends JFrame implements ActionListener 
{
    private JButton button;
    private JPanel panel;
    
    private Icon iconAct;

    public static void main(String[] args) 
    {
    	ImageExample frame = new ImageExample();
        frame.setSize(600, 700);
        frame.createGUI();
        frame.setVisible(true);
    }

    private void createGUI() 
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout() );

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 600));
        panel.setBackground(Color.white);
        window.add(panel);

        try	
        {
        	iconAct = new ImageIcon(Toolkit.getDefaultToolkit().createImage(ImageExample.class.getResource("images/step.png")));
        	//iconAct = new ImageIcon("Act.jpg");
        }
        catch (Exception e)
        {
            System.err.println("Act Icon ImageIcon "+e);
        } 
        
        button = new JButton("Act");
        button.setIcon(iconAct);
        //button.setBorderPainted(false);
        window.add(button);
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) 
    {
     
    }
}