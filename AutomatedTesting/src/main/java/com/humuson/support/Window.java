package com.humuson.support;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.humuson.selenium.StartTesting;

public class Window implements ActionListener {
	private PropRead pr = StartTesting.pr;
	public static JFrame mainframe = null;
	
	JTabbedPane bigTabPane = null;
	public static ArrayList<JPanel> bigTabs = new ArrayList<JPanel>();
	
	private JFileChooser filechooser = null;
	private JButton fileButton = null;
	
	JTabbedPane consoleTabPane = null;
	public static ArrayList<JPanel> consoleTabs = new ArrayList<JPanel>();
	private JTextArea allConsole = null;
	private JTextArea errorConsole = null;
	
	public Window() {
		newFrame();
		bigCategoryTab();
		consoleTab();
	}
	
	private void newFrame() {
		mainframe = new JFrame(pr.getPropValue("program.name").equals("") ? "TMS QC Automated Program" : pr.getPropValue("program.name"));
		mainframe.setSize(500, 400);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainframe.setVisible(true);
		mainframe.setLayout(new GridLayout(2, 1));
	}
	
	private void bigCategoryTab() {
		bigTabPane = new JTabbedPane();
		bigTabs.add(MainPanel());
		bigTabs.add(new JPanel());
		bigTabs.add(new JPanel());
		bigTabs.add(new JPanel());
		
		
		bigTabPane.add("Main", bigTabs.get(0));
		bigTabPane.add("Admin", bigTabs.get(1));
		bigTabPane.add("�뷮", bigTabs.get(2));
		bigTabPane.add("�ڵ�", bigTabs.get(3));
		
		mainframe.add(bigTabPane);
	}
	
	private JPanel MainPanel() {
		JPanel panel = new JPanel();
		
		//ù��°
		JPanel panel1 = new JPanel();
		JLabel secondsLabel = new JLabel("��� �ð� : ");
		JComboBox<Integer> seconds = new JComboBox<Integer>();
		for(int i=1; i<=10; i++) seconds.addItem(i);
		seconds.setSelectedIndex(2);
		JLabel unitLabel = new JLabel("��");
		panel1.add(secondsLabel);
		panel1.add(seconds);
		panel1.add(unitLabel);
		
		//�ι�°
		JPanel panel2 = new JPanel();
		fileButton = new JButton("���� ����");
		JLabel chromeLabel = new JLabel("������ driver ���� : ");
		filechooser = new JFileChooser("/");
		panel2.add(chromeLabel);
		panel2.add(fileButton);
		fileButton.addActionListener(this);
		
		//����°
		JPanel panel3 = new JPanel();
		JLabel urlLabel = new JLabel("TMS url �ּ� : ");
		JTextField urlTextfield = new JTextField(20);
		urlTextfield.setSize(300, 50);
		panel3.add(urlLabel);
		panel3.add(urlTextfield);
		
		//�׹�°
		JPanel panel4 = new JPanel();
		JLabel idLabel = new JLabel("ID : ");
		JTextField idTextfield = new JTextField(10);
		idTextfield.setSize(300, 50);
		panel4.add(idLabel);
		panel4.add(idTextfield);
		
		//�ټ���°
		JPanel panel5 = new JPanel();
		JLabel pwLabel = new JLabel("PW : ");
		JTextField pwTextfield = new JTextField(10);
		pwTextfield.setSize(300, 50);
		panel5.add(pwLabel);
		panel5.add(pwTextfield);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		
		panel.setLayout(new GridLayout(5, 1));
		return panel;
	}
	
	private void consoleTab() {
		consoleTabPane = new JTabbedPane();
		consoleTabs.add(new JPanel());
		consoleTabs.add(new JPanel());
		
		allConsole = new JTextArea();
		errorConsole = new JTextArea();
		
		JScrollPane scPane1 = new JScrollPane();
		JScrollPane scPane2 = new JScrollPane();
		
		scPane1.add(allConsole);
		scPane2.add(errorConsole);
		
		consoleTabs.get(0).add(scPane1);
		consoleTabs.get(1).add(scPane2);

		consoleTabPane.add("All", consoleTabs.get(0));
		consoleTabPane.add("Error", consoleTabs.get(1));
		
		
		mainframe.add(consoleTabPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==fileButton) {
			filechooser.showOpenDialog(mainframe);
		}
		
	}
}


