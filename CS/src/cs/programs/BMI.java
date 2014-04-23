package cs.programs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cs.CSProgram;

public class BMI extends CSProgram implements ActionListener {

	JFrame frame;
	JLabel labels[];
	JTextField height,weight,bmi;
	JButton doIt;
	
	public BMI(int id) {
		super("BMI", id);
	}

	@Override
	public void start() {
		frame = new JFrame("BMI calculator!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(500,500);
		
		labels = new JLabel[3];
		labels[0] = new JLabel("Height: "); labels[0].setBounds(50,50,100,30); frame.add(labels[0]);
		labels[1] = new JLabel("Weight: "); labels[1].setBounds(50,150,100,30); frame.add(labels[1]);
		labels[2] = new JLabel("H/W: "); labels[2].setBounds(50,250,100,30); frame.add(labels[2]);
		
		height = new JTextField(); height.setBounds(150,50,100,30); frame.add(height);
		weight = new JTextField(); weight.setBounds(150,150,100,30); frame.add(weight);
		bmi = new JTextField(); bmi.setBounds(150,250,100,30); frame.add(bmi);
		
		doIt = new JButton("Do it!"); doIt.setBounds(150,350,100,30); frame.add(doIt);
		doIt.addActionListener(this);
		
		frame.setVisible(true);
	}

	@Override
	public void help() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==doIt){
			int valid = 0;
			try{
			if(!height.getText().equals("")&&Double.parseDouble(height.getText())!=0){
				valid += 1;
			}
			} catch(NumberFormatException ex){height.setText("error");}
			try{
			if(!weight.getText().equals("")&&Double.parseDouble(weight.getText())!=0){
				valid += 1 << 1;
			}
			} catch(NumberFormatException ex){weight.setText("error");}
			try{
			if(!bmi.getText().equals("")&&Double.parseDouble(bmi.getText())!=0){
				valid += 1 << 2;
			}
			} catch(NumberFormatException ex){bmi.setText("error");}
			if((valid & 3) == 3){
				bmi.setText("" + Double.parseDouble(height.getText())/Double.parseDouble(weight.getText()));
				bmi.setSelectionStart(0); bmi.setSelectionEnd(0);
			}
			else if((valid & 5) == 5) {
				weight.setText("" + Double.parseDouble(height.getText())/Double.parseDouble(bmi.getText()));
				weight.setSelectionStart(0); weight.setSelectionEnd(0);
			}
			else if((valid & 6) == 6) {
				height.setText("" + Double.parseDouble(weight.getText())*Double.parseDouble(bmi.getText()));
				height.setSelectionStart(0); height.setSelectionEnd(0);
			}
		}
	}

}
