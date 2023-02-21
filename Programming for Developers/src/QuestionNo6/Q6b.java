package QuestionNo6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Q6b extends JFrame implements ActionListener {

    private JLabel inputLabel, targetLabel, outputLabel;
    private JTextField inputField, targetField, outputField;
    private JButton calculateButton;

    // Digit assignments
    private final static Map<Character, Integer> DIGIT_MAP = new HashMap<>();
    static {
        DIGIT_MAP.put('S', 6);
        DIGIT_MAP.put('I', 5);
        DIGIT_MAP.put('X', 0);
        DIGIT_MAP.put('E', 8);
        DIGIT_MAP.put('V', 7);
        DIGIT_MAP.put('N', 2);
        DIGIT_MAP.put('T', 1);
        DIGIT_MAP.put('W', 3);
        DIGIT_MAP.put('Y', 4);
    }

    public Q6b() {
        setTitle("Word Digit Sum");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        inputLabel = new JLabel("Input words (separated by commas):");
        add(inputLabel);
        inputField = new JTextField();
        add(inputField);

        targetLabel = new JLabel("Target word:");
        add(targetLabel);
        targetField = new JTextField();
        add(targetField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        add(calculateButton);

        outputLabel = new JLabel("Result:");
        add(outputLabel);
        outputField = new JTextField();
        outputField.setEditable(false);
        add(outputField);
    }

    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();
        String target = targetField.getText();

        // Split input words into array
        String[] words = input.split(",");

        // Convert words to numbers and sum them up
        int sum = 0;
        for (String word : words) {
            int number = 0;
            for (char c : word.toCharArray()) {
                number = number * 10 + DIGIT_MAP.get(c);
            }
            sum += number;
        }

        // Convert target to number and compare with sum
        int targetNumber = 0;
        for (char c : target.toCharArray()) {
            targetNumber = targetNumber * 10 + DIGIT_MAP.get(c);
        }

        if (sum == targetNumber) {
            outputField.setText("True");
        } else {
            outputField.setText("False");
        }
    }

    public static void main(String[] args) {
        Q6b app = new Q6b();
        app.setVisible(true);
    }
}
