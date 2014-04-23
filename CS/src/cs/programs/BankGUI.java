package cs.programs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cs.CSProgram;

public class BankGUI extends CSProgram implements ActionListener, KeyListener, PropertyChangeListener, MouseListener{

	JFrame frame;
	JPanel panel;
	InterfaceParser ip;
	Bank b;
	int currentAcc = 0;
	Account loggedAcc;
	
	public BankGUI(int id) {
		super("BankGUI", id);
	}

	public void start() {
		ip = new InterfaceParser();
		ip.addActionListener(this);
		ip.addPropertyChangeListener(this);
		ip.addKeyListener(this);
		ip.addMouseListener(this);
		ip.parseFile("LoginFrame");
		frame = new JFrame("Bank GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				b.lightEnd();
				super.windowClosing(e);
			}
		});
		panel = ip.getInterface();
		frame.setSize(800, 600);
		panel.setSize(800, 600);
		frame.add(panel);
		frame.setVisible(true);
		
		b = CSProgram.bank;
		b.lightStart();
		
		//expandAccountList();
	}

	public void help() {

	}
	
	public Component getElementByName(String name){
		return getElementByName(panel, name);
	}
	
	public Component getElementByName(Container con, String name){
		for(Component c : con.getComponents()){
			if(c.getName() == null) continue;
			if(c.getName().equals(name)){
				return c;
			}
		}
		return null;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton){
			JButton but = (JButton)e.getSource();
			if(but.getName().equals("loginButton")){
				String ident = ((JTextField)getElementByName("name")).getText();
				String passw = String.valueOf(((JPasswordField)getElementByName("passw")).getPassword());
				try{
				try{
					int id = Integer.valueOf(ident);
					if(id==0){ip.setInterface(panel); ip.parseFile("BankFrame"); expandAccountList(); frame.repaint(); return;}
					Account a = b.getAccountFromId(id);
					if(a == null){
						throw new Exception("Noname");
					}
					else{
						if(a.getPassword().equals(passw)){
							loggedAcc = a;
							throw new Exception("Success");
						}
						else{
							throw new Exception("Wrongpass");
						}
					}
				} catch(NumberFormatException ex){
					for(Account a : b.getAccounts()){
						if(a.getEmail().equals(ident)){
							if(a.getPassword().equals(passw)){
								loggedAcc = a;
								throw new Exception("Success");
							}
							else{
								throw new Exception("Wrongpass");
							}
						}
					}
					throw new Exception("Noname");
				} 
				} catch (Exception ex) {
					if(ex.getMessage().equals("Noname")){
						JOptionPane.showMessageDialog(frame, "No account like that exists!");
					}
					if(ex.getMessage().equals("Wrongpass")){
						JOptionPane.showMessageDialog(frame, "Wrong password!");
					}
					if(ex.getMessage().equals("Success")){
						ip.setInterface(panel);
						ip.parseFile("LoggedFrame");
						resetLoggedStatusPanel();
						panel.repaint();
					}
				}
			}
			if(but.getName().equals("registerButton")){
				ip.setInterface(panel);
				ip.parseFile("RegisterFrame");
				frame.repaint();
			}
			if(but.getName().equals("loggedAccountButt")){
				currentAcc = loggedAcc.getAccountNumber();
				JPanel jp = (JPanel)getElementByName("statusPanel");
				ip.setInterface(jp);
				ip.parseFile("LoggedAccountFrame");
				((JTextField)getElementByName(jp, "fname")).setText(loggedAcc.getFirstName());
				((JTextField)getElementByName(jp, "sname")).setText(loggedAcc.getSecondName());
				((JPasswordField)getElementByName(jp, "passw")).setText(loggedAcc.getPassword());
				((JTextField)getElementByName(jp, "perNum")).setText(loggedAcc.getPersonalNumber());
				((JTextField)getElementByName(jp, "email")).setText(loggedAcc.getEmail());
				((JTextField)getElementByName(jp, "phone")).setText(loggedAcc.getPhone());
				((JTextField)getElementByName(jp, "add")).setText(loggedAcc.getAddress());
				((JTextField)getElementByName(jp, "curr")).setText(loggedAcc.getCurrency());
				frame.repaint();
			}
			if(but.getName().equals("logOutButt")){
				loggedAcc=null;
				ip.setInterface(panel);
				ip.parseFile("LoginFrame");
				frame.repaint();
			}
			if(but.getName().equals("deleteLoggedAccountButt")){
				if(JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete account", JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
					b.deleteAccount(loggedAcc.getAccountNumber());
					loggedAcc=null;
					ip.setInterface(panel);
					ip.parseFile("LoginFrame");
					frame.repaint();
				}
			}
			if(but.getName().equals("sendButt")){
				ip.setInterface((JPanel) getElementByName("statusPanel"));
				ip.parseFile("SendFrame");
				panel.repaint();
			}
			if(but.getName().equals("sendSendButt")){
				JPanel pan = (JPanel) getElementByName("statusPanel");
				try{
					int accNum = Integer.parseInt(((JTextField)getElementByName(pan, "accNum")).getText());
					double amount = Double.parseDouble(((JTextField)getElementByName(pan, "money")).getText());
					
					if(amount > loggedAcc.getBalance() ){JOptionPane.showMessageDialog(frame, "Not enough money on your account!"); return;}
					if(b.getAccountFromId(accNum)==null){JOptionPane.showMessageDialog(frame, "No such account!"); return;}
					b.getAccountFromId(accNum).deposit(amount);
					loggedAcc.withdraw(loggedAcc.getPassword(), amount);
					resetLoggedStatusPanel();
				} catch(NumberFormatException ex){JOptionPane.showMessageDialog(frame, "Not numbers!");}
			}
			if(but.getName().equals("createAccountButt")){
				ip.setInterface((JPanel) getElementByName("statusPanel"));
				ip.parseFile("CreateAccountFrame");
				panel.repaint();
			}
			if(but.getName().equals("deleteAccountButt")){
				@SuppressWarnings("unchecked")
				JList<String> jl = (JList<String>)getElementByName((JPanel) getElementByName("statusPanel"), "accountsList");
				b.deleteAccount(Integer.parseInt(jl.getSelectedValue().split(":")[0]));
				resetStatusPanel();
			}
			if(but.getName().equals("createAccountCreateAccountButt")){
				JPanel pan = (JPanel) getElementByName("statusPanel");
				String passw = String.valueOf(((JPasswordField)getElementByName(pan,"passw")).getPassword());
				String fname = ((JTextField)getElementByName(pan,"fname")).getText();
				String sname = ((JTextField)getElementByName(pan,"sname")).getText();
				String perNum = ((JTextField)getElementByName(pan,"perNum")).getText();
				String email = ((JTextField)getElementByName(pan,"email")).getText();
				String phone = ((JTextField)getElementByName(pan,"phone")).getText();
				String add = ((JTextField)getElementByName(pan,"add")).getText();
				String curr = ((JTextField)getElementByName(pan,"curr")).getText();
				b.addAccount(passw, fname, sname, perNum, email, phone, add, curr);
				resetStatusPanel();
			}
			if(but.getName().equals("registerButt")){
				String passw = String.valueOf(((JPasswordField)getElementByName("passw")).getPassword());
				String fname = ((JTextField)getElementByName("fname")).getText();
				String sname = ((JTextField)getElementByName("sname")).getText();
				String perNum = ((JTextField)getElementByName("perNum")).getText();
				String email = ((JTextField)getElementByName("email")).getText();
				String phone = ((JTextField)getElementByName("phone")).getText();
				String add = ((JTextField)getElementByName("add")).getText();
				String curr = ((JTextField)getElementByName("curr")).getText();
				loggedAcc = b.addAccount(passw, fname, sname, perNum, email, phone, add, curr);
				if(loggedAcc == null){JOptionPane.showMessageDialog(frame, "An error occured."); return;}
				ip.setInterface(panel);
				ip.parseFile("LoggedFrame");
				resetLoggedStatusPanel();
				panel.repaint();
			}
			if(but.getName().equals("accountSaveAccountButt")){
				JPanel pan = (JPanel) getElementByName("statusPanel");
				String passw = String.valueOf(((JPasswordField)getElementByName(pan,"passw")).getPassword());
				try{
				Integer num = Integer.valueOf(((JTextField)getElementByName(pan,"num")).getText());
				String fname = ((JTextField)getElementByName(pan,"fname")).getText();
				String sname = ((JTextField)getElementByName(pan,"sname")).getText();
				String perNum = ((JTextField)getElementByName(pan,"perNum")).getText();
				String email = ((JTextField)getElementByName(pan,"email")).getText();
				String phone = ((JTextField)getElementByName(pan,"phone")).getText();
				String add = ((JTextField)getElementByName(pan,"add")).getText();
				double bal = Double.valueOf(((JTextField)getElementByName(pan,"bal")).getText());
				String curr = ((JTextField)getElementByName(pan,"curr")).getText();
				b.getAccountFromId(currentAcc).copyFrom(num, passw, fname, sname, perNum, b.getAccountFromId(currentAcc).getStartDate(), email, phone, add, bal, curr);
				resetStatusPanel();
				}
				catch(NumberFormatException ex){JOptionPane.showMessageDialog(frame, "Wrong format!"); return;}
			}
			if(but.getName().equals("loggedAccountSaveButt")){
				JPanel pan = (JPanel) getElementByName("statusPanel");
				String passw = String.valueOf(((JPasswordField)getElementByName(pan,"passw")).getPassword());
				String fname = ((JTextField)getElementByName(pan,"fname")).getText();
				String sname = ((JTextField)getElementByName(pan,"sname")).getText();
				String perNum = ((JTextField)getElementByName(pan,"perNum")).getText();
				String email = ((JTextField)getElementByName(pan,"email")).getText();
				String phone = ((JTextField)getElementByName(pan,"phone")).getText();
				String add = ((JTextField)getElementByName(pan,"add")).getText();
				String curr = ((JTextField)getElementByName(pan,"curr")).getText();
				b.getAccountFromId(currentAcc).copyFrom(currentAcc, passw, fname, sname, perNum, b.getAccountFromId(currentAcc).getStartDate(), email, phone, add, b.getAccountFromId(currentAcc).getBalance(), curr);
				resetLoggedStatusPanel();
			}
			if(but.getName().equals("namesSort")){
				if(but.getText().equals("ABC")){
					expandAccountListAlphabetically();
					but.setText("123");
					panel.repaint();
				}
				else if(but.getText().equals("123")){
					expandAccountList();
					but.setText("ABC");
					panel.repaint();
				}
			}
			if(but.getName().equals("XButt")){
				resetStatusPanel();
			}
			if(but.getName().equals("XXButt")){
				ip.setInterface(panel);
				ip.parseFile("LoginFrame");
				frame.repaint();
			}
			if(but.getName().equals("XXXButt")){
				resetLoggedStatusPanel();
			}
		}
	}
	
	public void resetStatusPanel(){
		JPanel pan = (JPanel) getElementByName("statusPanel");
		ip.setInterface(pan);
		ip.parseFile("AccountsFrame");
		expandAccountList();
		panel.repaint();
	}
	
	public void resetStatusPanelAlphabetically(){
		JPanel pan = (JPanel) getElementByName("statusPanel");
		ip.setInterface(pan);
		ip.parseFile("AccountsFrame");
		expandAccountListAlphabetically();
		panel.repaint();
	}
	
	public void resetLoggedStatusPanel(){
		JPanel pan = (JPanel) getElementByName("statusPanel");
		ip.setInterface(pan);
		ip.parseFile("InfoFrame");
		((JLabel)getElementByName(pan, "num")).setText("" + loggedAcc.getAccountNumber());
		((JLabel)getElementByName(pan, "fname")).setText(loggedAcc.getFirstName());
		((JLabel)getElementByName(pan, "sname")).setText(loggedAcc.getSecondName());
		((JLabel)getElementByName(pan, "perNum")).setText(loggedAcc.getPersonalNumber());
		((JLabel)getElementByName(pan, "email")).setText(loggedAcc.getEmail());
		((JLabel)getElementByName(pan, "phone")).setText(loggedAcc.getPhone());
		((JLabel)getElementByName(pan, "add")).setText(loggedAcc.getAddress());
		((JLabel)getElementByName(pan, "bal")).setText(""+loggedAcc.getBalance());
		((JLabel)getElementByName(pan, "curr")).setText(loggedAcc.getCurrency());
		panel.repaint();
	}
	
	public void expandAccountList(){
		@SuppressWarnings("unchecked")
		JList<String> l = (JList<String>)getElementByName((JPanel) getElementByName("statusPanel"), "accountsList");
		DefaultListModel<String> lm = new DefaultListModel<String>();
		ArrayList<Account> accs = new ArrayList<Account>(b.getAccounts());
		Collections.sort(accs,new Comparator<Account>() {
			public int compare(Account o1, Account o2) {
				return Integer.compare(o1.getAccountNumber(),o2.getAccountNumber());
			}
		});
		for(Account a : accs){
			lm.addElement("" + a.getAccountNumber() + ": " + a.getFirstName() + " " + a.getSecondName());
		}
		l.setModel(lm);
	}
	
	public void expandAccountListAlphabetically(){
		@SuppressWarnings("unchecked")
		JList<String> l = (JList<String>)getElementByName((JPanel) getElementByName("statusPanel"), "accountsList");
		DefaultListModel<String> lm = new DefaultListModel<String>();
		ArrayList<Account> accs = new ArrayList<Account>(b.getAccounts());
		Collections.sort(accs,new Comparator<Account>() {
			public int compare(Account o1, Account o2) {
				return (o1.getFirstName()+o1.getSecondName()).compareTo(o2.getFirstName()+o2.getSecondName());
			}
		});
		for(Account a : accs){
			lm.addElement("" + a.getAccountNumber() + ": " + a.getFirstName() + " " + a.getSecondName());
		}
		l.setModel(lm);
	}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {
		if(e.getSource() instanceof JTextField){
			JTextField tf = ((JTextField)e.getSource());
			char c = e.getKeyChar();
			String s = "";
			if(c!='\0'&&c!='\n'&&c!='\r'&&c!='\b'){s+=c;}
			boolean right = false;
			if(tf.getName().equals("fname")){ 
				right = Bank.checkFName(tf.getText()+s);
			}
			if(tf.getName().equals("sname")){ 
				right = Bank.checkSName(tf.getText()+s);
			}
			if(tf.getName().equals("passw")){
				right = Bank.checkPassword(String.valueOf(((JPasswordField)tf).getPassword())+s);
			}
			if(tf.getName().equals("perNum")){
				right = Bank.checkPerNum(tf.getText()+s);
			}
			if(tf.getName().equals("email")){
				right = Bank.checkEmail(tf.getText()+s);
			}
			if(tf.getName().equals("phone")){
				right = Bank.checkPhone(tf.getText()+s);
			}
			if(tf.getName().equals("add")){
				right = Bank.checkAddress(tf.getText()+s);
			}
			if(tf.getName().equals("curr")){right=true;}
			if(tf.getName().equals("searchField")){right=true;}
			if(tf.getName().equals("name")){
				right = (tf.getText()+s).matches("[A-Za-z0-9 ]+");
			}
			if(tf.getName().equals("accNum")){
				right = (tf.getText()+s).matches("\\d+");
			}
			if(tf.getName().equals("money")){
				right = (tf.getText()+s).matches("^[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?$");
			}
			tf.setBackground(right?Color.white:Color.red);
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {}

	public void mouseClicked(MouseEvent e) {
		if(getElementByName("statusPanel")!=null&&e.getSource().equals(getElementByName((JPanel) getElementByName("statusPanel"), "accountsList"))){
			@SuppressWarnings("unchecked")
			JList<String> jl = (JList<String>)e.getSource();
			if(e.getClickCount() == 2){
				JPanel jp = (JPanel)getElementByName("statusPanel");
				ip.setInterface(jp);
				ip.parseFile("AccountFrame");
				Account a = b.getAccountFromId(Integer.parseInt(jl.getSelectedValue().split(":")[0]));
				currentAcc = Integer.parseInt(jl.getSelectedValue().split(":")[0]);
				((JTextField)getElementByName(jp, "num")).setText(""+a.getAccountNumber());
				((JTextField)getElementByName(jp, "fname")).setText(a.getFirstName());
				((JTextField)getElementByName(jp, "sname")).setText(a.getSecondName());
				((JPasswordField)getElementByName(jp, "passw")).setText(a.getPassword());
				((JTextField)getElementByName(jp, "perNum")).setText(a.getPersonalNumber());
				((JTextField)getElementByName(jp, "email")).setText(a.getEmail());
				((JTextField)getElementByName(jp, "phone")).setText(a.getPhone());
				((JTextField)getElementByName(jp, "add")).setText(a.getAddress());
				((JTextField)getElementByName(jp, "bal")).setText(""+a.getBalance());
				((JTextField)getElementByName(jp, "curr")).setText(a.getCurrency());
				jp.repaint();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
