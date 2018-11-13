package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

import main.*;

public class Interface extends JFrame implements ActionListener, WindowListener{
	
	JButton bt_start;
	ProdFile prod;
	ConsoFile cons;
	JPanel north;
	JPanel center;
	
	public Interface(String nom,int w,int h, int x, int y) {
		super(nom);
		this.setBounds(x,y,w,h);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(this);
		
		bt_start= new JButton("Start");
		bt_start.addActionListener(this);
		
		Container content=this.getContentPane();
		content.setLayout(new BorderLayout());
		content.add(bt_start,BorderLayout.SOUTH);
		
		File file= new File();
		cons= new ConsoFile(file,1100,"Cons_1");
		prod= new ProdFile(file,1000,"Prod_1");
		
		north= new JPanel();
		north.setLayout(new FlowLayout());
		
		JLabel txt1= new JLabel(" File : ");
		JLabel txt2= new JLabel(file.affiche());
		
		JLabel txtC1= new JLabel(prod.getNom()+", ajout de : "+prod.getLast());
		JLabel txtC2= new JLabel(cons.getNom()+", retrait de : "+cons.getLast());
		
		center= new JPanel();
		center.setLayout(new GridLayout(2,1));
		center.add(txtC1);
		center.add(txtC2);
		
		content.add(north,BorderLayout.NORTH);
		content.add(center,BorderLayout.CENTER);
		north.add(txt1);
		north.add(txt2);
		
		this.setVisible(true);
		
		while(true) {
			txt2.setText(file.affiche());
			txtC1.setText(prod.getNom()+", ajout de : "+prod.getLast());
			txtC2.setText(cons.getNom()+", retrait de : "+cons.getLast());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton bouton= (JButton)e.getSource();
		String lib= bouton.getText();
		if(lib.equals("Start")) {
			if(!cons.isAlive()) {
				this.cons.start();
				this.prod.start();
			}
			else {
				synchronized(cons) {
					cons.notify();
				}
				synchronized(prod) {
					prod.notify();
				}
				cons.pause();
				prod.pause();
			}
			bouton.setText("Stop");
			
		}
		else {
			this.cons.pause();
			this.prod.pause();
			bouton.setText("Start");
		}
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Interface a= new Interface("Interface",500,350,100,100);
	}
}


