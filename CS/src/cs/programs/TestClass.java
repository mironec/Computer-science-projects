package cs.programs;

import javax.swing.JFrame;

import cs.CSProgram;

public class TestClass extends CSProgram{

	public TestClass(int id) {
		super("test", id);
	}

	public void start() {
		InterfaceParser ip = new InterfaceParser();
		ip.parseFile("inyFrame");
		JFrame frame = new JFrame();
		ip.getInterface().setSize(500,500);
		frame.setSize(500,500);
		frame.add(ip.getInterface());
		frame.setVisible(true);
	}

	public void help() {
		
	}
	

}
