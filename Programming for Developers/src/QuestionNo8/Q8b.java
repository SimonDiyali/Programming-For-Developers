package QuestionNo8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Q8b extends JFrame {
    private JLabel labelInput;
    private JTextArea inputTextArea;
    private JLabel kLabel;
    private JTextField kTextField;
    private JButton computeButton;
    private JLabel resultLabel;

    public Q8b() {
        // Create JFrame
        setTitle("Missing Even Number Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);

        // Add JLabel for input array message
        JLabel inputLabel = new JLabel("Enter the sorted array of even numbers: ");
        inputLabel.setBounds(20, 20, 250, 20);
        add(inputLabel);

        // Add JTextArea for input array
        JTextArea inputTextArea = new JTextArea();
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);
        inputScrollPane.setBounds(20, 50, 350, 80);
        add(inputScrollPane);

        // Add JLabel for input k value message
        JLabel kLabel = new JLabel("Enter the value of k: ");
        kLabel.setBounds(20, 150, 250, 20);
        add(kLabel);

        // Add JTextField for input k value
        JTextField kTextField = new JTextField();
        kTextField.setBounds(20, 180, 150, 20);
        add(kTextField);

        // Add JButton for computation
        JButton computeButton = new JButton("Find Missing Even Number");
        computeButton.setBounds(200, 180, 180, 20);
        add(computeButton);

        // Add JLabel for result
        JLabel resultLabel = new JLabel();
        resultLabel.setBounds(20, 220, 350, 20);
        add(resultLabel);

        // Add ActionListener for computation button
        computeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputTextArea.getText();
                int[] arr = parseInput(input);
                int k = Integer.parseInt(kTextField.getText());
                int missingEvenNumber = findKthMissingEvenNumber(arr, k);
                resultLabel.setText("The " + k + "th missing even number is " + missingEvenNumber);
            }
        });
    }

    // Function to parse input array
    private int[] parseInput(String input) {
        String[] stringArray = input.split("\\s+");
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }

    // Function to find kth missing even number
    public static int findKthMissingEvenNumber(int[] arr, int k) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int missing = arr[mid] - arr[0] - mid;
            if (missing < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return arr[right] + k - (arr[right] - arr[0] - right);
    }

    public static void main(String[] args) {
        Q8b gui = new Q8b();
        gui.setVisible(true);
    }
}