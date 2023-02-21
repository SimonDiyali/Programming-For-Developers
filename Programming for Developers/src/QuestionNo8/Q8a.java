package QuestionNo8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Q8a extends JFrame implements ActionListener {
    private JTextArea inputArea, outputArea;
    private JButton calculateButton;

    public Q8a() {
        setTitle("Maximum Area of Square made by 0s");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 1));

        // Create JTextArea to display input matrix
        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setEditable(true);
        JScrollPane inputScrollPane = new JScrollPane(inputArea);
        panel.add(inputScrollPane);

        // Create JButton to trigger calculation
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        panel.add(calculateButton);

        // Create JTextArea to display result
        outputArea = new JTextArea();
        outputArea.setLineWrap(true);
        outputArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        panel.add(outputScrollPane);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Q8a();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            // Read input matrix from JTextArea
            String input = inputArea.getText();
            String[] rows = input.split("\\n");
            int[][] matrix = new int[rows.length][rows[0].length()];

            for (int i = 0; i < rows.length; i++) {
                String row = rows[i];
                for (int j = 0; j < row.length(); j++) {
                    matrix[i][j] = Integer.parseInt(row.charAt(j) + "");
                }
            }

            // Calculate maximum area of square made by 0s using stacks
            int maxArea = calculateMaxArea(matrix);

            // Display result in JTextArea
            outputArea.setText("Maximum area of square made by 0s: " + maxArea);
        }
    }

    private int calculateMaxArea(int[][] matrix) {
        int maxArea = 0;

        int[][] heights = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    heights[i][j] = i == 0 ? 1 : heights[i - 1][j] + 1;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            int area = calculateMaxAreaFor(heights[i]);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    private int calculateMaxAreaFor(int[] heights) {
        int maxArea = 0;

        Stack<Integer> stack = new Stack<>();
        int i = 0;

        while (i < heights.length) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, Math.min(h, w) * Math.min(h, w));
            }
        }

        while (!stack.isEmpty()) {
            int h = heights[stack.pop()];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1;
            maxArea = Math.max(maxArea, Math.min(h, w) * Math.min(h, w));
        }

        return maxArea;
    }
}