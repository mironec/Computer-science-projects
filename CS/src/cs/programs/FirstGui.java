package cs.programs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import cs.CSProgram;

public class FirstGui extends CSProgram implements ActionListener{

	JFrame frame;
	JButton button;
	JLabel label;
	int numOfClicks=0;
	Timer timer[] = new Timer[5];
	
	public FirstGui(int id) {
		super("First GUI", id);
	}

	@Override
	public void start() {
		frame = new JFrame("Hej ho!");
		frame.setBounds(10, 10, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setVisible(true);
		
		button = new JButton("Clickuuu!");
		button.setBounds(0,400,100,50);
		button.addActionListener(this);
		frame.add(button);
		
		label = new JLabel("Score: 0");
		label.setBounds(frame.getWidth()/2,frame.getHeight()/2,150,30);
		frame.add(label);
		
		for(int i=0;i<timer.length;i++){
			timer[i] = new Timer(1000, this);
			timer[i].setRepeats(true);
			timer[i].start();
		}
	}

	public void help() {
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(button)){
			label.setText("Score: " + (++numOfClicks));
			System.out.println("X: " + (frame.getWidth()-button.getWidth()) + " Y: " + (frame.getHeight()-button.getHeight()));
			button.setLocation((int)(Math.random()*(frame.getWidth()-button.getWidth())),
					(int)(Math.random()*(frame.getHeight()-button.getHeight()*2)));
			System.out.println("X: " + button.getX() + " Y: " + button.getY());
		}
		if(e.getSource().equals(timer[0])){
			timer[0].setDelay((int)(Math.random()*5000)+2000);
			button.setBackground(new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
			button.setForeground(new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
		}
		if(e.getSource().equals(timer[1])){
			timer[1].setDelay((int)(Math.random()*5000)+2000);
			frame.setBackground(new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
		}
		if(e.getSource().equals(timer[2])){
			timer[2].setDelay((int)(Math.random()*5000)+2000);
			button.setBounds(button.getX(), button.getY(), (int)(Math.random()*100)+100, (int)(Math.random()*100)+30);
		}
	}

}
