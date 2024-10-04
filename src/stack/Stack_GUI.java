package stack;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import main.Testing_page;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Stack_GUI extends JFrame {
    private Deque<Integer> stack;
    private JPanel stackPanel,controlPanel;
    JScrollPane scrollPane;
    private JTextField inputField;
    private JLabel topElement,size;
    public Stack_GUI() {
    stack = new ArrayDeque<>();

        // Set up the frame
        setTitle("Stack Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
//        setLocationRelativeTo(null);
		setSize(1000, 1000);
        
        setLayout(new BorderLayout());

        // Stack display area (JPanel for visualization)
        stackPanel = new JPanel();
        stackPanel.setPreferredSize(new Dimension(500,800));
        stackPanel.setLayout(new BoxLayout(stackPanel, BoxLayout.Y_AXIS));
        stackPanel.setBackground(new Color(142, 172, 205));
        stackPanel.setFont(new Font("MV Boli",Font.PLAIN,20));
        stackPanel.setBorder(BorderFactory.createTitledBorder("Stack Visualization"));

        // Create a container panel to center the stackPanel in the middle of the screen
        JPanel centeredPanel = new JPanel();
        centeredPanel.setLayout(new GridBagLayout());  // This helps to center the content
        centeredPanel.setBackground(new Color(254, 249, 217));
        centeredPanel.add(stackPanel);  // Add the stackPanel inside the centeredPanel

        scrollPane = new JScrollPane(centeredPanel);
        scrollPane.setVisible(false);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons and input
        
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        
        JButton backButton=new JButton("Back");
        backButton.setFocusable(false);
        backButton.setFont(new Font("MV Boli",Font.PLAIN,20));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	new main.Testing_page();
            }
        });
        controlPanel.add(backButton);
        
        inputField = new JTextField(5);
        inputField.setFont(new Font("MV Boli",Font.PLAIN,20));
        controlPanel.add(inputField);

        JButton pushButton = new JButton("Push");
        pushButton.setFocusable(false);
        pushButton.setFont(new Font("MV Boli",Font.PLAIN,20));
        pushButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pushElement();
            }
        });
        controlPanel.add(pushButton);

        JButton popButton = new JButton("Pop");
        popButton.setFocusable(false);
        popButton.setFont(new Font("MV Boli",Font.PLAIN,20));
        popButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popElement();
            }
        });
        controlPanel.add(popButton);

        topElement = new JLabel();
        topElement.setText("Top :");
        topElement.setFont(new Font("MV Boli",Font.PLAIN,20));
        topElement.setPreferredSize(new Dimension(100,30));
        controlPanel.add(topElement);
        
        size = new JLabel();
        size.setText("Size :");
        size.setFont(new Font("MV Boli",Font.PLAIN,20));
        size.setPreferredSize(new Dimension(100,30));
        controlPanel.add(size);

        add(controlPanel, BorderLayout.SOUTH);
        // Initial visualization
        updateStackDisplay();
        this.setVisible(true);
    }

    // Update the stack visualization in a more graphical manner
    private void updateStackDisplay() {
    	scrollPane.setVisible(true);
        stackPanel.removeAll();  // Clear the current stack visualization

        // Visualize each element of the stack
        for (Integer elem : stack) {
            JLabel stackElement = new JLabel(String.valueOf(elem), SwingConstants.CENTER);
            // Create an EmptyBorder for padding (top, left, bottom, right)
           
            Border paddingBorder = new EmptyBorder(10, 20, 10, 20); 
            stackElement.setAlignmentX(Component.CENTER_ALIGNMENT);
            stackElement.setPreferredSize(new Dimension(150, 50));
            stackElement.setOpaque(true);
            stackElement.setBackground(new Color(135, 206, 250));  // Light blue background
            stackElement.setForeground(Color.BLACK);
            stackElement.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 2), paddingBorder));// Add a border to each element
            stackElement.setFont(new Font("Arial", Font.BOLD, 18));  // Bold font for visibility
            
            stackPanel.add(Box.createVerticalStrut(5));// Add space between elements
            stackPanel.add(Box.createVerticalGlue());
            stackPanel.add(stackElement);
        }
        
        stackPanel.revalidate();  // Refresh the panel
        stackPanel.repaint();
    }
    private void showSize() {
    	size.setText("Size "+String.valueOf(stack.size()));
    }
    private void top()
    {
    	if(stack.peekFirst()==null) {  		
    		topElement.setText("Top :"+String.valueOf(stack.peekFirst()));     		
    	}
     	else {
     		topElement.setText("Top :"+String.valueOf(stack.peekFirst()));
     	}
    }
  
	private void pushElement() {
        try {
            int value = Integer.parseInt(inputField.getText());
            stack.push(value);  // Use Deque's push() method
            updateStackDisplay();
            top();
            showSize();
            inputField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
        }
    }

    private void popElement() {
        if (!stack.isEmpty()) {
            stack.pop();  // Use Deque's pop() method
            updateStackDisplay();
            top();
            showSize();
            
        } else {
            JOptionPane.showMessageDialog(this, "Stack is empty.");
        }
    }

//    public static void main(String[] args) {
//    	Stack_GUI frame = new Stack_GUI();
//    	frame.setVisible(true);
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//            }
//        });
    }

