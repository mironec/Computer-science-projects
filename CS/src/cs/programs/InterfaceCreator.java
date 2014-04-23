package cs.programs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cs.CSProgram;

public class InterfaceCreator extends CSProgram implements MouseListener,MouseMotionListener,ActionListener{

	JFrame frame;
	JButton Button_addButton, Button_saveButton;
	JPanel panel;
	
	Point startPoint;
	Point endPoint;
	int currentBrush = 0;
	
	private static final int BRUSH_NO = 0;
	private static final int BRUSH_BUTTON = 1;
	
	public InterfaceCreator(int id){
		super("InterfaceCreator", id);
	}
	
	public void start() {
		frame = new JFrame("Interface Creator");
		Button_addButton = new JButton("Add button");
		Button_saveButton = new JButton("Save interface");
		panel = new JPanel();
		frame.setLayout(null);
		panel.setSize(500,400);
		panel.setLayout(null);
		panel.setBackground(Color.white);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		Button_addButton.addActionListener(this);
		Button_saveButton.addActionListener(this);
		
		//frame.setLayout(null);
		frame.setSize(800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setUndecorated(true);
		
		Button_addButton.setBounds(0, 400, 150, 100);
		Button_saveButton.setBounds(150, 400, 150, 100);
		
		frame.add(panel);
		frame.add(Button_addButton);
		frame.add(Button_saveButton);
		frame.revalidate();
		
		frame.setVisible(true);
	}

	public void help() {
		
	}
	
	private void saveInterface(){
		JFileChooser fileChooser = new JFileChooser(new File("."));
		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
		  File file = fileChooser.getSelectedFile();
		  try{
			  BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			  bw.write(getCurrentInterfaceCode());
			  bw.close();
		  } catch (IOException e) {}
		}
	}
	
	private String getCurrentInterfaceCode(){
		String txt = "";
		
		for(Component c : panel.getComponents() ){
			if(c instanceof JButton) txt += InterfaceParser.STRING_BUTTON + ";"
					+ c.getX() + "," + c.getY() + "," + c.getWidth() + "," + c.getHeight()
					+ ";" + ((JButton)c).getText();
			else if(c instanceof JLabel) txt += InterfaceParser.STRING_LABEL + ";"
					+ c.getX() + "," + c.getY() + "," + c.getWidth() + "," + c.getHeight()
					+ ";" + ((JLabel)c).getText();
			else continue;
			txt+="\n";
		}
		
		return txt;
	}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		startPoint = new Point(e.getPoint());
		if(e.getSource() instanceof JButton){
			JTextField f = new JTextField();
			f.setBounds(0,0,((JButton)e.getSource()).getWidth(),((JButton)e.getSource()).getHeight());
			((JButton)e.getSource()).add(f);
			f.addActionListener(this);
			f.grabFocus();
			panel.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(startPoint == null || endPoint == null){return;}
		if(e.getSource() == panel && currentBrush == BRUSH_BUTTON){
			currentBrush = BRUSH_NO;
			JButton b = new JButton();
			b.setBounds(
					Math.min(startPoint.x,e.getX()),
					Math.min(startPoint.y,e.getY()),
					Math.abs(startPoint.x-e.getX()),
					Math.abs(startPoint.y-e.getY())
			);
			
			b.setEnabled(false);
			panel.add(b);
			b.addMouseListener(this);
			panel.repaint();
		}
	}

	public void mouseDragged(MouseEvent e) {
		endPoint = new Point(e.getPoint());
	}

	public void mouseMoved(MouseEvent e) {}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Button_addButton){
			currentBrush = BRUSH_BUTTON;
		}
		if(e.getSource() == Button_saveButton){
			saveInterface();
		}
		if(e.getSource() instanceof JTextField){
			JTextField jf = (JTextField)e.getSource();
			((JButton)(jf.getParent())).setText(jf.getText());
			jf.getParent().removeAll();
		}
	}
}
