package QuestionNo4;

import javax.swing.*;

public class Q4b extends JFrame {
    private  JTextField inputField;
    private  JButton sortButton;
    private  JLabel stepsLabel;

    public Q4b() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Linked List Sorting");

        inputField = new JTextField(20);
        sortButton = new JButton("Sort");
        stepsLabel = new JLabel("Number of Steps: ");

        sortButton.addActionListener(e -> {
            String input = inputField.getText();
            // Call function to convert linked list to array
            int[] array = convertToList(input);
            // Call function to sort the array and count steps
            int steps = sortArray(array);
            stepsLabel.setText("Number of Steps: " + steps);
        });

        JPanel panel = new JPanel();
        panel.add(inputField);
        panel.add(sortButton);
        panel.add(stepsLabel);
        add(panel);

        pack();
        setVisible(true);
    }

    private int[] convertToList(String input) {
        // Parse input string to get integer values
        String[] nodeValues = input.split(",");
        int[] listValues = new int[nodeValues.length];
        for (int i = 0; i < nodeValues.length; i++) {
            listValues[i] = Integer.parseInt(nodeValues[i].trim());
        }

        return listValues;
    }


    private int sortArray(int[] array) {
        int steps = 0;
        boolean sorted = false;

        while (!sorted) {
            boolean removed = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    // Remove element from array
                    System.arraycopy(array, i + 1, array, i, array.length - i - 1);
                    removed = true;
                    break;
                }
            }

            if (!removed) {
                // Array is sorted
                sorted = true;
            } else {
                // Increment step counter
                steps++;
            }
        }

        return steps;
    }


    public static void main(String[] args) {
        Q4b q = new Q4b();
        q.setVisible(true);
    }

}
