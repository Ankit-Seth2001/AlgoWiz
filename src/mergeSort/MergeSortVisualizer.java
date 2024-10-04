package mergeSort;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MergeSortVisualizer extends JFrame {

    public MergeSortVisualizer() {
        
    	   // Get input array from user
        String input = JOptionPane.showInputDialog("Enter the array elements (comma-separated):");
        String[] inputArray = input.split(",");
        int[] array = new int[inputArray.length];

        try {
        // Parse the input into an integer array
        for (int i = 0; i < inputArray.length; i++) 
            array[i] = Integer.parseInt(inputArray[i].trim());
        }
        catch(NumberFormatException n) {
        	JOptionPane.showMessageDialog(this, "Invalid input! Please enter only integers separated by commas.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
//        		this.dispose();
//        		new main.Testing_page();
        	
        }
        setTitle("Merge Sort Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 1000);
        setLocationRelativeTo(null);

        MergeSortPanel panel = new MergeSortPanel(array);
        add(panel);
//        
        this.setVisible(true);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            MergeSortVisualizer frame = new MergeSortVisualizer();
//            frame.setVisible(true);
//        });
//    }
}

class MergeSortPanel extends JPanel {
    private int[] array;
    private int[][] stages;
    private int stageIndex;
    JButton backButton;

    public MergeSortPanel(int[] arr) {
    	
    	backButton=new JButton("Back");
        backButton.setFocusable(false);
        backButton.setFont(new Font("MV Boli",Font.PLAIN,20));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	new main.Testing_page();
            }
        });
        this.add(backButton);
        this.array = Arrays.copyOf(arr, arr.length);
        stages = new int[10][arr.length]; // Max of 10 stages for this example
        stageIndex = 0;
        setBackground(Color.WHITE);

        new Thread(() -> {
            mergeSort(array, 0, array.length - 1);
            repaint();
        }).start();
    }

    // Override paintComponent to draw the merge sort stages and connections
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));

        int boxWidth = 50;
        int boxHeight = 50;
        int xStart = 50;
        int yStart = 50;
        int yGap = 80;

        // Loop through the stages and draw each
        for (int i = 0; i <= stageIndex; i++) {
            int[] stage = stages[i];
            for (int j = 0; j < stage.length; j++) {
                g2d.setColor(Color.BLACK);
                g2d.drawRect(xStart + j * (boxWidth + 10), yStart + i * yGap, boxWidth, boxHeight);
                g2d.drawString(String.valueOf(stage[j]), xStart + j * (boxWidth + 10) + 20, yStart + i * yGap + 30);
            }

            // Draw arrows between stages
            if (i < stageIndex) {
                int mid = (stage.length / 2) * (boxWidth + 10) + xStart;
                g2d.setColor(Color.GRAY);
                g2d.drawLine(mid, yStart + i * yGap + boxHeight, mid, yStart + (i + 1) * yGap);
            }
        }
    }

    // Merge sort algorithm with visualization
    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Merge two sorted halves and store the state for visualization
    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

        // Capture the array state for visualization
        storeStage(arr);
    }

    // Store the current array state for a specific stage
    private void storeStage(int[] arr) {
        stages[stageIndex] = Arrays.copyOf(arr, arr.length);
        stageIndex++;
        repaint(); // Trigger re-drawing
        try {
            Thread.sleep(1000); // Slow down the visualization
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
