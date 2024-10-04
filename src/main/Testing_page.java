package main;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Testing_page extends JFrame implements ActionListener{
	private JButton Stack;
	private JButton Queue;
	private JButton BST;
	private JButton Msort;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Testing_page();
	}
	
	public Testing_page(){
		
		Stack =new JButton("Stack");
		Queue =new JButton("Queue");
		Msort =new JButton("Merge Sort");
		BST =new JButton("Binary Search Tree");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1500,800);
//		this.setPreferredSize(new Dimension(1000,600));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		
		Stack.setBounds(200, 170, 100, 30);
		Stack.setFocusable(false);
		Stack.addActionListener(this);
		
		Queue.setBounds(600, 170, 100, 30);
		Queue.setFocusable(false);
		Queue.addActionListener(this);
		
		
		BST.setBounds(200, 500, 200, 30);
		BST.setFocusable(false);
		BST.addActionListener(this);
		
		Msort.setBounds(600,500,200,30);
		Msort.setFocusable(false);
		Msort.addActionListener(this);
		
		this.add(Stack);
		this.add(Queue);
		this.add(BST);
		this.add(Msort);
		
		this.setLayout(null);
		this.setVisible(true);
	}

	@Override	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==Stack) {
			this.dispose();
			new stack.Stack_GUI();
		}
		if(e.getSource()==Queue) {
			this.dispose();
			new queue.Queue_GUI();
		}
		if(e.getSource()==BST) {
			this.dispose();
			new bst.BSTVisualization();
		}
		if(e.getSource()==Msort) {
			this.dispose();
			new mergeSort.MergeSortVisualizer();
		}
	}
	
}
