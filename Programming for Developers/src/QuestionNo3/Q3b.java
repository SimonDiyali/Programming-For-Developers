package QuestionNo3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Q3b extends JFrame {
    private JTextField inputField;
    private JTextField patternField;
    private JButton checkButton;
    private JLabel outputLabel;

    public Q3b() {
        setTitle("Pattern Match");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        // Input Field
        JPanel inputPanel = new JPanel();
        JLabel inputLabel = new JLabel("String: ");
        inputField = new JTextField(20);
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        add(inputPanel);

        // Pattern Field
        JPanel patternPanel = new JPanel();
        JLabel patternLabel = new JLabel("Pattern: ");
        patternField = new JTextField(20);
        patternPanel.add(patternLabel);
        patternPanel.add(patternField);
        add(patternPanel);

        // Check Button
        JPanel buttonPanel = new JPanel();
        checkButton = new JButton("Check");
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean result = checkPattern(inputField.getText(), patternField.getText());
                outputLabel.setText(result ? "True" : "False");
            }
        });
        buttonPanel.add(checkButton);
        add(buttonPanel);

        // Output Label
        JPanel outputPanel = new JPanel();
        JLabel outputTextLabel = new JLabel("Output: ");
        outputLabel = new JLabel("");
        outputPanel.add(outputTextLabel);
        outputPanel.add(outputLabel);
        add(outputPanel);
    }

    // Method to check pattern match
    private boolean checkPattern(String str, String pattern) {
        int strIndex = 0, patternIndex = 0;
        while (strIndex < str.length() && patternIndex < pattern.length()) {
            if (pattern.charAt(patternIndex) == '@') {
                return true;
            } else if (pattern.charAt(patternIndex) == '#') {
                strIndex++;
                patternIndex++;
            } else {
                if (str.charAt(strIndex) != pattern.charAt(patternIndex)) {
                    return false;
                }
                strIndex++;
                patternIndex++;
            }
        }
        return (patternIndex == pattern.length() && strIndex == str.length());
    }

    public static void main(String[] args) {
        Q3b frame = new Q3b();
        frame.setVisible(true);
    }
}
