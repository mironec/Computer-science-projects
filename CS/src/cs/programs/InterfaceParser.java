package cs.programs;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InterfaceParser{

	JPanel panel;
	
	public static final String STRING_BUTTON = "button";
	public static final String STRING_LABEL = "label";
	public static final String STRING_TEXT_FIELD = "textfield";
	public static final String STRING_PASSWORD_FIELD = "passwordfield";
	public static final String STRING_PANEL = "panel";
	public static final String STRING_LIST = "list";
	
	private ActionListener actionListener;
	
	public void addActionListener(ActionListener al){
		this.actionListener = al;
	}
	
	private KeyListener keyListener;
	
	public void addKeyListener(KeyListener kl){
		this.keyListener = kl;
	}
	
	private PropertyChangeListener propertyChangeListener;
	
	public void addPropertyChangeListener(PropertyChangeListener pcl){
		this.propertyChangeListener = pcl;
	}
	
	private MouseListener mouseListener;
	
	public void addMouseListener(MouseListener ml){
		this.mouseListener = ml;
	}
	
	public InterfaceParser(){
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(200,200);
	}
	
	public JPanel getInterface(){
		return panel;
	}
	
	public void setInterface(JPanel panel){
		this.panel = panel;
	}
	
	public void parseFile(String s){
		panel.removeAll();
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File(s)));
			String line = "";
			while((line=br.readLine())!=null){
				parseLine(line);
			}
			br.close();
		} catch(IOException e){return;}
		panel.repaint();
	}
	
	public void parseLine(String line){
		String command=line.trim().toLowerCase();
		
		String args[] = line.replaceAll("\\\\;","magicalPony").split(";");
		for(String s : args){s.replaceAll("magicalPony", ";");}
		Component c = null;
		if(command.startsWith(STRING_BUTTON)){
			JButton b = new JButton();
			c = b;
			b.setText(args[args.length-1]);
			b.addActionListener(actionListener);
			b.addMouseListener(mouseListener);
		}
		else if(command.startsWith(STRING_LABEL)){
			JLabel l = new JLabel();
			c = l;
			l.setText(args[args.length-1]);
		}
		else if(command.startsWith(STRING_TEXT_FIELD)){
			JTextField tf = new JTextField();
			c = tf;
			tf.addKeyListener(keyListener);
			tf.addPropertyChangeListener(propertyChangeListener);
			tf.addMouseListener(mouseListener);
		}
		else if(command.startsWith(STRING_PASSWORD_FIELD)){
			JPasswordField pf = new JPasswordField();
			c = pf;
			pf.addKeyListener(keyListener);
			pf.addPropertyChangeListener(propertyChangeListener);
			pf.addMouseListener(mouseListener);
		}
		else if(command.startsWith(STRING_PANEL)){
			JPanel p = new JPanel();
			
			InterfaceParser ip = new InterfaceParser();
			ip.getInterface().setSize(parseBounds(args[1]).width,parseBounds(args[1]).height);
			
			ip.addActionListener(actionListener);
			ip.addKeyListener(keyListener);
			ip.addMouseListener(mouseListener);
			ip.addPropertyChangeListener(propertyChangeListener);
			
			ip.parseFile(args[args.length-1]);
			p = ip.getInterface();
			c = p;
		}
		else if(command.startsWith(STRING_LIST)){
			JList<String> l = new JList<String>();
			
			l.addKeyListener(keyListener);
			l.addMouseListener(mouseListener);
			l.addPropertyChangeListener(propertyChangeListener);
			
			c = l;
		}
		if(c!=null){
			c.setBounds(parseBounds(args[1]));
			c.setName(args[2]);
			
			panel.add(c);
		}
	}
	
	private Rectangle parseBounds(String line){
		Rectangle r = new Rectangle(0,0,0,0);
		
		try{r.setLocation(Integer.parseInt(line.substring(0, line.indexOf(','))),r.y);}catch(NumberFormatException e){}
		line=line.substring(line.indexOf(',')+1);
		try{r.setLocation(r.x,Integer.parseInt(line.substring(0, line.indexOf(','))));}catch(NumberFormatException e){}
		line=line.substring(line.indexOf(',')+1);
		try{r.setSize(Integer.parseInt(line.substring(0, line.indexOf(','))),r.height);}catch(NumberFormatException e){}
		line=line.substring(line.indexOf(',')+1);
		try{r.setSize(r.width,Integer.parseInt(line));}catch(NumberFormatException e){}
		
		return r;
	}
}
