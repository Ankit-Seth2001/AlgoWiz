 
 
 package queue;

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.util.ArrayDeque;
 import java.util.Deque;

 public class Queue_GUI extends JFrame {
     private Deque<Integer> queue;
     private JPanel queuePanel,controlPanel;
     JScrollPane scrollPane;
     private JTextField inputField;
     private JLabel topElement,size;
     public Queue_GUI() {
     queue = new ArrayDeque<>();

         // Set up the frame
         setTitle("queue Visualizer");
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setResizable(false);
//         setLocationRelativeTo(null);
 		 setSize(1000, 1000);
         
         setLayout(new BorderLayout());

         // queue display area (JPanel for visualization)
         queuePanel = new JPanel();
         queuePanel.setPreferredSize(new Dimension(960,450));
         queuePanel.setLayout(new BoxLayout(queuePanel, BoxLayout.X_AXIS));
         queuePanel.setBackground(new Color(142, 172, 205));
         queuePanel.setFont(new Font("MV Boli",Font.PLAIN,20));
         queuePanel.setBorder(BorderFactory.createTitledBorder("Queue Visualization"));

         // Create a container panel to center the queuePanel in the middle of the screen
         JPanel centeredPanel = new JPanel();
         centeredPanel.setLayout(new GridBagLayout());  // This helps to center the content
         centeredPanel.setBackground(new Color(254, 249, 217));
         centeredPanel.add(queuePanel);  // Add the queuePanel inside the centeredPanel

         scrollPane = new JScrollPane(centeredPanel);
         scrollPane.setPreferredSize(new Dimension(500,800));
         scrollPane.setVisible(false);
         add(scrollPane, BorderLayout.CENTER);

         // Panel for buttons and input
         controlPanel = new JPanel();
         controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

         // back button
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

         JButton pushButton = new JButton("Enqueue");
         pushButton.setFocusable(false);
         pushButton.setFont(new Font("MV Boli",Font.PLAIN,20));
         pushButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 enqueue();
             }
         });
         controlPanel.add(pushButton);

         JButton popButton = new JButton("Dequeue");
         popButton.setFocusable(false);
         popButton.setFont(new Font("MV Boli",Font.PLAIN,20));
         popButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 dequeue();
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
         updatequeueDisplay();
         this.setVisible(true);
     }

     // Update the queue 
     private void updatequeueDisplay() {
     	scrollPane.setVisible(true);
         queuePanel.removeAll();  // Clear the current queue visualization

         // Visualize each element of the queue
         for (Integer elem : queue) {
             JLabel queueElement = new JLabel(String.valueOf(elem), SwingConstants.CENTER);
             queueElement.setPreferredSize(new Dimension(150, 50));
             queueElement.setOpaque(true);
             queueElement.setBackground(new Color(135, 206, 250));  // Light blue background
             queueElement.setForeground(Color.BLACK);
             queueElement.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add a border to each element
             queueElement.setFont(new Font("Arial", Font.BOLD, 18));  // Bold font for visibility
             
             queuePanel.add(Box.createVerticalStrut(5));  // Add space between elements
             queuePanel.add(queueElement);
         }
         
         queuePanel.revalidate();  // Refresh the panel
         queuePanel.repaint();
     }
     private void showSize() {
     	size.setText("Size "+String.valueOf(queue.size()));
     }
     private void top()
     {
     	if(queue.peekFirst()==null) {  		
     		topElement.setText("Top :"+String.valueOf(queue.peekFirst()));     		
     	}
      	else {
      		topElement.setText("Top :"+String.valueOf(queue.peekFirst()));
      	}
     }
   
 	private void enqueue() {
         try {
             int value = Integer.parseInt(inputField.getText());
             queue.addLast(value);  // Use Deque's enqueue() method
             updatequeueDisplay();
             top();
             showSize();
             inputField.setText("");
         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
         }
     }

     private void dequeue() {
         if (!queue.isEmpty()) {
             queue.removeFirst();  // Use Deque's pop() method
             updatequeueDisplay();
             top();
             showSize();
             
         } else {
             JOptionPane.showMessageDialog(this, "queue is empty.");
         }
     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//            	 Queue_GUI frame = new Queue_GUI();
//                 frame.setVisible(true);
//             }
//         });
//     }
 }
